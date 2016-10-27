package control;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gomes.faunars_1.R;

/**
 * Created by gomes on 06/08/2016.
 */
public class MainActivity extends AppCompatActivity {

    private MediaPlayer player;
    private LinearLayout layout;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mainFragment, "mainFragment").addToBackStack("mainFragment").commit(); //adiciona o fragmento ao content_main.xml


        player = MediaPlayer.create(getApplicationContext(),R.raw.mus_vsasgore);
        player.start();
        player.setLooping(true);



        //cria a Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.title_main);
        actionBar.setIcon(R.drawable.large);
        //actionBar.setSubtitle("Lista de livros lidos");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public void onResume(){
        super.onResume();

    }
    /**
     * Ele é chamado pelo Android ao destruir a Activity.
     */
    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    /*----------------------------------

	Criação e tratamento de eventos da ActionBar

	-----------------------------------*/

    /**
     * Infla o menu (inflar no Android significa renderizar um arquivo .xml. Neste caso, o menu_main.xml)
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu); //infla o arquivo menu.xml na ActionBar
        //SearchView mySearchView = (SearchView) menu.findItem(R.id.menuitem_pesquisar).getActionView();//obtem a SearchView
       // mySearchView.setQueryHint("digite o nome"); //coloca um hint na SearchView
       // mySearchView.setOnQueryTextListener(MainActivity.this); //cadastra o tratador de eventos na lista de tratadores da SearchView
        return true;
    }
     /*----------------------------------
	Tratador de eventos (Handler) do menu da ActionBar.
	-----------------------------------*/
    /**
     * Ele e chamado ao clicar em um item de menu da ActionBar.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.menuitem_novo:{
                mostrarcreditos();
                return true;
            }

            case R.id.menuitem_som:{
                som();
                return true;
            }
            case R.id.menuitem_cancelar:{
                sair();
                return true;
            }
        }
        return false;
    }
    public void mostrarcreditos(){
        AlertDialog.Builder builder = new AlertDialog.Builder(
                MainActivity.this);
        builder.setTitle("Créditos:");
        builder.setMessage("App desenvolvido por Jiovana Gomes, TCC para o curso técnico integrado de informática do IFSUL, Bagé-RS.");
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        //Toast.makeText(getApplicationContext(),"Yes is clicked", Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();


    }
    public void som(){
        if(player.isPlaying()){
            player.pause();
        }else {
            player.start();
        }

    }

    public void sair(){
        player.stop();
        onDestroy();
    }
}
