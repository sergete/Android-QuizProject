package urjc.android.jms_ss.quizproject.java_class;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import urjc.android.jms_ss.quizproject.database.AppDatabase;

public class GameManager {

    //  Instancia singleton
    private static GameManager instancia = null;

    //  Configuracion
    private Configuration config;
    private final String configFileName = "settings.json";

    //Perfil
    private Perfil perfil;

    //  Variables sobre las preguntas
    private int numeroPreguntas;
    private String tipoPreguntas;
    private String dificultadPreguntas;
    private boolean juegoEmpezado;
    private int indicePregunta;
    private List<Question> preguntas;

    //  Variables del juagador
    private String nombre;
    private long tiempoUltimaPartida;
    private int puntuacion;

    //  Acceso a la base de datos
    private AppDatabase db;

    /**
     *
     * @param act
     */
    private GameManager(Activity act) {
        Context ctx = act.getApplicationContext();

        numeroPreguntas = 3;

        config = new Configuration();
        if(!config.readJSON(ctx, configFileName)) {
            config.writeJson(ctx, configFileName);
        }
        tipoPreguntas = config.getGameMode();
        dificultadPreguntas = config.getDifficulty();

        db = AppDatabase.getmInstance(ctx);
        if(db.questionDao().hasFirstElement() < 1) {
            new QuestionCreator().create(db, act);
        }

        if(config.getPlayerId() != -1){
            perfil = db.perfilDao().getUserById(config.getPlayerId());
        }
        else{
            perfil = null;
        }

    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public AppDatabase getDb() {
        return db;
    }

    public Configuration getConfig() {
        return config;
    }

    public int getIndicePregunta() {
        return indicePregunta;
    }

    public int getNumeroPreguntas() {
        return numeroPreguntas;
    }

    /**
     *  Crea una instancia del objeto GameManager en caso de que no este creada, si ya ha sido
     *  creada entonces la devuelve. Patron singleton del objeto.
     * @param activity Es necesario proporcionar un contexto para poder inicializar la base de datos.
     * @return instancia de GameManager
     */
    public static GameManager getInstancia(Activity activity) {
        if(instancia == null) {
            instancia = new GameManager(activity);
        }
        return instancia;
    }

    /**
     * Configura la dificultad de las preguntas y si son de tipo multimedia o no
     * @param tipoPreguntas
     * @param dificultadPreguntas
     */
    public void configurarJuego(Context ctx, String tipoPreguntas, String dificultadPreguntas) {
        config.setDifficulty(dificultadPreguntas);
        config.setGameMode(tipoPreguntas);
        config.writeJson(ctx, configFileName);
        this.tipoPreguntas = tipoPreguntas;
        this.dificultadPreguntas = dificultadPreguntas;
    }

    /**
     * Empieza una nueva partida, cogiendo las preguntas de la base de datos.
     */
    public void empezarJuego(){
        if(!juegoEmpezado) {
            juegoEmpezado = true;
            indicePregunta = 0;
            puntuacion = 0;
            tiempoUltimaPartida = 0;
            preguntas = preguntasAleatorias();
        }
    }

    /**
     * Devuleve si el prefil esta seleccionado
     * @return
     */
    public boolean tienePerfil() {
        return config.getPlayerId() > -1;
    }


    /**
     * Mete el perfil en la configuracion
     * @param perfilId
     * @param name
     */
    public void seleccionarPerfil(int perfilId, String name) {
        config.setPlayerId(perfilId);
        nombre = name;
    }

    /**
     * Genera preguntas aleatorias de entre todas las preguntas
     * @return una lista con las preguntas que utilizaran durante la partida.
     */
    private List<Question> preguntasAleatorias() {
        List<Question> preguntasDb = db.questionDao().getAllQuestions();
        Collections.shuffle(preguntasDb);

        switch (dificultadPreguntas) {
            case "FACIL":
                numeroPreguntas = 5;
                break;
            case "MEDIO":
                numeroPreguntas = 10;
                break;
            case "DIFICIL":
                numeroPreguntas = 15;
                break;
        }

        List<Question> typeList = new ArrayList<Question>(40);
        switch (tipoPreguntas) {
            case "MULTIMEDIA":
                tipoPreguntas = "MULTIMEDIA";
                for(int i = 0; i<preguntasDb.size(); ++i){
                    if(preguntasDb.get(i).getControlSys().equals("MULTIMEDIA") || preguntasDb.get(i).getControlSys().equals("SOUND"))
                    {
                        typeList.add(preguntasDb.get(i));
                    }
                }
                break;
            case "AZAR":
                tipoPreguntas = "AZAR";
                break;
        }

        if(typeList.size() == 0){
            List<Question> returnList = new ArrayList<Question>(numeroPreguntas);
            for(int i = 0; i < numeroPreguntas; i++) {
                returnList.add(preguntasDb.get(i));
            }
            return returnList;
        }
        else{
            List<Question> returnList = new ArrayList<Question>(numeroPreguntas);
            for(int i = 0; i < numeroPreguntas; i++) {
                returnList.add(typeList.get(i));
            }
            return returnList;
        }
    }

    /**
     * Devuelve true si el juego se ha terminado, ademas fija la variable de juego empezado a false
     * @return
     */
    public boolean haTerminado() {
        if(indicePregunta >= numeroPreguntas) {
            juegoEmpezado = false;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * termina el juego en caso de terminarlo antes de tiempo
     */
    public void terminarJuego(){
        juegoEmpezado = false;
    }

    /**
     * Anade la puntuacion proporcionada al jugador
     * @param puntos numero de puntos a anadir o quitar
     * @return el numero total de puntos del jugador
     */
    public int incrementarPuntuacion(int puntos) {
        puntuacion += puntos;
        if(puntuacion < 0) {
            puntuacion = 0;
        }
        return puntuacion;
    }

    /**
     * Dado un string con la respuesta, indica si se trata de la respuesta correcta a la pregunta
     * @param respuesta
     * @return true si es correcta
     */
    public boolean esCorrectaLaPregunta(String respuesta) {
        return respuesta.equals(preguntas.get(indicePregunta).getCorrectAnswerText());
    }

    /**
     * Devuelve un string con la respuesta correcta a la pregunta
     * @return
     */
    public String getRespuestaCorrecta() {
        return preguntas.get(indicePregunta).getCorrectAnswerText();
    }

    /**
     * Devuelve el texto principal de la pregunta
     * @return
     */
    public String getTextoPregunta() {
        return preguntas.get(indicePregunta).getQuestionText();
    }

    /**
     * Devuelve un array de string con las respuestas a la pregunta
     * @return
     */
    public String [] getAnswers() {
        return preguntas.get(indicePregunta).getAnswers();
    }

    /**
     * Indica que se pasa a la siguiente pregunta
     */
    public void incrementarIndicePregunta() {
        indicePregunta++;
    }

    /**
     * Si el texto principal de la pregunta requiere una imagen devuelve true
     * @return
     */
    public boolean tieneImagenLaPregunta() {
        return preguntas.get(indicePregunta).hasImage();
    }

    /**
     * Devuelve la imagen hacia la que hace referencia la pregunta
     * @return
     */
    public int getImagenDeLaPregunta() {
        return preguntas.get(indicePregunta).getQuestionImg();
    }

    /**
     * Devuelve un sistema de control correspondiente
     * @return ( RADIO_BUTTON | SPINNER | LIST_VIEW | MULTIMEDIA | SOUND )
     */
    public String getSistemaDeControlDeLaPregunta() {
        return preguntas.get(indicePregunta).getControlSys();
    }

    /**
     * Devuelve el nombre del jugador que este activo en este momento
     * @return nombre del jugador activo
     */
    public String getNombreJugador() {
        return nombre;
    }

    /**
     * Proporciona un nombre para el jugador que se mostrara durante la duracion del juego
     * @param name
     */
    public void setNombreJugador(String name) {
        this.nombre = name;
    }

    /**
     * Proporciona el tiempo que ha durado la partida en milisegundos
     * @param tiempo
     */
    public void setDuracionPartida(long tiempo) {
        tiempoUltimaPartida = tiempo;
    }

    /**
     * Devuelve el tiempo que duro la ultima partida en milisegundos
     * @return
     */
    public long duracionUltimaPartida() {
        return tiempoUltimaPartida;
    }

    /**
     * Devuelve la puntuacion actual del jugador
     * @return puntuacion del jugador
     */
    public int getPuntuacionJugador() {
        return puntuacion;
    }

    /**
     * Devuelve true si el jugador ha obtenido al menos la mitad de la puntuacion total
     * @return
     */
    public boolean mostrarEnhorabuena() {
        return puntuacion >= (numeroPreguntas*20) / 2;
    }

    /**
     * devuelve la pregunta con todos sus elementos para cargarla en el fragmento
     * @return
     */
    public Question getQuestion(){
        return preguntas.get(indicePregunta);
    }
}
