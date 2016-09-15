package control;

import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.gomes.faunars_1.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Especie;

/**
 * Created by gomes on 10/08/2016.
 */
public class ListFragment extends Fragment implements SearchView.OnQueryTextListener{

private final String TAG = "Webservice_Carros"; //TAG para o LogCat
protected RecyclerView recyclerView; //o container onde serão apresentados os dados
private SwipeRefreshLayout swipeRefreshLayout; //o SwipeRefresh. O objeto que identificará o movimento de swipe e reagirá
private ProgressBar progressBar; //uma animação para indicar processando
private List<Especie> especies; //lista dos carros, utilizada no método de tratamento do onClick() do item da lista




@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); //informa ao sistema que o fragment irá adicionar botões na ActionBar

        }
@Override
public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Chamou onDestroy da ListFragment.");
        getActivity().finish(); //finaliza a app
        }
@Override
public void onResume() {
        super.onResume();

        }
    /*
         Método do ciclo de vida do Fragment.
                 */
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_list, container, false); //infla o xml da UI e associa ao Fragment

    ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_fragment_listcarros);  //um título para a janela

    //configura a RecyclerView
    recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView); //mapeia o RecyclerView do layout.
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); //associa um gerenciador de layout Linear ao recyclerView.
    recyclerView.setItemAnimator(new DefaultItemAnimator()); //associa um tipo de animação ao recyclerView.
    recyclerView.setHasFixedSize(true);

    //configura o SwipeRefreshLayout
    swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swinperefrechlayout);
    swipeRefreshLayout.setOnRefreshListener(OnRefreshListener());
    swipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_1, R.color.refresh_progress_2, R.color.refresh_progress_3);

    //Cria um ProgressBar para mostrar uma animação de processando
    progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    Log.d(TAG, "BackStack em ListFragment = " + getFragmentManager().getBackStackEntryCount());
    return view;
}
    /*
        Infla o menu.
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_list, menu);
        android.widget.SearchView mySearchView = (android.widget.SearchView) menu.findItem(R.id.menuitem_pesquisar).getActionView();//obtém a SearchView
        mySearchView.setQueryHint("pesquisar por nome"); //coloca um hint na SearchView
        mySearchView.setOnQueryTextListener(this); //cadastra o tratador de eventos na lista de tratadores da SearchView
    }
    /*
        Trata eventos dos itens de menu.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    /*
        Trata eventos da SearchView
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Carro> carroList = new ArrayList<>(); //uma lista para nova camada de modelo da RecyclerView

        for(Carro carro : carros){ //um for-eatch na lista de carros
            if(carro.nome.startsWith(newText)) { //se o nome do carro começa com o texto digitado
                carroList.add(carro); //adiciona o carro na nova lista
            }
        }
        recyclerView.setAdapter(new CarroAdapter(getContext(), carroList, onClickCarro())); //Context, fonte de dados, tratador do evento onClick
        return false;
    }

    /*
        Classe interna que extende uma AsyncTask.
        Lembrando: A AsyncTask gerência a thread que acessa os dados no web service.
    */
    private class CarrosTask extends AsyncTask<Void, Void, List<Carro>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE); //faz com que a ProgressBar apareça para o usuário
        }
        @Override
        protected List<Carro> doInBackground(Void... params) {
            //busca os carros em background, em uma thread exclusiva para esta tarefa.
            try {
                return CarroService.getCarros("/carros");
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "Exceção ao obter a lista de carros, método .doInBackground()");
                return null;
            }
        }
        @Override
        protected void onPostExecute(List<Carro> carros) {
            super.onPostExecute(carros);
            if (carros != null) {
                Log.d(TAG, "Quantidade de carros no onPostExecute(): " + carros.size());
                //atualiza a view na UIThread
                recyclerView.setAdapter(new CarroAdapter(getContext(), carros, onClickCarro())); //Context, fonte de dados, tratador do evento onClick
                ListCarrosFragment.this.carros = carros; //copia a lista de carros para uso no tratador do onClick
                swipeRefreshLayout.setRefreshing(false); //para a animação da swipeRefrech
                progressBar.setVisibility(View.INVISIBLE); //faz com que a ProgressBar desapareça para o usuário
            }else{
                progressBar.setVisibility(View.INVISIBLE); //faz com que a ProgressBar desapareça para o usuário
                AlertUtils.showOk(getContext(), R.string.app_name, R.string.alertdialog_message_erro_download); //faz aparecer o AlertDialog para o usuário
            }
        }
    }
    /*
        Este método utiliza a interface declarada na classe CarroAdapter para tratar o evento onClick do item da lista.
     */
   protected CarroAdapter.CarroOnClickListener onClickCarro() {
        //chama o contrutor da interface (implícito) para cria uma instância da interface declarada no adaptador.
        return new CarroAdapter.CarroOnClickListener() {
            // Aqui trata o evento onItemClick.
            @Override
            public void onClickCarro(View view, int idx) {
                //Toast.makeText(getContext(), "Você clicou no item da RecyclerView.", Toast.LENGTH_LONG).showOk();
                replaceFragment(idx);
            }
        };
    }
    /*
        Substitui o Fragmento no container R.id.fragment_container, componente do layout content_main.xml
     */
   private void replaceFragment(Integer idx){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //Prepara o fragment que será inflado
        DetalheCarroFragment detalheCarroFragment = new DetalheCarroFragment();
        Bundle args = new Bundle();
        args.putSerializable("CARRO", carros.get(idx));
        detalheCarroFragment.setArguments(args);
        transaction.replace(R.id.fragment_container, detalheCarroFragment).addToBackStack(null).commit();
    }
    /*
        Este método trata o evento onRefresh() do SwipeRefreshLayout.
        Ele acontece quando o usuário faz um swipe com o dedo para baixo na View.
     */
    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (AndroidUtils.isNetworkAvailable(getContext())) { //se houver conexão com a internet, wi-fi ou 3G ...
                    if (AndroidUtils.isNetworkAvailable(getContext())) { //se houver conexão com a internet, wi-fi ou 3G ...
                        new CarrosTask().execute(); //cria uma instância de AsyncTask
                    } else {
                        AndroidUtils.alertDialog(getContext(), "Alerta de conectividade.", "Não há conexão com a internet. Vefirique se você ligou o wi-fi ou os dados móveis.");
                        recyclerView.setAdapter(new CarroAdapter(getContext(), new ArrayList<Carro>(), onClickCarro()));
                    }
                }
            }
        };
    }
}