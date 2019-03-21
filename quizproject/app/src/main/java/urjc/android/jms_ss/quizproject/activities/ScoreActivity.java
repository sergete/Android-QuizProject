package urjc.android.jms_ss.quizproject.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import urjc.android.jms_ss.quizproject.R;
import urjc.android.jms_ss.quizproject.java_class.GameManager;

import static urjc.android.jms_ss.quizproject.activities.QuizActivity.N_QUESTIONS;

public class ScoreActivity extends AppCompatActivity {

    //Player p;

    private void init() {
        TextView name = (TextView) findViewById(R.id.score_name);
        TextView score = (TextView) findViewById(R.id.score_totalScore);
        TextView time = (TextView) findViewById(R.id.score_time);
        TextView msg_congrats = (TextView) findViewById(R.id.msg_congrats);
        Button menu = (Button)findViewById(R.id.score_menuback);

        GameManager gameManager = GameManager.getInstancia(this);

        name.setText(gameManager.getNombreJugador());
        score.setText(String.valueOf(gameManager.getPuntuacionJugador()));
        long timeMillis = gameManager.duracionUltimaPartida() / 1000;
        if(timeMillis > 59) {
            time.setText(timeMillis / 60 + "m " + timeMillis % 60  + "s");
        }
        else {
            time.setText(timeMillis + "s");
        }

        if(!gameManager.mostrarEnhorabuena()) {
            msg_congrats.setText(R.string.intentalo_de_nuevo);
        }


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(ScoreActivity.this, ScoreChartActivity.class);
                toy.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(toy);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //Toast.makeText(this, "Puntuación: " + p.puntuacion + " name: " + p.name, Toast.LENGTH_SHORT).show();

        init();
    }

}
