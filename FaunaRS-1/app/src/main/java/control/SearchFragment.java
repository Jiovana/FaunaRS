package control;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gomes.faunars_1.R;

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
    private SearchFragment searchFragment;
    private EspecieDAO especieDAO;
    private Especie especie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        especieDAO = EspecieDAO.getInstance(getContext());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_search_fragment);  //um título para a janela
        final MainActivity mainActivity = (MainActivity)getActivity();

        btnP = (Button) view.findViewById(R.id.btnP);
        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.filo = ("PEIXES");
                replaceFragment();
            }
        });
        btnAn = (Button) view.findViewById(R.id.btnAn);
        btnAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.filo = ("ANFÍBIOS");
                replaceFragment();
            }
        });
        btnR = (Button) view.findViewById(R.id.btnR);
        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.filo = ("REPTILIA");
                replaceFragment();
            }
        });
        btnAv = (Button) view.findViewById(R.id.btnAv);
        btnAv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.filo = ("AVES");
                replaceFragment();
            }
        });
        btnM = (Button) view.findViewById(R.id.btnM);
        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.filo = ("MAMMALIA");
                replaceFragment();
            }
        });
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

    private void replaceFragment() {
       // Bundle args = new Bundle();
        //args.putString("Filo",searchFragment.getFilo());

        ListFragment listFragment = new ListFragment();
        //listFragment.setArguments(args);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //Prepara o fragment que será inflado

        transaction.replace(R.id.fragment_container, listFragment).addToBackStack(null).commit();
    }


}
