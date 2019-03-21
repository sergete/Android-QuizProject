package urjc.android.jms_ss.quizproject.java_class;

import android.app.Activity;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import urjc.android.jms_ss.quizproject.activities.QuizActivity;

@Entity(tableName = "questions")
public class Question {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_id")
    private int question_id;

    @NonNull
    @ColumnInfo(name = "question_text")
    private String questionText;
    @NonNull
    @ColumnInfo(name = "question_a1")
    private String a1;
    @NonNull
    @ColumnInfo(name = "question_a2")
    private String a2;
    @NonNull
    @ColumnInfo(name = "question_a3")
    private String a3;
    @NonNull
    @ColumnInfo(name = "question_a4")
    private String a4;
    @NonNull
    @ColumnInfo(name = "question_correctAnswer")
    private int correctAnswer;
    @ColumnInfo(name = "quesion_img")
    private int questionImg;
    @NonNull
    @ColumnInfo(name = "question_controlSys")
    private String controlSys;
    @ColumnInfo(name = "question_mediaId")
    private Integer mediaId;
    @ColumnInfo(name = "question_soundId")
    private Integer soundId;
    @ColumnInfo(name = "question_uri")
    private String uri;

    //para listView
    @ColumnInfo(name = "question_answersId1")
    private Integer answersId1;
    @ColumnInfo(name = "question_answersId2")
    private Integer answersId2;
    @ColumnInfo(name = "question_answersId3")
    private Integer answersId3;
    @ColumnInfo(name = "question_answersId4")
    private Integer answersId4;

    /**
     * Constructor vacio para la creacion de preguntas desde la base de datos
     */
    public Question() {

    }

    /**
     * @param activity
     * @param question
     * @param answers
     * @param correctAnswer
     * @param questionImg
     * @param questionVid
     * @param questionSound
     * @param controlSys
     * @param images
     * Constructor global todo
     */
    public Question(Activity activity, String question, String[] answers, int correctAnswer, int questionImg,
                     int questionVid, int questionSound, String controlSys, @Nullable Integer[] images) {
        this.questionText = question;
        this.a1 = answers[0];
        this.a2 = answers[1];
        this.a3 = answers[2];
        this.a4 = answers[3];
        this.correctAnswer = correctAnswer;
        this.questionImg = questionImg;
        this.mediaId = questionVid;
        this.soundId = questionSound;
        this.controlSys = controlSys;
        this.uri = ("android.resource://"+ activity.getApplication().getPackageName() +"/"+questionVid);
        if(images == null){
            this.answersId1 = null;
            this.answersId2 = null;
            this.answersId3 = null;
            this.answersId4 = null;

        }
        else{
            this.answersId1 = images[0];
            this.answersId2 = images[1];
            this.answersId3 = images[2];
            this.answersId4 = images[3];
        }
    }

    /**
     * Constructor para una pregunta con imagen
     * @param text
     * @param c
     * @param questionImg
     */
    @Ignore
    public Question(String text, String c, int questionImg) {
        this.questionText = text;
        this.questionImg = questionImg;
        this.controlSys = c;
        this.correctAnswer = -1;
    }

    /**
     * Constructor para una pregunta sin imagen
     * @param text
     * @param c
     */
    @Ignore
    public Question(String text, String c) {
        this.questionText = text;
        this.questionImg = -1;
        this.controlSys = c;
        this.correctAnswer = -1;
    }

    /**
     * Anade el texto de las respuestas de la pregunta
     * @param a1
     * @param a2
     * @param a3
     * @param a4
     * @param correct indica el indice de la respuesta correcta, (siendo 1 el equivalente a a1, 2 el equivalente a a2, etc)
     */
    @Ignore
    public void addAnswers(String a1, String a2, String a3, String a4, int correct) {
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.correctAnswer += correct;
    }

    /**
     * Anade las imagenes asociadas a las respuestas
     * @param respuestas String con las respuestas
     * @param imid identificador (int) del indice de la respuesta (R.drawable.foo)
     * @param correct indica el indice de la respuesta correcta, (siendo 1 el equivalente a a1, 2 el equivalente a a2, etc)
     */
    @Ignore
    public void addImages(String [] respuestas, Integer[] imid, int correct)
    {
        this.a1 = respuestas[0];
        this.a2 = respuestas[1];
        this.a3 = respuestas[2];
        this.a4 = respuestas[3];
        this.answersId1 = imid[0];
        this.answersId2 = imid[1];
        this.answersId3 = imid[2];
        this.answersId4 = imid[3];

        this.correctAnswer += correct;
    }

