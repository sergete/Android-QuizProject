package urjc.android.jms_ss.quizproject.java_class;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import urjc.android.jms_ss.quizproject.R;

public class Questions {

    private String question;
    private String [] answers;
    private List<Integer> images;
    private int correctAnswer;
    private int questionImg;
    private int questionVid;
    private int questionSound;
    private Uri uri;
    private String controlSys;

    public Questions() {
    }

    public Questions(Activity activity, String question, String[] answers, int correctAnswer, int questionImg,
                     int questionVid, int questionSound, String controlSys, @Nullable List<Integer> images) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.questionImg = questionImg;
        this.questionVid = questionVid;
        this.questionSound = questionSound;
        this.controlSys = controlSys;
        this.uri = Uri.parse("android.resource://"+ activity.getApplication().getPackageName() +"/"+questionVid);
        if(images == null){
            this.images = null;
        }
        else{
            this.images = new ArrayList<>(4);
            this.images.addAll(images);
        }
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public String getAnswers(int index) {
        return answers[index];
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getQuestionImg() {
        return questionImg;
    }

    public void setQuestionImg(int questionImg) {
        this.questionImg = questionImg;
    }

    public int getQuestionVid() {
        return questionVid;
    }

    public void setQuestionVid(int questionVid) {
        this.questionVid = questionVid;
    }

    public int getQuestionSound() {
        return questionSound;
    }

    public void setQuestionSound(int questionSound) {
        this.questionSound = questionSound;
    }

    public String getControlSys() {
        return controlSys;
    }

    public void setControlSys(String controlSys) {
        this.controlSys = controlSys;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public List<Integer> getImages() {
        return images;
    }

    public void setImages(List<Integer> images) {
        this.images = images;
    }

    public String getAnswer(){return answers[correctAnswer];}
}
