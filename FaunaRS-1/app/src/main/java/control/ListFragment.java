package control;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.gomes.faunars_1.R;

import java.util.ArrayList;
import java.util.List;

import adapter.EspecieAdapter;
import dao.EspecieDAO;
import model.Especie;

/**
 * Created by gomes on 10/08/2016.
 */
public class ListFragment extends Fragment implements SearchView.OnQueryTextListener{

    protected RecyclerView recyclerView;
    private List<Especie> especies;
    private LinearLayoutManager linearLayoutManager;
    private EspecieDAO especieDAO;
    private SearchFragment searchFragment;
    private Bundle args;

    public ListFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        especieDAO = EspecieDAO.getInstance(getContext());
        setHasOptionsMenu(true);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
       // args = getArguments();
        MainActivity mainActivity = (MainActivity)getActivity();
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(mainActivity.filo);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        new Task().execute();
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_list,menu);
        //SearchView searchView = (SearchView) menu.findItem(R.id.menuitem_pesquisar).getActionView();
        SearchView sv = (SearchView) menu.findItem(R.id.menuitem_pesquisar).getActionView();

        sv.setQueryHint("Nome da espécie");
        sv.setOnQueryTextListener(this);
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        //toast("Ao pressionar enter.");
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Especie> especieList = new ArrayList<>(); //uma lista para nova camada de modelo da RecyclerView

        for(Especie especie : especies){ //um for-each na lista de especies
            if(especie.nome.contains(newText)) { //se o nome da especie contém o texto digitado
                especieList.add(especie); //adiciona a especie na nova lista
            }
        }
        //Context, fonte de dados, tratador do evento onClick
        recyclerView.setAdapter(new EspecieAdapter(getContext(), especieList, onClickEspecie()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    protected EspecieAdapter.EspecieOnClickListener onClickEspecie(){
        return new EspecieAdapter.EspecieOnClickListener() {
            // Aqui trata o evento onItemClick.
            @Override
            public void onClickEspecie(View view, int idx) {
                //Toast.makeText(getContext(), "Você clicou no item da RecyclerView.", Toast.LENGTH_LONG).showOk();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //Prepara o fragment que será inflado
                EspecieFragment especieFragment = new EspecieFragment();
                Bundle args = new Bundle();
                args.putSerializable("ESPÉCIE", especies.get(idx));
                especieFragment.setArguments(args);
                transaction.replace(R.id.fragment_container, especieFragment).addToBackStack(null).commit();
            }
        };

    }
    /*
        Classe interna para operaçẽos assíncronas na base de dados.
     */
    private class Task extends AsyncTask<Void, Void, List<Especie>>{ //<Params, Progress, Result>

        List<Especie> especies;

        @Override
        protected List<Especie> doInBackground(Void... voids) {
            //busca os carros em background, em uma thread exclusiva para esta tarefa.
            MainActivity mainActivity = (MainActivity)getActivity();
                return especieDAO.getByFilo(mainActivity.filo);
        }

        @Override
        protected void onPostExecute(List<Especie> especies) {
            super.onPostExecute(especies);
            //copia a lista de carros para uso no onQueryTextChange()
            ListFragment.this.especies = especies;
            //atualiza a view na UIThread
            recyclerView.setAdapter(new EspecieAdapter(getContext(), especies, onClickEspecie())); //Context, fonte de dados, tratador do evento onClick
        }
    }//fim classe interna


}