    /**
     * Proporciona el contexto de la actividad donde se mostrara la listview
     * @param ctx
     *
    @Ignore
    public void addCustomListView(Activity ctx) {
        String[] respuestas = {a1, a2, a3, a4};
        Integer[] imid = {answersId1, answersId2, answersId3, answersId4};
        clv = new CustomListView(ctx, respuestas, imid);
    }
    */

    /**
     * Devuelve true si la pregunta tiene una imagen
     * @return true si existe una imagen en la pregunta
     */
    @Ignore
    public boolean hasImage() {
        return questionImg != -1;
    }

    /**
     * Devuelve el texto de la respuesta en funcion de su indice
     * @param position
     * @return
     */
    @Ignore
    public String getAnswerAtPosition(int position) {
        String [] answers = {a1, a2, a3, a4 };
        return answers[position];
    }

    /**
     * Devuelve un array de string con las respuestas
     * @return
     */
    @Ignore
    public String[] getAnswers() {
        String [] answers = {a1, a2, a3, a4 };
        return answers;
    }

    /**
     *
     * @param index
     * @return una pregunta del array de preguntas
     */
    @Ignore
    public String getAnswers(int index) {
        String [] answers = {a1, a2, a3, a4 };
        return answers[index];
    }

    /**
     * Devuleve el identificador de las imagenes de las respuestas.
     * @return
     */
    @Ignore
    public Integer[] getAnswersId(){
        Integer[] answersId = {answersId1, answersId2, answersId3, answersId4};
        return answersId;
    }

    /**
     * Devuelve el texto de la respuesta correcta
     * @return
     */
    @Ignore
    public String getCorrectAnswerText() {
        String [] answers = {a1, a2, a3, a4 };
        return answers[correctAnswer];
    }

    //  Getters
    @NonNull
    public int getQuestion_id() {
        return question_id;
    }

    @NonNull
    public String getQuestionText() {
        return questionText;
    }

    @NonNull
    public String getA1() {
        return a1;
    }

    @NonNull
    public String getA2() {
        return a2;
    }

    @NonNull
    public String getA3() {
        return a3;
    }

    @NonNull
    public String getA4() {
        return a4;
    }

    @NonNull
    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public int getQuestionImg() {
        return questionImg;
    }

    @NonNull
    public String getControlSys() {
        return controlSys;
    }

    public Integer getAnswersId1() {
        return answersId1;
    }

    public Integer getAnswersId2() {
        return answersId2;
    }

    public Integer getAnswersId3() {
        return answersId3;
    }

    public Integer getAnswersId4() {
        return answersId4;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public Integer getSoundId() {return soundId;}

    public String getUri() {return uri;}

    @Ignore
    public Uri getUritoLoad(){return Uri.parse(uri);}

    /*
    @Ignore
    public CustomListView getCustomListView(){return clv;}
    */

    //  Setters
    public void setQuestion_id(@NonNull int question_id) {
        this.question_id = question_id;
    }

    public void setQuestionText(@NonNull String questionText) {
        this.questionText = questionText;
    }

    public void setA1(@NonNull String a1) {
        this.a1 = a1;
    }

    public void setA2(@NonNull String a2) {
        this.a2 = a2;
    }

    public void setA3(@NonNull String a3) {
        this.a3 = a3;
    }

    public void setA4(@NonNull String a4) {
        this.a4 = a4;
    }

    public void setCorrectAnswer(@NonNull int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setQuestionImg(int questionImg) {
        this.questionImg = questionImg;
    }

    public void setControlSys(@NonNull String controlSys) {
        this.controlSys = controlSys;
    }

    public void setAnswersId1(Integer answersId1) {
        this.answersId1 = answersId1;
    }

    public void setAnswersId2(Integer answersId2) {
        this.answersId2 = answersId2;
    }

    public void setAnswersId3(Integer answersId3) {
        this.answersId3 = answersId3;
    }

    public void setAnswersId4(Integer answersId4) {
        this.answersId4 = answersId4;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public void setSoundId(Integer soundId) {
        this.soundId = soundId;
    }



    public void setUri(String uri) {
        this.uri = uri;
    }
}
