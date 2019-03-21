package urjc.android.jms_ss.quizproject.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import urjc.android.jms_ss.quizproject.Fragments.ConfigurationFragment;
import urjc.android.jms_ss.quizproject.R;
import urjc.android.jms_ss.quizproject.database.AppDatabase;
import urjc.android.jms_ss.quizproject.java_class.GameManager;
import urjc.android.jms_ss.quizproject.java_class.Perfil;

public class ConfigurationActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    //public static AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        fragmentManager = getSupportFragmentManager();
        //appDatabase = AppDatabase.getmInstance(this);

        if(findViewById(R.id.fragment_container)!=null){
            if(savedInstanceState!=null){
                return;
            }
            fragmentManager.beginTransaction().add(R.id.fragment_container, new ConfigurationFragment()).commit();
        }
    }

}