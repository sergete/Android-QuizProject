package urjc.android.jms_ss.quizproject.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import urjc.android.jms_ss.quizproject.R;
import urjc.android.jms_ss.quizproject.activities.ConfigurationActivity;
import urjc.android.jms_ss.quizproject.activities.QuestionsActivity;
import urjc.android.jms_ss.quizproject.java_class.GameManager;
import urjc.android.jms_ss.quizproject.java_class.Perfil;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoadUsersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoadUsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoadUsersFragment extends Fragment implements View.OnClickListener, View.OnCreateContextMenuListener {

    private final int PHOTO_RESULT = 1;
    View view;

    private GameManager gameManager;

    private ListView lsvUser;
    //Adapter
    List<Perfil> userList;
    CustomerAdapter customerAdapter;

    Button back, select;
    private OnFragmentInteractionListener mListener;

    private int indexProfile = -1;

    public LoadUsersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment LoadUsersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoadUsersFragment newInstance(String param1, String param2) {
        LoadUsersFragment fragment = new LoadUsersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameManager = GameManager.getInstancia(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_load_users, container, false );

        userList = new ArrayList<Perfil>();

        //init view
        lsvUser = view.findViewById(R.id.fragment_loadUsers_listview);

        back = view.findViewById(R.id.fragment_loadUsers_btn_menuback);
        back.setOnClickListener(this);

        SetListener();

        //registerForContextMenu(lsvUser);
        loadListView();

        Toast.makeText(getContext(), "Press Long time over a profile to show options",Toast.LENGTH_LONG).show();

        return view;
    }

    private void loadListView(){

        if(userList.size() > 0)
            userList.clear();

        //load all data from Database
        userList = gameManager.getDb().getAllPerfiles();
        //userList = ConfigurationActivity.appDatabase.getAllPerfiles();

        customerAdapter = new CustomerAdapter(getContext());
        //asignamos la vista en la que se creará el menú flotante de seleccionar perfil, Update o Delete perfi
        lsvUser.setAdapter(customerAdapter);
    }

    //Crea un listener para generar un menú cuando clickas en la listview
    private void SetListener(){
        lsvUser.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
                menu.setHeaderTitle("Select action:");

                menu.add(Menu.NONE, 0, Menu.NONE, "SELECT");
                menu.add(Menu.NONE, 1, Menu.NONE, "UPDATE");
                menu.add(Menu.NONE, 2, Menu.NONE, "DELETE");
            }
        });

    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.fragment_loadUsers_btn_menuback:
                ConfigurationActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container
                        , new ConfigurationFragment()).addToBackStack(null).commit();
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        final Perfil perfil = userList.get(info.position);
        switch (item.getItemId()){
            case 0: //SELECT
                gameManager.seleccionarPerfil(perfil.getUser_id(), perfil.getUser_name());
                gameManager.setPerfil(perfil);
                Toast.makeText(getContext(), "Perfil seleccionado", Toast.LENGTH_SHORT).show();
                break;
            case 1: //UPDATE
                takePicture(view, info.position);
                Toast.makeText(getContext(), "Foto cambiada", Toast.LENGTH_SHORT).show();
                break;
            case 2: //REMOVE
                new AlertDialog.Builder(getContext())
                        .setTitle("Confirmación")
                        .setMessage("Do you want to delete " + perfil.getUser_name())
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(perfil != null && gameManager.getPerfil() != null && perfil.getUser_id() == gameManager.getPerfil().getUser_id()){
                                    gameManager.seleccionarPerfil(-1, "");
                                }
                                gameManager.getDb().DeletePerfil(perfil);
                                //ConfigurationActivity.appDatabase.DeletePerfil(perfil);
                                Toast.makeText(getContext(),"Perfil borrado", Toast.LENGTH_SHORT).show();
                                loadListView();
                                dialog.dismiss();
                            }
                        }).create().show();

                break;
        }

        return super.onContextItemSelected(item);
    }

    //region CameraMethods region
    private Uri createFileURI() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(System.currentTimeMillis());
        String fileName = "PHOTO_" + timeStamp + ".jpg";
        return Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),fileName));
    }


    public void takePicture(View view, int userId) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            Uri miURI = createFileURI();
            getActivity().getIntent().putExtra(MediaStore.EXTRA_OUTPUT, miURI);
            indexProfile = userId;
            getActivity().getIntent().putExtra("User_id", userId);
            startActivityForResult(takePictureIntent, PHOTO_RESULT);
        }
    }

    //toma la foto y la muestra por pantalla cuando acaba la acción
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_RESULT && resultCode == getActivity().RESULT_OK ) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            String string_bitmap = Perfil.BitMapToString(imageBitmap);
            if(indexProfile > -1){
                Perfil p = userList.get(indexProfile);
                //change photo from profile
                p.setUser_photo(string_bitmap);

                //Update profile
                gameManager.getDb().UpdatePerfil(p);
                //ConfigurationActivity.appDatabase.UpdatePerfil(p);
                //reset value
                indexProfile = -1;

                //Recargamos la página
                loadListView();

                //Photo changed message
                Toast.makeText(getContext(), "Photo changed",Toast.LENGTH_LONG).show();
            }

        }
    }
    //endregion


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //Clase de apoyo para crear un customListView para mostrar los perfiles con la foto
    class CustomerAdapter extends BaseAdapter{

        private Context context;
        public CustomerAdapter(Context context){
            super();
            this.context = context;
        }
        @Override
        public int getCount() {
            try{
                return userList.size();
            }catch (Exception e){
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            return userList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return userList.indexOf(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.fragment__loadusers_listview, null);

            ImageView imageView = view.findViewById(R.id.frg_loadUsers_imageContainer);
            TextView textView = view.findViewById(R.id.frg_loadUsers_txtContainer);

            //decodifica la foto y la imprime en una ListView
            Bitmap bitmap= Perfil.StringToBitMap(userList.get(position).getUser_photo());
            imageView.setImageBitmap(bitmap);

            textView.setText((CharSequence) userList.get(position).toString());

            return view;
        }
    }
}
