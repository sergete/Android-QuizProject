package urjc.android.jms_ss.quizproject.activities;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import urjc.android.jms_ss.quizproject.R;
import urjc.android.jms_ss.quizproject.java_class.GameManager;


public class MultimediaSolutionActivity extends AppCompatActivity implements View.OnTouchListener {

    private VideoView vd;
    private MediaController mc;
    private Uri uri;
    private boolean finished;

    private GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia_solution);

        gameManager = GameManager.getInstancia(this);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        finished = false;

        prepareVideoSettings();
        showVideo();
    }

    private void prepareVideoSettings(){
        vd = findViewById(R.id.act_ms_videoview);

        mc = new MediaController(this);
        uri = gameManager.getQuestion().getUritoLoad();

        //vd.setOnTouchListener(this);
        vd.setOnTouchListener(this);

        mc.setAnchorView(vd);
        vd.setMediaController(mc);
        vd.setVideoURI(uri);
        vd.requestFocus();
    }

    private void showVideo()
    {
        vd.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "Video completed", Toast.LENGTH_SHORT).show();
                finished = true;
            }
        });

        vd.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                vd.start();
            }
        });

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (finished) {
            finish();
            return true;
        }
        else
            return true;
    }
}
