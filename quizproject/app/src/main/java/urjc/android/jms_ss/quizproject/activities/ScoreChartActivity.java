package urjc.android.jms_ss.quizproject.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import urjc.android.jms_ss.quizproject.R;
import urjc.android.jms_ss.quizproject.database.AppDatabase;
import urjc.android.jms_ss.quizproject.java_class.Perfil;

public class ScoreChartActivity extends AppCompatActivity {

    private ListView lsvUser;
    //Adapter
    List<Perfil> userList;
    CustomerAdapter customerAdapter;
    Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_chart);

        volver = findViewById(R.id.act_scorechart_volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InterfaceActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        userList = new ArrayList<Perfil>();

        //init view
        lsvUser = findViewById(R.id.act_scorechart_lv);

        loadListView();
    }

    private void loadListView(){

        if(userList.size() > 0)
            userList.clear();

        //load all data from Database
        AppDatabase appDatabase = AppDatabase.getmInstance(this);
        userList = appDatabase.getHighScorePerfiles();
        //userList = appDatabase.getAllPerfiles();

        customerAdapter = new CustomerAdapter(this);
        //asignamos la vista en la que se creará el menú flotante de seleccionar perfil, Update o Delete perfi
        lsvUser.setAdapter(customerAdapter);
    }

    //Clase de apoyo para crear un customListView para mostrar los perfiles con la foto
    class CustomerAdapter extends BaseAdapter {

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
            convertView = getLayoutInflater().inflate(R.layout.score_chart_listview, null);

            ImageView imageView = convertView.findViewById(R.id.scorechart_imageView);
            TextView textView = convertView.findViewById(R.id.scorechart_name);
            TextView scoreView = convertView.findViewById(R.id.scorechart_score);

            //decodifica la foto y la imprime en una ListView
            Bitmap bitmap= Perfil.StringToBitMap(userList.get(position).getUser_photo());
            imageView.setImageBitmap(bitmap);

            textView.setText((CharSequence) userList.get(position).getUser_name());
            scoreView.setText(String.valueOf(userList.get(position).getUser_max_punt()));

            return convertView;
        }
    }
}
