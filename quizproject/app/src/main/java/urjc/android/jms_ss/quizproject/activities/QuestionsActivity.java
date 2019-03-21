package urjc.android.jms_ss.quizproject.activities;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import urjc.android.jms_ss.quizproject.Fragments.ListViewQuestion_Fragment;
import urjc.android.jms_ss.quizproject.Fragments.Multimedia_Fragment;
import urjc.android.jms_ss.quizproject.Fragments.NormalQuestion_Fragment;
import urjc.android.jms_ss.quizproject.Fragments.SoundFragment;
import urjc.android.jms_ss.quizproject.Fragments.Spinner_Fragment;
import urjc.android.jms_ss.quizproject.R;
import urjc.android.jms_ss.quizproject.database.AppDatabase;
import urjc.android.jms_ss.quizproject.java_class.GameManager;
import static urjc.android.jms_ss.quizproject.java_class.AudioPlay.stopSound;

public class QuestionsActivity extends AppCompatActivity implements View.OnClickListener {

    public static FragmentManager fragmentManager;
    public GameManager gameManager;
    public static Context context = null;
    public AppDatabase appDatabase;

    public static String currentAnswer = "";

    private TextView score, questionNumber, totalquestionNumber;
    private Chronometer timer;
    private long initialTime;

    private Button siguiente, back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        appDatabase = AppDatabase.getmInstance(this);

        gameManager = GameManager.getInstancia(this);
        fragmentManager = getSupportFragmentManager();
        context = getApplicationContext();

        timer = (Chronometer) findViewById(R.id.act_questions_time);

        score = findViewById(R.id.act_questions_score);
        score.setText(String.valueOf(0));

        siguiente = findViewById(R.id.act_questions_siguiente);
        siguiente.setOnClickListener(this);

        back = findViewById(R.id.act_questions_backMenu);
        back.setOnClickListener(this);

        gameManager.empezarJuego();

        //Numero de pregunta por la que vamos
        questionNumber = findViewById(R.id.act_questions_numerador);
        questionNumber.setText(String.valueOf(gameManager.getIndicePregunta()+1));
        totalquestionNumber = findViewById(R.id.act_questions_denominador);
        totalquestionNumber.setText(String.valueOf(gameManager.getNumeroPreguntas()));

