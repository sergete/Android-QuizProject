package urjc.android.jms_ss.quizproject.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import urjc.android.jms_ss.quizproject.R;
import urjc.android.jms_ss.quizproject.activities.ImageActivity;
import urjc.android.jms_ss.quizproject.activities.QuestionsActivity;
import urjc.android.jms_ss.quizproject.java_class.GameManager;
import urjc.android.jms_ss.quizproject.java_class.Question;

import static urjc.android.jms_ss.quizproject.activities.QuestionsActivity.currentAnswer;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Spinner_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class Spinner_Fragment extends Fragment {

    View view;
    private LinearLayout linearLayout;
    private Button siguiente, volver;
    private ImageView imageView;
    private TextView score;

    private int imageIndex;

    private GameManager gameManager;

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
        view = inflater.inflate(R.layout.fragment_spinner, container, false);

        init();
        addSpinner();

        return view;
    }

    private void init(){

        TextView textView = view.findViewById(R.id.frg_spinner_question);
        textView.setText(gameManager.getQuestion().getQuestionText());

        linearLayout = view.findViewById(R.id.frg_spinner_questionLayout);

        imageView = view.findViewById(R.id.frg_spinner_imageQuestion);
        imageView.setImageResource(gameManager.getQuestion().getQuestionImg());

        imageIndex = gameManager.getQuestion().getQuestionImg();

        imageView.setOnClickListener(new View.OnClickListener() {
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


    private void addSpinner() {

        TextView select = new TextView(getContext());
        select.setText("Seleccione una respuesta:");
        select.setTextColor(Color.WHITE);
        select.setPadding(16,0, 16,10);

        String [] questions = gameManager.getQuestion().getAnswers();

        Spinner spinner = new Spinner(getContext());
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, questions);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentAnswer = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        linearLayout.addView(select,0);
        linearLayout.addView(spinner, 1);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
