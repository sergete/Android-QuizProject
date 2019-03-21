package urjc.android.jms_ss.quizproject.java_class;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "perfiles")
public class Perfil {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    private int user_id;
    @ColumnInfo(name = "user_photo")
    private String user_photo;
    @ColumnInfo(name = "user_name")
    private String user_name;
    @ColumnInfo(name = "user_max_punt")
    private int user_max_punt;
    @ColumnInfo(name = "user_num_partidas")
    private int user_num_partidas;
    @ColumnInfo(name = "user_last_play")
    private Date last_play;

    public Perfil() {
    }

    @Ignore
    public Perfil(String user_name, String photo) {
        this.user_photo = photo;
        this.user_name = user_name;
        this.user_max_punt = 0;
        this.user_num_partidas = 0;
        this.last_play = Calendar.getInstance().getTime();
    }

    @NonNull
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(@NonNull int user_id) {
        this.user_id = user_id;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_max_punt() {
        return user_max_punt;
    }

    public void setUser_max_punt(int user_max_punt) {
        this.user_max_punt = user_max_punt;
    }

    public int getUser_num_partidas() {
        return user_num_partidas;
    }

    public void setUser_num_partidas(int user_num_partidas) {
        this.user_num_partidas = user_num_partidas;
    }

    public Date getLast_play() {
        return this.last_play;
    }

    public void setLast_play(Date last_play) {
        this.last_play = last_play;
    }

    @Override
    public String toString(){
        return new StringBuilder("Nombre de Usuario: " + user_name).append("\n").append(" Máxima Puntuación: " + String.valueOf(user_max_punt)).append("\n").append(" Numero de partidas: " + String.valueOf(user_num_partidas))
                .append("\n Ultima partida: " + last_play.toString()).toString();
    }

    @Ignore
    public static String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    @Ignore
    public static Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }

    @Ignore
    public void incrementNumPartidas(){
        this.user_num_partidas++;
    }
}
