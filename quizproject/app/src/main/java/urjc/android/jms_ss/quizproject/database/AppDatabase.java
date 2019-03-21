package urjc.android.jms_ss.quizproject.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import java.util.List;

import urjc.android.jms_ss.quizproject.interfaces.PerfilDao;
import urjc.android.jms_ss.quizproject.interfaces.QuestionDao;
import urjc.android.jms_ss.quizproject.java_class.Converters;
import urjc.android.jms_ss.quizproject.java_class.Perfil;
import urjc.android.jms_ss.quizproject.java_class.Question;

import static urjc.android.jms_ss.quizproject.database.AppDatabase.DATABASE_VERSION;

@Database(entities = {Perfil.class, Question.class},version = DATABASE_VERSION, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public static final int DATABASE_VERSION = 30;
    public static final String DATABASE_NAME = "jms.db";
    public abstract PerfilDao perfilDao();
    public abstract QuestionDao questionDao();

    public static AppDatabase mInstance = null;

    public static AppDatabase getmInstance(Context context){
        if(mInstance == null){
            synchronized (AppDatabase.class) {
                mInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                        //Permite ejecutarse en varios threads, no es recomendable si vas a usarlo en producción
                        .allowMainThreadQueries()
                        //FALLBACK destruye la database_version anterior cuando cambiamos de versión
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return mInstance;
    }

    public  void insertPerfil(Perfil perfil){
        perfilDao().AddPerfil(perfil);
    }

    public  void DeletePerfil(Perfil perfil){
        perfilDao().DeletePerfil(perfil);
    }

    public  void DeleteAllPerfil(){
        perfilDao().DeleteAllPerfil();
    }

    public  void UpdatePerfil(Perfil perfil){
        perfilDao().UpdatePerfil(perfil);
    }

    public  List<Perfil> getAllPerfiles(){
        return perfilDao().getAllPerfiles();
    }

    public List<Perfil> getHighScorePerfiles() {return perfilDao().getHighScorePerfil();}

}
