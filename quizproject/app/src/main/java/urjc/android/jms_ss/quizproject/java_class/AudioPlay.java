package urjc.android.jms_ss.quizproject.java_class;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class AudioPlay {
    public static MediaPlayer mediaPlayer = null;
    public static MediaPlayer mediaPlayerSounds = null;
    public static boolean activatedSound = true;

    public static void playAudio(Context c,int id){
        mediaPlayer = MediaPlayer.create(c,id);
        if(!mediaPlayer.isPlaying() && !activatedSound)
        {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }

    public static void playAudioSounds(Context c,int id){
        mediaPlayerSounds = MediaPlayer.create(c,id);
        if(!mediaPlayerSounds.isPlaying() && mediaPlayerSounds != null)
        {
            mediaPlayer.setLooping(false);
            mediaPlayerSounds.start();
        }
    }

    public static void ReplayAudioSounds(){
        if(!mediaPlayerSounds.isPlaying() && mediaPlayerSounds != null)
        {
            mediaPlayerSounds.start();
        }
    }

    public static void stopSound(){
        if(mediaPlayerSounds != null && mediaPlayerSounds.isPlaying())
            mediaPlayerSounds.stop();
    }

    public static void stopAudio(){
        if(!activatedSound)
            mediaPlayer.stop();
    }
}
