package urjc.android.jms_ss.quizproject.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


import urjc.android.jms_ss.quizproject.R;
import urjc.android.jms_ss.quizproject.java_class.YoutubeConfig;

public class PreviewActivity extends YouTubeBaseActivity {

    private static final String TAG = "Preview Activity";
    int PERMISSION_REQUEST_CODE_READ_EXTERNAL_STORAGE = 100;

    YouTubePlayerView video;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    YouTubePlayer.PlayerStateChangeListener videoListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        requestPermission();

        video= findViewById(R.id.preview_video);

        videoListener = new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoaded(String s) {

            }

            @Override
            public void onAdStarted() {

            }

            @Override
            public void onVideoStarted() {

            }

            @Override
            public void onVideoEnded() {
                //Cuando el video termina pasamos a la siguiente pantalla
                Intent intent = new Intent(getApplicationContext(), InterfaceActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {

            }
        };

        //Listener para el video cuando se inicializa
        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                //invocamos al listener videoListener para saber cuando empieza el video termina, etc.
                youTubePlayer.setPlayerStateChangeListener(videoListener);
                //Pantalla completa
                youTubePlayer.setFullscreen(true);
                //Cargamos video de youtube https://www.youtube.com/watch?v=oM1poedvNJc pero solo nos quedamos con lo siguiente al =
                youTubePlayer.loadVideo("oM1poedvNJc");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "Failure to initialize Video");
            }
        };

        Log.d(TAG, "Initializing Video");

        //Inicializa el Listener y le da la clave para ejecutarlo
        video.initialize(YoutubeConfig.getApiKey(), mOnInitializedListener);

        /*Intent intent = new Intent(getApplicationContext(), InterfaceActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);*/
    }

    public void requestPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE_READ_EXTERNAL_STORAGE);
        }
    }
}
