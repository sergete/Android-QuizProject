package urjc.android.jms_ss.quizproject.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import urjc.android.jms_ss.quizproject.R;
import urjc.android.jms_ss.quizproject.java_class.GameManager;

import static urjc.android.jms_ss.quizproject.java_class.AudioPlay.playAudio;
import static urjc.android.jms_ss.quizproject.java_class.AudioPlay.stopAudio;

public class InterfaceActivity extends Activity {

    public Button btn_startgame, btn_exit, btn_config;
    private GameManager gameManager;

    public void init(){
        gameManager = GameManager.getInstancia(this);

        playAudio(getApplicationContext(),R.raw.clash_royale_main_theme_soundtrack);
        btn_startgame = (Button)findViewById(R.id.btn_startgame);
        btn_startgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(gameManager.getConfig().getPlayerId() != -1){
                    stopAudio();
                    Intent intent = new Intent(getApplicationContext(), QuestionsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

            }
        });

        //Si pulsa exit cerramos la aplicación
        btn_exit = (Button)findViewById(R.id.btn_interfSalir);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        btn_config = findViewById(R.id.btn_interf_configuracion);
        btn_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConfigurationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);

        init();
    }


}
