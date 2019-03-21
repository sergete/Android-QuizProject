package urjc.android.jms_ss.quizproject.java_class;

import android.app.Activity;

import urjc.android.jms_ss.quizproject.R;
import urjc.android.jms_ss.quizproject.database.AppDatabase;

public class QuestionCreator {

    /**
     * Constructor sin parametros
     */
    public QuestionCreator() {

    }

    /**
     * Crea las preguntas y las mete en la base de datos.
     * @param db Base de datos donde se guardaran las preguntas
     * @param activity para crear la Uri en la clase question para cargar
     * los recursos necesarios
     */
    public void create(AppDatabase db, Activity activity) {
        Question q;

        /*
        q = new Question("¿En qué año se lanzó clash royale?", "RADIO_BUTTON");
        q.addAnswers("2012", "2016", "2014", "2017", 2);
        db.questionDao().addQuestion(q);

        q = new Question("¿Cuánto elixir cuesta esta carta?", "SPINNER", R.drawable.balloon);
        q.addAnswers("5", "3", "9", "8", 1);
        db.questionDao().addQuestion(q);

        q = new Question("¿Cómo se llama esta carta?", "RADIO_BUTTON", R.drawable.bowler);
        q.addAnswers("Minero", "Bruja", "Mago", "Lanzarrocas", 4);
        db.questionDao().addQuestion(q);

        q = new Question("¿Cuál ha sido la última carta añadida?", "LIST_VIEW");
        String[] answers = {"Reclutas Reales", "Megacaballero", "Duende Gigante", "Puercos Reales"};
        Integer[] images = {R.drawable.royalrecruits, R.drawable.megacaballero, R.drawable.goblin_giant, R.drawable.royalhogscard};
        q.addImages(answers,images,3);
        db.questionDao().addQuestion(q);

        q = new Question("¿Qué carta hace más daño?", "LIST_VIEW");
        String [] ans = {"Ejército de esqueletos", "Chispitas", "Pekka", "Trío de Mosqueteras"};
        Integer []im = {R.drawable.ejercito_de_esqueletos, R.drawable.chispitas, R.drawable.pekka, R.drawable.trio_de_mosqueteras};
        q.addImages(ans, im,1);
        db.questionDao().addQuestion(q);
        */
        String multimedia = "MULTIMEDIA";
        String video = "SOUND";
        String radio = "RADIO_BUTTON";
        String spinner = "SPINNER";
        String listview = "LIST_VIEW";


        //MULTIMEDIA QUESTIONS

        int r = R.raw.question1;
        String [] answers = {"40","35","45","50"};
        Question q1 = new Question(activity, "Cual es el daño de la bola de hielo a torre con reglas de torneo?", answers, 2,
                -1, r, -1, multimedia, null);
        db.questionDao().addQuestion(q1);

        r = R.raw.question2;
        answers = new String[]{"Golem de hielo", "Esqueletos", "Espíritu de hielo", "Montapuercos"};
        Question q2 = new Question(activity,"Cual es la carta con menos daño por segundo del juego?", answers, 0,
                -1, r, -1, multimedia, null);
        db.questionDao().addQuestion(q2);

        r = R.raw.question3;
        answers = new String[]{"Mosquetera", "Máquina voladora", "No disparan", "Misma distancia"};
        Question q3 = new Question(activity,"Cual tiene más alcance la mosquetera o máquina voladora?", answers, 3,
                -1, r, -1, multimedia, null);
        db.questionDao().addQuestion(q3);

        r = R.raw.question4;
        answers = new String[]{"4000", "4008", "4829", "4256"};
        Question q4 = new Question(activity,"Cuanta vida tiene la torre del rey a reglas de torneo?", answers, 1,
                -1, r, -1, multimedia, null);
        db.questionDao().addQuestion(q4);

        r = R.raw.question5;
        answers = new String[]{"4", "6", "2", "8"};
        Question q5 = new Question(activity,"Cuantas cartas hay con coste 2?", answers, 3,
                -1, r, -1, multimedia, null);
        db.questionDao().addQuestion(q5);

        r = R.raw.question6;
        answers = new String[]{"2.5","3","2.7","2"};
        Question q6 = new Question(activity, "Cuanto tiempo dura la curación? (Segundos)", answers, 0,
                -1, r, -1, multimedia, null);
        db.questionDao().addQuestion(q6);

        r = R.raw.question7;
        answers = new String[]{"8", "10", "12", "14"};
        Question q7 = new Question(activity,"Cuantos duendes salen de la choza de duendes en total?", answers, 2,
                -1, r, -1, multimedia, null);
        db.questionDao().addQuestion(q7);

        r = R.raw.question8;
        answers = new String[]{"1", "4", "2", "3"};
        Question q8 = new Question(activity,"Cuantos globos tiene el barril de esqueletos?", answers, 3,
                -1, r, -1, multimedia, null);
        db.questionDao().addQuestion(q8);

        r = R.raw.question9;
        answers = new String[]{"4", "8", "5", "3"};
        Question q9 = new Question(activity,"Cuantos reclutas reales aparecen en la imágen de su carta?", answers, 3,
                -1, r, -1, multimedia, null);
        db.questionDao().addQuestion(q9);

        r = R.raw.question10;
        answers = new String[]{"pelo gris y cejas negras", "pelo negro y cejas gris", "es calvo", "pelo y cejas grises"};
        Question q10 = new Question(activity,"De que color tiene el pelo y las cejas el arquero mágico?", answers, 0,
                -1, r, -1, multimedia, null);
        db.questionDao().addQuestion(q10);

        r = R.raw.question11;
        answers = new String[]{"negros","verdes","los tiene tapados","marrones"};
        Question q11 = new Question(activity, "De que color tiene los ojos el cazador?", answers, 2,
                -1, r, -1, multimedia, null);
        db.questionDao().addQuestion(q11);

        r = R.raw.question12;
        answers = new String[]{"verdes", "marrones", "negros", "no tiene"};
        Question q12 = new Question(activity,"De que color tiene los ojos la princesa?", answers, 1,
                -1, r, -1, multimedia, null);
        db.questionDao().addQuestion(q12);

        r = R.raw.question13;
        answers = new String[]{"igual", "bandida", "barril de esqueletos", "no atacan a torre"};
        Question q13 = new Question(activity,"que tropa hace más daño si no se defiende, bandida o barril esqueletos?", answers, 2,
                -1, r, -1, multimedia, null);
        db.questionDao().addQuestion(q13);

        r = R.raw.question14;
        answers = new String[]{"las dos", "izquierda", "derecha", "ninguna"};
        Question q14 = new Question(activity,"Que ceja tiene levantada el Mago Eléctrico?", answers, 1,
                -1, r, -1, multimedia, null);
        db.questionDao().addQuestion(q14);

        r = R.raw.question15;
        answers = new String[]{"leñador", "igual puntuación", "fantasma real", "no tienen vida"};
        Question q15 = new Question(activity,"Quien tiene más puntos de vida el leñador o el fantasma real?", answers, 2,
                -1, r, -1, multimedia, null);
        db.questionDao().addQuestion(q15);

        //SOUNDS QUESTIONS

        r = R.raw.esqueleto_gigante_sound;
        answers = new String[]{"Barbaros", "Esqueleto gigante", "Globo bombástico", "Esqueletos"};
        Question q17 = new Question(activity,"A quién pertenece éste sonido?", answers, 1,
                -1, r, R.raw.esqueleto_gigante_sound_mp3, video, null);
        db.questionDao().addQuestion(q17);

        r = R.raw.barbaros_sound;
        answers = new String[]{"Barbaros", "Valkiria", "fantasma real", "Bárbaros de élite"};
        Question q16 = new Question(activity,"A quién pertenece éste sonido?", answers, 0,
                -1, r, R.raw.barbaros_sound_mp3, video, null);
        db.questionDao().addQuestion(q16);

        r = R.raw.goblin_barrel_sound;
        answers = new String[]{"Duendes", "Duendes con lanza", "Barril de duendes", "Pandilla de duendes"};
        Question q18 = new Question(activity,"A quién pertenece éste sonido?", answers, 2,
                -1, r, R.raw.goblin_barrel_sound_mp3, video, null);
        db.questionDao().addQuestion(q18);

        r = R.raw.hog_rider_sound;
        answers = new String[]{"Barril de duendes", "Valkiria", "Gigante noble", "Montapuercos"};
        Question q19 = new Question(activity,"A quién pertenece éste sonido?", answers, 3,
                -1, r, R.raw.hog_rider_sound_mp3, video, null);
        db.questionDao().addQuestion(q19);

        r = R.raw.pekka_sound;
        answers = new String[]{"Pekka", "Valkiria", "mini Pekka", "Bárbaros de élite"};
        Question q20 = new Question(activity,"A quién pertenece éste sonido?", answers, 2,
                -1, r, R.raw.pekka_sound_mp3, video, null);
        db.questionDao().addQuestion(q20);

        r = R.raw.valkiria_sound;
        answers = new String[]{"Pekka", "Valkiria", "mini Pekka", "Bárbaros de élite"};
        Question q21 = new Question(activity,"A quién pertenece éste sonido?", answers, 1,
                -1, r, R.raw.valkiria_sound_mp3, video, null);
        db.questionDao().addQuestion(q21);

        //LISTVIEW QUESTIONS

        Integer[] images2 = new Integer[4];
        images2[0] = (R.drawable.ejercito_de_esqueletos);
        images2[1] = (R.drawable.chispitas);
        images2[2] = (R.drawable.pekka);
        images2[3] = (R.drawable.trio_de_mosqueteras);
        String [] questions = { "Ejército de esqueletos", "Chispitas", "Pekka", "Trío de Mosqueteras"};
        q = new Question(activity, "¿Qué carta hace más daño?", questions, 0, -1, -1,
                -1, listview, images2);
        db.questionDao().addQuestion(q);

        images2 = new Integer[4];
        images2[0] = (R.drawable.royalrecruits);
        images2[1] = (R.drawable.megacaballero);
        images2[2] = (R.drawable.goblin_giant);
        images2[3] = (R.drawable.royalhogscard);
        questions = new String []{ "Reclutas Reales", "Megacaballero", "Duende Gigante", "Puercos Reales"};
        q = new Question(activity, "¿Cuál ha sido la última carta añadida?", questions, 3, -1, -1,
                -1, listview, images2);
        db.questionDao().addQuestion(q);

        //SPINNER QUESTIONS
        int imagesSpinner = R.drawable.balloon;
        String [] questionsSpinner = { "5", "3", "9", "8"};
        q = new Question(activity, "¿Cuánto elixir cuesta esta carta?", questionsSpinner, 0, imagesSpinner, -1,
                -1, spinner, null);
        db.questionDao().addQuestion(q);

        //RADIO BUTTON QUESTIONS
        int image = R.drawable.portada_clash_royale;
        questions = new String[]{ "2012", "2016", "2014", "2017"};
        q = new Question(activity, "¿En qué año se lanzó clash royale?", questions, 1, image, -1,
                -1, radio, null);
        db.questionDao().addQuestion(q);

        image = R.drawable.bowler;
        questions = new String []{"Minero", "Bruja", "Mago", "Lanzarrocas"};
        q = new Question(activity, "¿Cómo se llama esta carta?", questions, 3, image, -1,
                -1, radio, null);
        db.questionDao().addQuestion(q);

        image = R.drawable.portada_clash_royale;
        questions = new String[]{ "Elixir", "Especia", "Eter", "Mana"};
        q = new Question(activity, "¿Cuál es la sustancia más valiosa de Clash Royale?", questions, 0, image, -1,
                -1, radio, null);
        db.questionDao().addQuestion(q);

        image = R.drawable.portada_clash_royale;
        questions = new String[]{ "Clash of Titans", "Clash of Legends", "Clash of Loyals", "Clash of Clans"};
        q = new Question(activity, "¿En que otro juego de la saga se basa Clash Royale?", questions, 3, image, -1,
                -1, radio, null);
        db.questionDao().addQuestion(q);

        image = R.drawable.portada_clash_royale;
        questions = new String[]{ "17", "27", "37", "47"};
        q = new Question(activity, "¿Cuántas cartas diferentes tenía el juego inicialmente?", questions, 1, image, -1,
                -1, radio, null);
        db.questionDao().addQuestion(q);

        image = R.drawable.portada_clash_royale;
        questions = new String[]{ "Puntos", "Cristales", "Coronas", "Monedas"};
        q = new Question(activity, "¿Cómo se llaman los puntos del juego?", questions, 2, image, -1,
                -1, radio, null);
        db.questionDao().addQuestion(q);

        image = R.drawable.portada_clash_royale;
        questions = new String[]{ "15 seg", "30 seg", "60 seg", "90 seg"};
        q = new Question(activity, "¿Cuánto dura el periodo de muerte súbita", questions, 2, image, -1,
                -1, radio, null);
        db.questionDao().addQuestion(q);

        image = R.drawable.portada_clash_royale;
        questions = new String[]{ "12 horas", "24 horas", "36 horas", "48 horas"};
        q = new Question(activity, "¿Cada cuánto tiempo puedes acceder a un cofre supermágico?", questions, 1, image, -1,
                -1, radio, null);
        db.questionDao().addQuestion(q);
    }
}
