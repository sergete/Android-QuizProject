package urjc.android.jms_ss.quizproject.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import urjc.android.jms_ss.quizproject.java_class.Question;

@Dao
public interface QuestionDao {

    /**
     * Devuelve el identificador del primer elemento de la tabla de preguntas
     * @return
     */
    @Query("SELECT COUNT(question_id) FROM questions")
    public int hasFirstElement();

    /**
     * Devuelve la pregunta segun su indice
     * @param questionId
     * @return
     */
    @Query("SELECT * FROM questions WHERE question_id=:questionId")
    public Question getQuestionById(int questionId);

    /**
     * Devuleve todas las preguntas
     * @return
     */
    @Query("SELECT * FROM questions")
    public List<Question> getAllQuestions();

    /**
     * Anade una pregunta a la base de datos.
     * @param question
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addQuestion(Question... question);

    /**
     * Actualiza la pregunta en la base de datos.
     * @param Question
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void updateQuestion(Question... Question);

    /**
     * Borra la pregunta de la base de datos.
     * @param question
     */
    @Delete
    public void deleteQuestion(Question... question);

    /**
     * Borra todas las preuntas de la base de datos.
     */
    @Query("DELETE FROM questions")
    public void deleteAllQuestions();
}
