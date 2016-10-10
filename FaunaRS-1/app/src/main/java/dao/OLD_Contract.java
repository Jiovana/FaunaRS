package dao;

import android.provider.BaseColumns;

/**
 * Created by gomes on 03/09/2016.
 */
public class OLD_Contract {
    public static final String DB_NOME = "animais.db";
    public static final int DB_VERSAO = 1;

    //para impedir que acidentalmente se instancie a classe OLD_Contract dá-se a ela um construtor vazio
    public OLD_Contract(){}

    public static abstract class Especie implements BaseColumns {

        public static final String TABELA_ESPECIE= "especie";
        public static final String COLUNA_ID = "_id";
        public static final String COLUNA_NOME = "nome";
        public static final String COLUNA_LOCAL = "local";
        public static final String COLUNA_CARACT = "caract";
        public static final String COLUNA_HABITOS = "habitos";
        public static final String COLUNA_CARAC3 = "carac3";
        public static final String COLUNA_CARAC2= "carac2";
        public static final String COLUNA_FOTOG = "fotog";
        public static final String COLUNA_FOTOP= "fotop";
        public static final String COLUNA_FILO = "filo";
        public static final String COLUNA_ORDEM = "ordem";
        public static final String COLUNA_CLASSE = "classe";
        public static final String COLUNA_GENERO = "genero";
        public static final String COLUNA_ESP = "esp";
        public static final String COLUNA_CARAC4 = "carac4";
        public static final String COLUNA_CARAC5= "carac5";
        public static final String COLUNA_CARAC6 = "carac6";
    }

    public static abstract class Galeria implements BaseColumns{
        public static final String TABELA_GALERIA= "galeria";
        public static final String COLUNA_ID = "_id";
        public static final String COLUNA_ESPID = "espid";
        public static final String COLUNA_IMG = "img";
    }


}
