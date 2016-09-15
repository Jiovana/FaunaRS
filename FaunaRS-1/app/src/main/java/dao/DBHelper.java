package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DBHelper extends SQLiteOpenHelper {

    // atributos e/ou constantes da classe
    private static DBHelper myHelper; // objeto que auxilia na criação e/ou
    // upgrade do banco de dados

    /**
     * Método construtor privado para implementar o padrão de projeto Singleton.
     *
     * @param context
     */
    private DBHelper(Context context) {
        super(context, Contract.DB_NOME, null, Contract.DB_VERSAO);
    }

    /**
     * Método responsável por obter a instância do objeto ou contruí-lo, se este
     * ainda não tiver sido contruído.
     *
     * @param context
     * @return ctoDAO
     */
    public static DBHelper getInstance(Context context) {
        if (myHelper == null) {
            myHelper = new DBHelper(context);
            return myHelper;
        }

        return myHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}


