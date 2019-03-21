package urjc.android.jms_ss.quizproject.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import urjc.android.jms_ss.quizproject.R;
import urjc.android.jms_ss.quizproject.activities.ImageActivity;
import urjc.android.jms_ss.quizproject.activities.InterfaceActivity;
import urjc.android.jms_ss.quizproject.activities.QuestionsActivity;
import urjc.android.jms_ss.quizproject.activities.QuizActivity;
import urjc.android.jms_ss.quizproject.java_class.GameManager;
import urjc.android.jms_ss.quizproject.java_class.Question;

import static urjc.android.jms_ss.quizproject.activities.QuestionsActivity.currentAnswer;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NormalQuestion_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class NormalQuestion_Fragment extends Fragment {

    private View view;

    private ImageView imageQuestion;
    private LinearLayout linearLayout;

    private TextView score;

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
        view = inflater.inflate(R.layout.fragment_normal_question_, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setQuestion();
        setImage();
        addRadioButtons();

        return view;
    }

    private void setQuestion() {
        TextView textView = view.findViewById(R.id.frg_normalquestion_question);
        textView.setText(gameManager.getQuestion().getQuestionText());
    }

    private void setImage(){
        ImageView imgQuestion = (ImageView) view.findViewById(R.id.frg_normalquestion_imageQuestion);
        final int imageIndex = gameManager.getQuestion().getQuestionImg();
        imgQuestion.setImageResource(imageIndex);

        imgQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(getActivity(), ImageActivity.class);
                Bundle b = new Bundle();
                b.putInt("img", imageIndex);
                toy.putExtras(b);

                startActivity(toy);
            }
        });
    }

    private void addRadioButtons() {

        //layout para las preguntas con Spinner
        linearLayout = view.findViewById(R.id.frg_normalquestion_questionLayout);

        RadioGroup options = new RadioGroup(getActivity().getApplicationContext());

        RadioButton option[] = new RadioButton[4];

        for(int i = 0; i < option.length; i++) {
            option[i] = new RadioButton(getActivity().getApplicationContext());
            option[i].setText(gameManager.getQuestion().getAnswers(i));
            option[i].setTextColor(Color.WHITE);
            option[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentAnswer = ((RadioButton)view.findViewById(v.getId())).getText().toString();
                }
            });
            options.addView(option[i]);
        }

        linearLayout.addView(options);
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
