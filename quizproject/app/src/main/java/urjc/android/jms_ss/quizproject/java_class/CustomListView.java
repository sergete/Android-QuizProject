package urjc.android.jms_ss.quizproject.java_class;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import urjc.android.jms_ss.quizproject.activities.QuizActivity;
import urjc.android.jms_ss.quizproject.R;

import static urjc.android.jms_ss.quizproject.activities.QuizActivity.Singleton_quizActivity;

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
        this.imid = imid;

    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r = convertView;
        ViewHolder viewHolder = null;
        if(r == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.listview_layout, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)r.getTag();
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
                Singleton_quizActivity.selectedAnswer(name[v.getId()]);
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

    public Integer[] getImages(){
        return imid;
    }

    class ViewHolder{
        TextView tvw1;
        ImageView ivw;
        Button btn;

        ViewHolder(View v){
            tvw1 = (TextView) v.findViewById(R.id.listview_txtview);
            ivw = (ImageView) v.findViewById(R.id.listview_imageView);
            btn = (Button) v.findViewById(R.id.listview_button);
        }
    }
}
