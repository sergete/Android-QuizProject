package urjc.android.jms_ss.quizproject.Fragments;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import urjc.android.jms_ss.quizproject.R;
import urjc.android.jms_ss.quizproject.activities.QuizActivity;
import urjc.android.jms_ss.quizproject.java_class.GameManager;

import static urjc.android.jms_ss.quizproject.activities.QuestionsActivity.currentAnswer;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListViewQuestion_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class ListViewQuestion_Fragment extends Fragment {

    private View view;
    private CustomListView clv;

    private TextView textView;

    private OnFragmentInteractionListener mListener;

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
        view = inflater.inflate(R.layout.fragment_list_view_question_, container, false);

        textView = view.findViewById(R.id.frg_listview_question);
        textView.setText(gameManager.getQuestion().getQuestionText());

        clv = new CustomListView(getActivity()
                , gameManager.getQuestion().getAnswers()
                , gameManager.getQuestion().getAnswersId());

        addListView();

        return view;
    }

    private void addListView(){

        ListView lv = (ListView) view.findViewById(R.id.frg_listview_lv);

        lv.setAdapter(clv);
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

    public class CustomListView extends ArrayAdapter<String> {

        QuizActivity quiz;
        private String [] name;
        private Integer[] imid;
        private Activity context;
        private int contador;

        public CustomListView(Activity context, String [] name , Integer[] imid){
            super(context, R.layout.listview_layout, name);
            this.context = context;
            this.name = name;
            this.imid = new Integer[4];
            this.imid = imid;
            String wait = "";
        }

        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            View r = convertView;
            ViewHolder viewHolder = null;
            if(r == null){
                LayoutInflater layoutInflater = getLayoutInflater();
                r = layoutInflater.inflate(R.layout.listview_layout, null, true);
                viewHolder = new ViewHolder(r);
                r.setTag(viewHolder);
            }
            else{
                viewHolder = (ViewHolder) r.getTag();
            }

            viewHolder.ivw.setImageResource(imid[position]);
            viewHolder.tvw1.setText(name[position]);
            viewHolder.btn.setId(position);
            viewHolder.btn.setText(String.valueOf(position + 1));

            viewHolder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    changeColor(id);
                    v.setBackgroundColor(Color.parseColor("#00ff00"));
                    currentAnswer = name[v.getId()];
                    //Singleton_quizActivity.selectedAnswer(name[v.getId()]);
                }
            });
            return r;
        }

        private void changeColor(int id){
            for(int i=0; i<4; ++i){
                if(i != id){
                    context.findViewById(i).setBackgroundColor(Color.parseColor("#7e7e7e"));
                }
            }
        }

        public Integer[] getImages(){return imid;}

        public class ViewHolder{
            public TextView tvw1;
            public ImageView ivw;
            public Button btn;

            ViewHolder(View v){
                tvw1 = (TextView) v.findViewById(R.id.listview_txtview);
                ivw = (ImageView) v.findViewById(R.id.listview_imageView);
                btn = (Button) v.findViewById(R.id.listview_button);
            }
        }
    }
}
