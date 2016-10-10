package dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by gomes on 22/04/2016.
 */
public class OLD_EspecieDAO {
    //atributos e/ou constantes da classe
    private static OLD_EspecieDAO OLDEspecieDAO; //objeto da própria classe
    private final Context context; //o contexto da aplicação
    private OLD_DBHelper myHelper; //objeto que auxilia na criação e/ou upgrade do banco de dados
    private SQLiteDatabase database; //objeto de conexão com o banco de dados


    /**
     * Método construtor privado para implementar o padrão de projeto Singleton.
     *
     * @param context
     */
    private OLD_EspecieDAO(Context context) {
        this.context = context; //recebe o contexto da Activity que o chamou
    }
    /**
     * Método responsável por obter a instância do objeto ou contruí-lo, se este ainda não tiver sido contruído.
     *
     * @param context
     * @return livroDAO
     */
    public static OLD_EspecieDAO getInstance(Context context) {
        if (OLDEspecieDAO == null) {
            OLDEspecieDAO = new OLD_EspecieDAO(context);
            return OLDEspecieDAO;
        }
        return OLDEspecieDAO;
    }
    /**
     * Método responsável por abrir uma conexão com o banco de dados.
     */
    public void open() {
        myHelper = OLD_DBHelper.getInstance(context); //instância um objeto que auxilia na criação e/ou atualização
        database = myHelper.getWritableDatabase(); //devolve uma conexão para o banco de dados
    }

    /**
     * Método responsável por liberar a conexão com o banco de dados.
     */
    public void close() {
        database.close(); //libera o recurso
    }



    /**
     * MétodoS para obter a lista de ESPECIES.
     *
     * @return Cursor
     */
    public Cursor getListaPeixe() {
        //retorna o Cursor para os registros contidos no banco de dados
        return database.rawQuery("SELECT  * FROM " + OLD_Contract.Especie.TABELA_ESPECIE + "WHERE ESP_FILO = 'PEIXES' ORDER BY " + OLD_Contract.Especie.COLUNA_NOME, null);
    }
    public Cursor getListaAnfibio() {
        //retorna o Cursor para os registros contidos no banco de dados
        return database.rawQuery("SELECT  * FROM " + OLD_Contract.Especie.TABELA_ESPECIE + "WHERE ESP_FILO = 'ANFÍBIOS' ORDER BY " + OLD_Contract.Especie.COLUNA_NOME, null);
    }
    public Cursor getListaReptil() {
        //retorna o Cursor para os registros contidos no banco de dados
        return database.rawQuery("SELECT  * FROM " + OLD_Contract.Especie.TABELA_ESPECIE + "WHERE ESP_FILO = 'REPTILIA' ORDER BY "+ OLD_Contract.Especie.COLUNA_NOME, null);
    }
    public Cursor getListaAve() {
        //retorna o Cursor para os registros contidos no banco de dados
        return database.rawQuery("SELECT  * FROM " + OLD_Contract.Especie.TABELA_ESPECIE + "WHERE ESP_FILO = 'AVES' ORDER BY "+ OLD_Contract.Especie.COLUNA_NOME, null);
    }
    public Cursor getListaMamifero() {
        //retorna o Cursor para os registros contidos no banco de dados
        return database.rawQuery("SELECT  * FROM " + OLD_Contract.Especie.TABELA_ESPECIE + "WHERE ESP_FILO = 'MAMMALIA' ORDER BY "+ OLD_Contract.Especie.COLUNA_NOME, null);
    }

    /**
     *  Método para obter a lista de especies, baseado no criterio "nome"
     * @param nome
     * @return
     */
    public Cursor getListaByNomePeixe(String nome) {
        //retorna o Cursor para os registros contidos no banco de dados baseado no criterio "nome"
       return database.rawQuery("SELECT  * FROM " + OLD_Contract.Especie.TABELA_ESPECIE + " WHERE ESP_FILO = 'PEIXES' AND ESP_NOME LIKE '" + nome + "%'", null);
    }
    public Cursor getListaByNomeAnfibio(String nome) {
        //retorna o Cursor para os registros contidos no banco de dados baseado no criterio "nome"
        return database.rawQuery("SELECT  * FROM " + OLD_Contract.Especie.TABELA_ESPECIE + " WHERE ESP_FILO = 'ANFÍBIOS' AND ESP_NOME LIKE '" + nome + "%'", null);
    }
    public Cursor getListaByNomeReptil(String nome) {
        //retorna o Cursor para os registros contidos no banco de dados baseado no criterio "nome"
        return database.rawQuery("SELECT  * FROM " + OLD_Contract.Especie.TABELA_ESPECIE + " WHERE ESP_FILO = 'REPTILIA' AND ESP_NOME LIKE '" + nome + "%'", null);
    }
    public Cursor getListaByNomeAve(String nome) {
        //retorna o Cursor para os registros contidos no banco de dados baseado no criterio "nome"
        return database.rawQuery("SELECT  * FROM " + OLD_Contract.Especie.TABELA_ESPECIE + " WHERE ESP_FILO = 'AVES' AND ESP_NOME LIKE '" + nome + "%'", null);
    }
    public Cursor getListaByNomeMamifero(String nome) {
        //retorna o Cursor para os registros contidos no banco de dados baseado no criterio "nome"
        return database.rawQuery("SELECT  * FROM " + OLD_Contract.Especie.TABELA_ESPECIE + " WHERE ESP_FILO = 'MAMMALIA' AND ESP_NOME LIKE '" + nome + "%'", null);
    }
}
