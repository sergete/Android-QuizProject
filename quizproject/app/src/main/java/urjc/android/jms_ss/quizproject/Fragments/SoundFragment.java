package urjc.android.jms_ss.quizproject.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.icu.text.TimeZoneFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import urjc.android.jms_ss.quizproject.R;
import urjc.android.jms_ss.quizproject.activities.InterfaceActivity;
import urjc.android.jms_ss.quizproject.activities.MultimediaSolutionActivity;
import urjc.android.jms_ss.quizproject.activities.QuestionsActivity;
import urjc.android.jms_ss.quizproject.java_class.GameManager;

import static urjc.android.jms_ss.quizproject.activities.QuestionsActivity.currentAnswer;
import static urjc.android.jms_ss.quizproject.java_class.AudioPlay.playAudioSounds;
import static urjc.android.jms_ss.quizproject.java_class.AudioPlay.ReplayAudioSounds;
import static urjc.android.jms_ss.quizproject.java_class.AudioPlay.stopSound;

import static urjc.android.jms_ss.quizproject.java_class.AudioPlay.stopAudio;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SoundFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class SoundFragment extends Fragment implements View.OnClickListener{

    private Button btn1, btn2, btn3, btn4, sound, solution;
    private TextView txt;
    private boolean solution_pressed;

    private GameManager gameManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameManager = GameManager.getInstancia(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sound, container, false);

        solution_pressed = false;

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txt = view.findViewById(R.id.frg_sound_question);

        solution = view.findViewById(R.id.frg_sound_solution);
        solution.setOnClickListener(this);

        btn1 = view.findViewById(R.id.frg_sound_ans1);
        btn1.setOnClickListener(this);

        btn2 = view.findViewById(R.id.frg_sound_ans2);
        btn2.setOnClickListener(this);

        btn3 = view.findViewById(R.id.frg_sound_ans3);
        btn3.setOnClickListener(this);

        btn4 = view.findViewById(R.id.frg_sound_ans4);
        btn4.setOnClickListener(this);

        sound = view.findViewById(R.id.frg_sound_replayAudio);
        sound.setOnClickListener(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                playAudioSounds(QuestionsActivity.context, gameManager.getQuestion().getSoundId());
            }
        },1000);


        questions();

        return view;
    }

    private void questions(){
        txt.setText(gameManager.getQuestion().getQuestionText());
        btn1.setText(gameManager.getQuestion().getAnswers(0));
        btn2.setText(gameManager.getQuestion().getAnswers(1));
        btn3.setText(gameManager.getQuestion().getAnswers(2));
        btn4.setText(gameManager.getQuestion().getAnswers(3));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.frg_sound_ans1:
                if(!solution_pressed){
                    currentAnswer = btn1.getText().toString();
                    Toast.makeText(getContext(), "Respuesta 1 seleccionada", Toast.LENGTH_SHORT).show();
                }
                else{
                    selectedAnswerDialog();
                }
                break;
            case R.id.frg_sound_ans2:
                if(!solution_pressed){
                    currentAnswer = btn2.getText().toString();
                    Toast.makeText(getContext(), "Respuesta 2 seleccionada", Toast.LENGTH_SHORT).show();
                }
                else{
                    selectedAnswerDialog();
                }
                break;
            case R.id.frg_sound_ans3:
                if(!solution_pressed){
                    currentAnswer = btn3.getText().toString();
                    Toast.makeText(getContext(), "Respuesta 3 seleccionada", Toast.LENGTH_SHORT).show();
                }
                else{
                    selectedAnswerDialog();
                }
                break;
            case R.id.frg_sound_ans4:
                if(!solution_pressed){
                    currentAnswer = btn4.getText().toString();
                    Toast.makeText(getContext(), "Respuesta 4 seleccionada", Toast.LENGTH_SHORT).show();
                }
                else{
                    selectedAnswerDialog();
                }
                break;
            case R.id.frg_sound_replayAudio:
                    ReplayAudioSounds();
                break;
            case R.id.frg_sound_solution:
                stopSound();
                if(!currentAnswer.isEmpty()){
                    solution_pressed = true;
                    Intent intent = new Intent(getContext(), MultimediaSolutionActivity.class);
                    startActivity(intent);
                }
                else{
                    alertDialog();
                }
                break;
        }
    }

    private void alertDialog(){
        new AlertDialog.Builder(getContext())
                .setTitle("Información")
                .setMessage("Debe seleccionar una respuesta antes de ver la solución")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    private void selectedAnswerDialog(){
        new AlertDialog.Builder(getContext())
                .setTitle("Información")
                .setMessage("Ya ha seleccionado una respuesta no puede cambiar después de ver la solución")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

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
