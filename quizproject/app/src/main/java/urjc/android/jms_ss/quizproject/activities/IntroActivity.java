package urjc.android.jms_ss.quizproject.activities;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.support.v4.content.ContextCompat;

import urjc.android.jms_ss.quizproject.R;

import static urjc.android.jms_ss.quizproject.java_class.AudioPlay.stopAudio;

public class IntroActivity extends AppCompatActivity {

    TextInputLayout playerLayout;
    TextInputEditText playerName;
    public Button button_config, button_volver;

    public void init(){

        button_config = (Button)findViewById(R.id.frg_btn_cu_config);
        button_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(IntroActivity.this, ConfigurationActivity.class);
                    //liberan las activities y podemos cerrar la aplicación sin problemas
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
            }
        });

        button_volver = (Button)findViewById(R.id.btn_introVolver);
        button_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, InterfaceActivity.class);
                //liberan las activities y podemos cerrar la aplicación sin problemas
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        init();
    }
}

