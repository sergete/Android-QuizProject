package urjc.android.jms_ss.quizproject.Fragments;

import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import urjc.android.jms_ss.quizproject.R;
import urjc.android.jms_ss.quizproject.activities.ConfigurationActivity;
import urjc.android.jms_ss.quizproject.activities.InterfaceActivity;
import urjc.android.jms_ss.quizproject.database.AppDatabase;
import urjc.android.jms_ss.quizproject.java_class.GameManager;

public class ConfigurationFragment extends Fragment implements View.OnClickListener{

    private GameManager gameManager;

    Spinner sp_dif, sp_mod;

    String[] difficulties = new String[3];
    String[] preguntas = new String[2];

    String selectedDifficulties = "EASY";
    String selectedPreguntas = "AZAR";

    ArrayAdapter<String> adapter_dif;
    ArrayAdapter<String> adapter_mod;
    private Button create, select, delete, menu_back, apply_changes;
    //endregion


    public ConfigurationFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameManager = GameManager.getInstancia(getActivity());

        difficulties[0]= getString(R.string.facil);
        difficulties[1]= getString(R.string.medio);
        difficulties[2]= getString(R.string.dificil);
        preguntas[0] = getString(R.string.azar);
        preguntas[1] = getString(R.string.multimedia);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //appDatabase = AppDatabase.getmInstance(getActivity().getApplicationContext());

        View view = inflater.inflate(R.layout.fragment_configuration, container, false);
        sp_dif = view.findViewById(R.id.config_sp_dif);
        adapter_dif = new ArrayAdapter<String>(getActivity().getApplicationContext()
                , R.layout.support_simple_spinner_dropdown_item, difficulties);
        sp_dif.setAdapter(adapter_dif);
        sp_dif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDifficulties = difficulties[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_mod = view.findViewById(R.id.config_sp_mod);
        adapter_mod = new ArrayAdapter<String>(getActivity().getApplicationContext()
                , R.layout.support_simple_spinner_dropdown_item, preguntas);
        sp_mod.setAdapter(adapter_mod);
        sp_mod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPreguntas = preguntas[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        create = view.findViewById(R.id.fragment_config_btnCreate);
        create.setOnClickListener((View.OnClickListener) this);

        select = view.findViewById(R.id.fragment_config_btnSelect);
        select.setOnClickListener((View.OnClickListener) this);

        delete = view.findViewById(R.id.fragment_config_btn_delete);
        delete.setOnClickListener((View.OnClickListener) this);

        menu_back = view.findViewById(R.id.fragment_config_menuback);
        menu_back.setOnClickListener((View.OnClickListener) this);

        apply_changes = view.findViewById(R.id.fragment_config_setchanges);
        apply_changes.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.fragment_config_btnCreate:
                ConfigurationActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container
                        , new CreateUserFragment()).addToBackStack(null).commit();
                break;
            case R.id.fragment_config_menuback:
                gameManager.configurarJuego(getActivity().getApplicationContext(), selectedPreguntas, selectedDifficulties);
                Intent intent = new Intent(getActivity(), InterfaceActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.fragment_config_btnSelect:
                ConfigurationActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container
                        , new LoadUsersFragment()).addToBackStack(null).commit();
                break;
            case R.id.fragment_config_btn_delete:
                gameManager.getDb().DeleteAllPerfil();
                //ConfigurationActivity.appDatabase.DeleteAllPerfil();
                break;
            case R.id.fragment_config_setchanges:
                Toast.makeText(getActivity().getApplicationContext(), "Cambios aplicados", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
