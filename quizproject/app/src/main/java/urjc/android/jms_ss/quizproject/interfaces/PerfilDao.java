package urjc.android.jms_ss.quizproject.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import urjc.android.jms_ss.quizproject.java_class.Perfil;

@Dao
public interface PerfilDao {

    @Query("SELECT * FROM perfiles WHERE user_id=:userId")
    public Perfil getUserById(int userId);

    @Query("SELECT * FROM perfiles WHERE user_name=:userName LIMIT 1")
    public Perfil getUserById(String userName);

    @Query("SELECT * FROM perfiles")
    public List<Perfil> getAllPerfiles();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void AddPerfil(Perfil... perfil);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void UpdatePerfil(Perfil... perfil);

    @Delete
    public void DeletePerfil(Perfil... perfil);

    @Query("DELETE FROM perfiles")
    public void DeleteAllPerfil();

    @Query("select *\n" +
            "from perfiles\n" +
            //"where user_max_punt = (SELECT max(user_max_punt) FROM perfiles)\n" +
            "order by user_max_punt desc\n" +
            "limit 10")
    public List<Perfil> getHighScorePerfil();

}