        if(findViewById(R.id.act_questions_frg_container)!=null){
            if(savedInstanceState!=null){
                return;
            }
            initialTime = SystemClock.elapsedRealtime();
            timer.setBase(initialTime);
            timer.start();
            loadFirstQuestion(gameManager.getQuestion().getControlSys());
        }

    }

    public void loadFirstQuestion(String cs){
        switch (cs){
            case "SOUND":
                fragmentManager.beginTransaction().add(R.id.act_questions_frg_container, new SoundFragment()).commit();
                break;
            case "MULTIMEDIA":
                fragmentManager.beginTransaction().add(R.id.act_questions_frg_container, new Multimedia_Fragment()).commit();
                break;
            case "RADIO_BUTTON":
                fragmentManager.beginTransaction().add(R.id.act_questions_frg_container, new NormalQuestion_Fragment()).commit();
                break;
            case "SPINNER":
                fragmentManager.beginTransaction().add(R.id.act_questions_frg_container, new Spinner_Fragment()).commit();
                break;
            case "LIST_VIEW":
                fragmentManager.beginTransaction().add(R.id.act_questions_frg_container, new ListViewQuestion_Fragment()).commit();
                break;
            default:
                Intent intent = new Intent(this, InterfaceActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }
    }
/*
    public static void loadNextQuestion(){
        contador++;
        currentAnswer = "";
        if(contador < game_manager.preguntas.size()){
            switch (game_manager.preguntas.get(contador).getControlSys()){
                case "SOUND":
                    fragmentManager.beginTransaction().replace(R.id.act_questions_frg_container
                            ,new SoundFragment()).addToBackStack(null).commit();
                    break;
                case "MULTIMEDIA":
                    fragmentManager.beginTransaction().replace(R.id.act_questions_frg_container
                            ,new Multimedia_Fragment()).addToBackStack(null).commit();
                    break;
                case "RADIO_BUTTON":
                    fragmentManager.beginTransaction().replace(R.id.act_questions_frg_container
                            ,new NormalQuestion_Fragment()).addToBackStack(null).commit();
                    break;
                case "SPINNER":
                    fragmentManager.beginTransaction().replace(R.id.act_questions_frg_container
                            ,new Spinner_Fragment()).addToBackStack(null).commit();
                    break;
                case "LIST_VIEW":
                    fragmentManager.beginTransaction().replace(R.id.act_questions_frg_container
                            ,new ListViewQuestion_Fragment()).addToBackStack(null).commit();
                    break;
                default:
                    Intent intent = new Intent(QuestionsActivity.context, InterfaceActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                    break;

            }
        }
        else{
            contador = 0;
            Intent intent = new Intent(QuestionsActivity.context, InterfaceActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }

    }
*/
    public void loadNextQuestion2(){
        stopSound();
        currentAnswer = "";
        gameManager.incrementarIndicePregunta();
        questionNumber.setText(String.valueOf(gameManager.getIndicePregunta()+1));

        if(!gameManager.haTerminado()){
            switch (gameManager.getQuestion().getControlSys()){
                case "SOUND":
                    fragmentManager.beginTransaction().replace(R.id.act_questions_frg_container
                            ,new SoundFragment()).addToBackStack(null).commit();
                    break;
                case "MULTIMEDIA":
                    fragmentManager.beginTransaction().replace(R.id.act_questions_frg_container
                            ,new Multimedia_Fragment()).addToBackStack(null).commit();
                    break;
                case "RADIO_BUTTON":
                    fragmentManager.beginTransaction().replace(R.id.act_questions_frg_container
                            ,new NormalQuestion_Fragment()).addToBackStack(null).commit();
                    break;
                case "SPINNER":
                    fragmentManager.beginTransaction().replace(R.id.act_questions_frg_container
                            ,new Spinner_Fragment()).addToBackStack(null).commit();
                    break;
                case "LIST_VIEW":
                    fragmentManager.beginTransaction().replace(R.id.act_questions_frg_container
                            ,new ListViewQuestion_Fragment()).addToBackStack(null).commit();
                    break;
                default:
                    Intent intent = new Intent(this, InterfaceActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    break;

            }
        }
        else{
            if(gameManager.getPerfil().getUser_max_punt() < gameManager.getPuntuacionJugador()){
                gameManager.getPerfil().setUser_max_punt(gameManager.getPuntuacionJugador());
                gameManager.getDb().UpdatePerfil(gameManager.getPerfil());
            }
            closeQuestions();
        }

    }

    public void closeQuestions(){
        currentAnswer = "";

        //  Parar el cronometro
        timer.stop();
        gameManager.setDuracionPartida(SystemClock.elapsedRealtime() - initialTime);

        gameManager.getPerfil().incrementNumPartidas();
        gameManager.getPerfil().setLast_play(Calendar.getInstance().getTime());

        gameManager.getDb().perfilDao().UpdatePerfil(gameManager.getPerfil());

        Intent intent = new Intent(this, ScoreActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void backToMenu(){
        currentAnswer = "";

        gameManager.terminarJuego();
        //  Parar el cronometro
        timer.stop();

        Intent intent = new Intent(this, InterfaceActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.act_questions_siguiente:
                //hideButtons();
                if(!currentAnswer.isEmpty()){
                    if(currentAnswer.equals(gameManager.getQuestion().getCorrectAnswerText())){
                        gameManager.incrementarPuntuacion(20);
                        Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        gameManager.incrementarPuntuacion(-10);
                        Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
                    }
                    score.setText(String.valueOf(gameManager.getPuntuacionJugador()));
                }
                else{
                    Toast.makeText(this, "Debe seleccionar una respuesta", Toast.LENGTH_LONG).show();
                }
                loadNextQuestion2();
                break;
            case R.id.act_questions_backMenu:
                backToMenu();
                break;
        }
    }

}
