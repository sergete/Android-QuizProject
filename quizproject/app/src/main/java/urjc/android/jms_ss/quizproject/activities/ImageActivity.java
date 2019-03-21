package urjc.android.jms_ss.quizproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import urjc.android.jms_ss.quizproject.R;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        init();
    }

    private void init() {


        ImageView img = (ImageView) findViewById(R.id.openImage);
        Bundle b = getIntent().getExtras();
        int value = -1;
        if(b != null) {
            value = b.getInt("img");
            img.setImageResource(value);
        }

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
