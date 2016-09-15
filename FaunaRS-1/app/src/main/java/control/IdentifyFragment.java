package control;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.gomes.faunars_1.R;

public class IdentifyFragment extends android.support.v4.app.Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_identify, container, false);


        return view;
    }
    /**
     * Ele é chamado pelo Android ao destruir a Activity.
     */
    @Override
    public void onDestroy(){
        super.onDestroy();
        //livroDAO.close(); //fecha a conexao com o banco de dados
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_identify, menu); //infla o arquivo menu.xml na ActionBar
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

    }
    public void som(){

    }

    public void sair(){
        super.onDestroy();
    }
}

