package dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by gomes on 06/08/2016.
 */
public class GaleriaDAO {
    //atributos e/ou constantes da classe
    private static GaleriaDAO galeriaDAO; //objeto da própria classe
    private final Context context; //o contexto da aplicação
    private DBHelper myHelper; //objeto que auxilia na criação e/ou upgrade do banco de dados
    private SQLiteDatabase database; //objeto de conexão com o banco de dados


    /**
     * Método construtor privado para implementar o padrão de projeto Singleton.
     *
     * @param context
     */
    private GaleriaDAO(Context context) {
        this.context = context; //recebe o contexto da Activity que o chamou
    }
    /**
     * Método responsável por obter a instância do objeto ou contruí-lo, se este ainda não tiver sido contruído.
     *
     * @param context
     */
    public static GaleriaDAO getInstance(Context context) {
        if (galeriaDAO == null) {
            galeriaDAO = new GaleriaDAO(context);
            return galeriaDAO;
        }
        return galeriaDAO;
    }
    /**
     * Método responsável por abrir uma conexão com o banco de dados.
     */
    public void open() {
        myHelper = DBHelper.getInstance(context); //instância um objeto que auxilia na criação e/ou atualização
        database = myHelper.getWritableDatabase(); //devolve uma conexão para o banco de dados
    }

    /**
     * Método responsável por liberar a conexão com o banco de dados.
     */
    public void close() {
        database.close(); //libera o recurso
    }




}
