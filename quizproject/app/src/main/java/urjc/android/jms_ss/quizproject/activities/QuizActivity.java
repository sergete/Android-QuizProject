package urjc.android.jms_ss.quizproject.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import urjc.android.jms_ss.quizproject.R;
import urjc.android.jms_ss.quizproject.java_class.Question;

public class QuizActivity extends AppCompatActivity {

    TextView playerName, playerName2;

    public enum Control_system {
        RADIO_BUTTON, LIST_VIEW, SPINNER
    }

    public static QuizActivity Singleton_quizActivity = null;
    public static int N_QUESTIONS = 5;

    private Question[] questions;

    private TextView score;
    private TextView question;
    private Button btn_backMenu;
    private Button btn_next;

    private ListView listview;
    private RelativeLayout rl_listView;

    private String currentAnswer;
    private String correctAnswer;

    private int scorePoints;
    private int questionNumber;

    //Player p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Singleton_quizActivity = this;

        //p = (Player) getIntent().getParcelableExtra("PLAYER");

        init();
    }

    public QuizActivity getInstance(){
        if(Singleton_quizActivity == null){
            Singleton_quizActivity = this;
        }
        return Singleton_quizActivity;
    }

    private void init() {
        rl_listView = (RelativeLayout)findViewById(R.id.quiz_listviewLayout);

        createQuestions();

        currentAnswer = "";

        questionNumber = 0;

        score = (TextView)findViewById(R.id.score);
        //score.setText("" + scorePoints);
        //score.setText("" + p.puntuacion);
        question = (TextView)findViewById(R.id.question);

        listview = (ListView) findViewById(R.id.quiz_listview);

        updateQuestion();

        btn_backMenu = (Button)findViewById(R.id.backMenu);
        btn_backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(QuizActivity.this, InterfaceActivity.class);

                startActivity(toy);
            }
        });

        btn_next = (Button) findViewById(R.id.next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentAnswer != "") {
                    if(currentAnswer.equals(correctAnswer)) {
                        updateScore(true);
                        Toast.makeText(QuizActivity.this, "Correcto", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        updateScore(false);
                        Toast.makeText(QuizActivity.this, "Incorrecto: la respuesta correcta es " + correctAnswer, Toast.LENGTH_SHORT ).show();
                    }
                    if(questionNumber < N_QUESTIONS) {
                        updateQuestion();
                    }
                    else {
                        //  Nueva actividad de mostrar la puntuacion
                        Intent toy = new Intent(QuizActivity.this, ScoreActivity.class);
                        Bundle b = new Bundle();
                        //  Pasar objeto player ------------------------------------------------------------------------------------***
                        b.putInt("score", scorePoints);
                        b.putString("name", "Player1");
                        toy.putExtras(b);

                        //toy.putExtra("PLAYER", p);
                        startActivity(toy);
                    }
                }
            }
        });
    }

    private void createQuestions() {
        /*questions = new Question[N_QUESTIONS];
        questions[0] = new Question("¿En qué año se lanzó clash royale?", Control_system.RADIO_BUTTON, R.drawable.portada_clash_royale);
        questions[0].addAnswers("2012", "2016", "2014", "2017", 2);
        questions[1] = new Question("¿Cuánto elixir cuesta esta carta?", Control_system.SPINNER, R.drawable.balloon);
        questions[1].addAnswers("5", "3", "9", "8", 1);
        questions[2] = new Question("¿Cómo se llama esta carta?", Control_system.RADIO_BUTTON, R.drawable.bowler);
        questions[2].addAnswers("Minero", "Bruja", "Mago", "Lanzarrocas", 4);
        questions[3] = new Question("¿Cuál ha sido la última carta añadida?", Control_system.LIST_VIEW);
        String[] answers = {"Reclutas Reales", "Megacaballero", "Duende Gigante", "Puercos Reales"};
        Integer[] images = {R.drawable.royalrecruits, R.drawable.megacaballero, R.drawable.goblin_giant, R.drawable.royalhogscard};
        questions[3].addImages(answers,images,3, this);
        questions[4] = new Question("¿Qué carta hace más daño?", Control_system.LIST_VIEW);
        String [] ans = {"Ejército de esqueletos", "Chispitas", "Pekka", "Trío de Mosqueteras"};
        Integer []im = {R.drawable.ejercito_de_esqueletos, R.drawable.chispitas, R.drawable.pekka, R.drawable.trio_de_mosqueteras};
        questions[4].addImages(ans, im,1, this);
        */
    }

    private void updateScore(boolean add) {
        /*if(add) {
            p.puntuacion += 3;
        } else {
            if((p.puntuacion - 2) > 0) {
                p.puntuacion -= 2;
            }
            else {
                p.puntuacion = 0;
            }

        }

        score.setText("" + p.puntuacion);
        */
    }

    private void updateQuestion() {
        /*question.setText(questions[questionNumber].getQuestion());
        correctAnswer = questions[questionNumber].getCorrectAnswer();
        LinearLayout layoutButtons = (LinearLayout) findViewById(R.id.questionLayout);
        layoutButtons.removeAllViews();

        //  Comprobar si existe foto en la pregunta
        if(questions[questionNumber].hasImage()) {
            ImageView imgQuestion = (ImageView) findViewById(R.id.imageQuestion);
            imgQuestion.setVisibility(View.VISIBLE);
            imgQuestion.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
            final int imageIndex = questions[questionNumber].getImage();
            imgQuestion.setImageResource(imageIndex);


            imgQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toy = new Intent(QuizActivity.this, ImageActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("img", imageIndex);
                    toy.putExtras(b);

                    startActivity(toy);
                }
            });
        }
        else{
            ImageView imageView = (ImageView)findViewById(R.id.imageQuestion);
            imageView.setImageResource(0);
            imageView.setMaxHeight(0);
            imageView.setMinimumHeight(0);
            imageView.setVisibility(View.GONE);
        }

        // Segun el tipo de sistema de control
        switch(questions[questionNumber].getControlSys()) {
            case RADIO_BUTTON:
                addRadioButtons(questions[questionNumber], layoutButtons);
                break;
            case SPINNER:
                addSpinner(questions[questionNumber], layoutButtons);
                break;
            case LIST_VIEW:
                addListView((questions[questionNumber]), layoutButtons);
                break;
        }
        questionNumber++;
        */
    }

    private void addRadioButtons(Question q, LinearLayout l) {
        //Hace invisible el relative layout del LIST VIEW
        if(rl_listView.getVisibility() == View.VISIBLE)
            rl_listView.setVisibility(View.GONE);

        RadioGroup options = new RadioGroup(this);

        RadioButton option[] = new RadioButton[4];

        for(int i = 0; i < option.length; i++) {
            option[i] = new RadioButton(this);
            option[i].setText(q.getAnswers()[i]);
            option[i].setTextColor(Color.WHITE);
            option[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentAnswer = ((RadioButton)findViewById(v.getId())).getText().toString();
                }
            });
            options.addView(option[i]);
        }

        l.addView(options);
    }

    private void addSpinner(Question q, LinearLayout l) {
        //Hace invisible el relative layout del LIST VIEW
        if(rl_listView.getVisibility() == View.VISIBLE)
            rl_listView.setVisibility(View.GONE);

        TextView select = new TextView(this);
        select.setText("Seleccione una respuesta:");
        select.setTextColor(Color.WHITE);
        select.setPadding(16,0, 16,10);
        Spinner sp = new Spinner(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, q.getAnswers());
        sp.setBackgroundColor(Color.parseColor("#44ffffff"));
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentAnswer = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        l.addView(select);
        l.addView(sp);
    }

    private void addListView(Question q, LinearLayout l){
        LinearLayout rl = (LinearLayout) findViewById(R.id.questionLayout);
        rl.setVisibility(View.GONE);

        rl_listView.setVisibility(View.VISIBLE);
        ListView lv = (ListView) findViewById(R.id.quiz_listview);

        //lv.setAdapter(q.getCustomListView());
    }

    public void selectedAnswer(String temp){
        currentAnswer = temp;
    }

}
