package dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Especie;
import model.Galeria;

/**
 * Created by gomes on 06/08/2016.
 */
public class GaleriaDAO extends SQLiteOpenHelper{
    private static String TAG = "galeriadao";
    private static String NAME = "animais.db";
    private static int VERSION = 1;
    private static GaleriaDAO galeriaDAO = null;

    private GaleriaDAO(Context context){
        super(context, NAME, null, VERSION);
        getWritableDatabase();
    }

    public static GaleriaDAO getInstance (Context context){
        if (galeriaDAO == null){
            return galeriaDAO = new GaleriaDAO(context);
        }
        return galeriaDAO;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String sql = "CREATE TABLE IF NOT EXISTS GALERIA " +
                "(_GAL_ID INTEGER PRIMARY KEY, " +
                "GAL_IMG LONGBLOB, " +
                "ESP_ID INTEGER, " +
                "FOREIGN KEY (ESP_ID) REFERENCES ESPECIE(_ESP_ID));";
        Log.d(TAG, "Criando a tabela GALERIA. Aguarde...");
        sqLiteDatabase.execSQL(sql);
        Log.d(TAG,"Tabela GALERIA criada com sucesso");
        new Task().execute();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public List<Galeria> getByEsp(Long espid){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        try {
            return toList(sqLiteDatabase.rawQuery("SELECT * FROM GALERIA WHERE ESP_ID = '" + espid + "'",null));
        }finally {
            sqLiteDatabase.close();
        }
    }

    private List<Galeria> toList(Cursor c){
        List<Galeria> imagens = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                Galeria imagem  = new Galeria();
                imagem.gal_id = c.getLong(c.getColumnIndex("_GAL_ID"));
                imagem.esp_id = c.getLong(c.getColumnIndex("ESP_ID"));
                imagem.imagem = c.getBlob(c.getColumnIndex("GAL_IMG"));

                imagens.add(imagem);
            }while (c.moveToNext());
        }
        return imagens;
    }
    private class Task extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            return popularTabelaGaleria();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean){
                Log.d(TAG, "Tabela GALERIA populada com sucesso.");
            }
        }
    }
    private boolean popularTabelaGaleria(){
        return false;
    }
}


