package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Especie;



/**
 * Created by gomes on 28/09/2016.
 */
public class EspecieDAO extends SQLiteOpenHelper {

    private static String TAG = "speciedao";
    private static String NAME = "animais.db";
    private static int VERSION = 1;
    private static EspecieDAO especieDAO = null;

    private EspecieDAO(Context context){
        super(context, NAME, null, VERSION);
        getWritableDatabase();
    }

    public static EspecieDAO getInstance (Context context){
        if (especieDAO == null){
            return especieDAO = new EspecieDAO(context);
        }
        return especieDAO;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String sql = "CREATE TABLE IF NOT EXISTS ESPECIE " +
                "(_ESP_ID INTEGER PRIMARY KEY, " +
                "ESP_NOME VARCHAR(100), " +
                "ESP_LOCAL TEXT, " +
                "ESP_CARACT TEXT, " +
                "ESP_HABITOS TEXT, " +
                "ESP_CARAC3 VARCHAR(50), " +
                "ESP_CARAC2 VARCHAR(50), " +
                "ESP_FOTOG MEDIUMBLOB, " +
                "ESP_FOTOP BLOB, " +
                "ESP_FILO VARCHAR(50), " +
                "ESP_ORDEM VARCHAR(50), " +
                "ESP_CLASSE VARCHAR(50), " +
                "ESP_GENERO VARCHAR(50), " +
                "ESP_ESP VARCHAR(100), " +
                "ESP_CARAC4 VARCHAR(50), " +
                "ESP_CARAC5 VARCHAR(50), " +
                "ESP_CARAC6 VARCHAR(50));";
        Log.d(TAG, "Criando a tabela ESPECIE. Aguarde...");
        sqLiteDatabase.execSQL(sql);
        Log.d(TAG,"Tabela ESPECIE criada com sucesso");
        new Task().execute();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public List<Especie> getByFilo(String filo){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        try {
            return toList(sqLiteDatabase.rawQuery("SELECT * FROM ESPECIE WHERE ESP_FILO = '" + filo + "'",null));
        }finally {
            sqLiteDatabase.close();
        }
    }
    public List<Especie> getByNome(String filo, String nome){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        try {
            return toList(sqLiteDatabase.rawQuery("SELECT * FROM ESPECIE WHERE ESP_FILO = '" + filo + "' AND ESP_NOME LIKE '" +
                    nome + "%'",null));
        }finally {
            sqLiteDatabase.close();
        }
    }

    private List<Especie> toList(Cursor c){
        List<Especie> especies = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                Especie especie = new Especie();
                especie.id = c.getLong(c.getColumnIndex("_ESP_ID"));
                especie.nome = c.getString(c.getColumnIndex("ESP_NOME"));
                especie.caracteristicas = c.getString(c.getColumnIndex("ESP_CARACT"));
                especie.habitos = c.getString(c.getColumnIndex("ESP_HABITOS"));
                especie.localizacao = c.getString(c.getColumnIndex("ESP_LOCAL"));
                especie.caracter2 = c.getString(c.getColumnIndex("ESP_CARAC2"));
                especie.caracter3 = c.getString(c.getColumnIndex("ESP_CARAC3"));
                especie.caracter4 = c.getString(c.getColumnIndex("ESP_CARAC4"));
                especie.caracter5 = c.getString(c.getColumnIndex("ESP_CARAC5"));
                especie.caracter6 = c.getString(c.getColumnIndex("ESP_CARAC6"));
                especie.filo = c.getString(c.getColumnIndex("ESP_FILO"));
                especie.ordem = c.getString(c.getColumnIndex("ESP_ORDEM"));
                especie.classe = c.getString(c.getColumnIndex("ESP_CLASSE"));
                especie.genero = c.getString(c.getColumnIndex("ESP_GENERO"));
                especie.especie = c.getString(c.getColumnIndex("ESP_ESP"));
                especie.img1 = c.getBlob(c.getColumnIndex("ESP_FOTOP"));
                especie.img2 = c.getBlob(c.getColumnIndex("ESP_FOTOG"));

                especies.add(especie);
            }while (c.moveToNext());
        }
        return especies;
    }
    private class Task extends AsyncTask<Void, Void, Boolean>{
        @Override
        protected Boolean doInBackground(Void... voids) {
            return popularTabelaEspecie();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean){
                Log.d(TAG, "Tabela ESPECIE populada com sucesso.");
            }
        }
    }
    private boolean popularTabelaEspecie(){
        return false;
    }
}
