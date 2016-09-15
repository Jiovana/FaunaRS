package control;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.example.gomes.faunars_1.R;

import java.util.List;

import dao.EspecieDAO;
import model.Especie;

/**
 * Created by gomes on 06/08/2016.
 */
public class SearchFragment extends Fragment {

    private Button btnP;
    private Button btnAn;
    private Button btnR;
    private Button btnAv;
    private Button btnM;
    private ListFragment listFragment;
    private EspecieDAO especieDAO;
    private Especie especie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_search_fragment);  //um título para a janela
        especieDAO = EspecieDAO.getInstance(getContext());
        especieDAO.open();

        return view;
    }

    /**
     * Ele é chamado pelo Android ao destruir a Activity.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        //livroDAO.close(); //fecha a conexao com o banco de dados
        getActivity().finish(); //finaliza a app
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void onClickPeixes() {

        replaceFragment();
    }

    public void onClickAnfibios() {
        replaceFragment();
    }

    public void onClickRepteis() {
        replaceFragment();
    }

    public void onClickAves() {
        replaceFragment();
    }

    public void onClickMamiferos() {
        replaceFragment();
    }

    private void replaceFragment() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //Prepara o fragment que será inflado
        ListFragment listFragment = new ListFragment();
        transaction.replace(R.id.fragment_container, listFragment).addToBackStack(null).commit();
    }


}
