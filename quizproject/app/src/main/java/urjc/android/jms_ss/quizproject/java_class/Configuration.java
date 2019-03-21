package urjc.android.jms_ss.quizproject.java_class;

import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Configuration {

    String difficulty;
    String gameMode;
    int playerId;

    public Configuration() {
        difficulty = "EASY";
        gameMode = "AZAR";
        playerId = -1;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    private void setFromJSON(String cadenaJson) {
        try {
            JSONObject object = new JSONObject(cadenaJson);
            difficulty = object.getString("difficulty");
            gameMode = object.getString("gameMode");
            playerId = object.getInt("playerId");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean readJSON(Context ctx, String fileName) {
        try{
            FileInputStream fi = ctx.openFileInput(fileName);
            InputStreamReader miReader = new InputStreamReader(fi);
            BufferedReader miBuffer = new BufferedReader(miReader);

            String line;
            StringBuffer file = new StringBuffer();
            String newLine = "";

            while ((line = miBuffer.readLine()) != null)
            {
                file.append(newLine + line);
                newLine = "\n";
            }

            setFromJSON(file.toString());

            fi.close();
        }
        catch (IOException e){
            return false;
        }
        return true;
    }

    public void writeJson(Context ctx, String fileName) {
        try {
            FileOutputStream fi = ctx.openFileOutput(fileName, ctx.MODE_PRIVATE);
            OutputStreamWriter osw= new OutputStreamWriter(fi);
            osw.write(toJSON());
            osw.flush();
            osw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private String toJSON() {
        return "{ difficulty : " + difficulty + ", gameMode : " + gameMode +  ", playerId : " + playerId + " }";
    }

}
