package urjc.android.jms_ss.quizproject.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Random;

import urjc.android.jms_ss.quizproject.R;
import urjc.android.jms_ss.quizproject.activities.ConfigurationActivity;
import urjc.android.jms_ss.quizproject.java_class.GameManager;
import urjc.android.jms_ss.quizproject.java_class.Perfil;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateUserFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CreateUserFragment extends Fragment implements View.OnClickListener {

    View view;
    TextInputLayout playerLayout;
    TextInputEditText playerName;
    public Button button_start, button_volver, button_photo;

    private GameManager gameManager;

    //region region Camera Variables
    final int PHOTO_RESULT=1;
    private Uri miURI =null;
    String fileName="";
    String string_bitmap="";
    boolean cameraAvailable;
    Integer[] defaultImages;
    //endregion

    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameManager = GameManager.getInstancia(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create_user, container, false);

        createErrorInfo();
        init();
        cameraAvailable = checkCameraHardware();
        if(!cameraAvailable){
            cameraDialog();
            defaultImages = new Integer[5];
            defaultImages[0] = R.drawable.goblin_giant;
            defaultImages[1] = R.drawable.balloon;
            defaultImages[2] = R.drawable.bowler;
            defaultImages[3] = R.drawable.mago;
            defaultImages[4] = R.drawable.portada_clash_royale;
        }

        return view;
    }

    //region InitialMethods region

    //Crea el icono de error en caso de que dejemos el espacio en blanco para informar al usuario
    public void createErrorInfo(){
        EditText editTextView = (EditText) view.findViewById(R.id.txt_playerName);  //Your EditText
        ImageView imageView = view.findViewById(R.id.frg_cu_imageView_userPhoto);

        int errorColor;
        final int version = Build.VERSION.SDK_INT;

        //Get the defined errorColor from color resource.
        if (version >= 23) {
            errorColor = ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorAccent);
        } else {
            errorColor = getResources().getColor(R.color.colorAccent);
        }

        String errorString = "This field cannot be empty";  // Your custom error message.
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(errorColor);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);
        editTextView.setError(spannableStringBuilder);

        playerName = (TextInputEditText) view.findViewById(R.id.txt_playerName);
        playerLayout = (TextInputLayout) view.findViewById(R.id.textInputLayout);
    }

    /** Check if this device has a camera */
    private boolean checkCameraHardware() {
        if (getActivity().getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    public void init() {

        fileName = "";
        button_start = (Button) view.findViewById(R.id.frg_btn_cu_config);
        button_start.setOnClickListener((View.OnClickListener) this);
        button_volver = (Button)view.findViewById(R.id.frg_btn_cu_volver);
        button_volver.setOnClickListener((View.OnClickListener) this);
        button_photo = view.findViewById(R.id.frg_btn_cu_fotoPerfil);
        button_photo.setOnClickListener((View.OnClickListener) this);

    }

    //endregion

    @Override
    public void onClick(View v) {
        String value = "";
        switch (v.getId()){
            case R.id.frg_btn_cu_config:
                //Si la cadena de texto está vacía manda un mensaje de error
                if (playerName.getText().toString().isEmpty()) {
                    playerLayout.setError("Please enter valid name.");
                }
                else if(string_bitmap.isEmpty()){
                    alertDialog();
                    Toast.makeText(getActivity().getApplicationContext(), "You must add a Photo to Create a new Profile", Toast.LENGTH_LONG).show();
                }
                else {
                    //Add new User
                    playerLayout.setError(null);
                    Perfil perfil = new Perfil(playerName.getText().toString(), string_bitmap);

                    gameManager.getDb().insertPerfil(perfil);
                    //ConfigurationActivity.appDatabase.insertPerfil(perfil);

                    //añado perfil a la base de datos
                    Toast.makeText(getActivity().getApplicationContext(), "Perfil added succesfully", Toast.LENGTH_LONG).show();
                    //appDatabase.close();
                    //AppDatabase.CloseTransaction();
                    ConfigurationActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container
                            , new ConfigurationFragment()).addToBackStack(null).commit();
                }
                break;
            case R.id.frg_btn_cu_volver:
                ConfigurationActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container
                        , new ConfigurationFragment()).addToBackStack(null).commit();
                break;
            case R.id.frg_btn_cu_fotoPerfil:
                if(cameraAvailable){
                    takePicture(view);
                }
                else{
                    setDefaultImage();
                }

                break;
        }
    }

    private void setDefaultImage() {
        Random r = new Random();
        int low = 0;
        int high = 4;
        int index = r.nextInt(high-low) + low;

        Bitmap icon = BitmapFactory.decodeResource(getActivity().getResources(),
                defaultImages[index]);
        string_bitmap = Perfil.BitMapToString(icon);

        ImageView imageView=(ImageView)view.findViewById(R.id.frg_cu_imageView_userPhoto);
        imageView.setImageBitmap(icon);
    }

    //region CameraMethods region
    private Uri createFileURI() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(System.currentTimeMillis());
        fileName = "PHOTO_" + timeStamp + ".jpg";
        return Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),fileName));
    }


    public void takePicture(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            miURI = createFileURI();
            getActivity().getIntent().putExtra(MediaStore.EXTRA_OUTPUT, miURI);
            startActivityForResult(takePictureIntent, PHOTO_RESULT);
        }
    }

    //toma la foto y la muestra por pantalla cuando acaba la acción
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_RESULT && resultCode == getActivity().RESULT_OK ) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            string_bitmap = Perfil.BitMapToString(imageBitmap);

            ImageView imageView=(ImageView)view.findViewById(R.id.frg_cu_imageView_userPhoto);
            imageView.setImageBitmap(imageBitmap);
        }
    }
    //endregion

    //region Dialog alert
    private void alertDialog(){
        new AlertDialog.Builder(getContext())
                .setTitle("Información")
                .setMessage("El perfil debe tener foto para continuar")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    private void cameraDialog(){
        new AlertDialog.Builder(getContext())
                .setTitle("Información")
                .setMessage("Camara no disponible presione el botón Foto de Perfil para seleccionar una al azar")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
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
}
