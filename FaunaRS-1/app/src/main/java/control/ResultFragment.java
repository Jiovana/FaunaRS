package control;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gomes.faunars_1.R;

import java.util.List;

import adapter.EspecieAdapter;
import dao.EspecieDAO;
import model.Especie;


public class ResultFragment extends Fragment {

    public static final String TAG = "ResultFragment";

    protected RecyclerView recyclerView;
    private List<Especie> especies;
    private LinearLayoutManager linearLayoutManager;
    private EspecieDAO especieDAO;
    private IdentifyFragment identifyFragment;
    private EspecieAdapter adapter;
    public String classe, c2, c3, c4, c5;
    public boolean aux1 = false, aux2 = false, aux3 = false, aux4 = false;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        especieDAO = EspecieDAO.getInstance(getContext());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Resultado da pesquisa:");
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_result);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar_result);
        new Task().execute();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected EspecieAdapter.EspecieOnClickListener onClickEspecie() {
        return new EspecieAdapter.EspecieOnClickListener() {
            // Aqui trata o evento onItemClick.
            @Override
            public void onClickEspecie(View view, int idx) {
                //Toast.makeText(getContext(), "Você clicou no item da RecyclerView.", Toast.LENGTH_LONG).showOk();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //Prepara o fragment que será inflado
                EspecieFragment especieFragment = new EspecieFragment();
                Bundle args = new Bundle();
                args.putSerializable("ESPECIE", especies.get(idx));
                especieFragment.setArguments(args);
                transaction.replace(R.id.fragment_container, especieFragment).addToBackStack(null).commit();
            }
        };

    }

    /*
        Classe interna para operaçẽos assíncronas na base de dados.
     */
    private class Task extends AsyncTask<Void, Void, List<Especie>> { //<Params, Progress, Result>

        List<Especie> especies;

        @Override
        protected List<Especie> doInBackground(Void... voids) {
            //busca os animais em background, em uma thread exclusiva para esta tarefa.
            if (aux1 == false && aux2 == false && aux3 == false && aux4 == false) {
                Log.d(TAG, "Caiu no 1 if");
                return especieDAO.getByC2C3C4C5(classe, c2, c3, c4, c5);
            }else if (aux1 == true && aux2 == true && aux3 == true && aux4 == true) {
                Log.d(TAG, "Caiu no 2 if. Atualmente o 2 if é o segundo mesmo");
                return especieDAO.getByClasse(classe);
            } else if (aux1 == false && aux2 == false && aux3 == false && aux4 == true) {
                Log.d(TAG, "Caiu no 3 if");
                return especieDAO.getByC2C3C4(classe, c2, c3, c4);
            } else if (aux1 == false && aux2 == false && aux4 == false && aux3 == true) {
                Log.d(TAG, "Caiu no 4 if");
                return especieDAO.getByC2C3C5(classe, c2, c3, c5);
            } else if (aux1 == false && aux3 == false && aux4 == false && aux2 == true) {
                Log.d(TAG, "Caiu no 5 if");
                return especieDAO.getByC2C4C5(classe, c2, c4, c5);
            } else if (aux2 == false && aux3 == false && aux4 == false && aux1 ==true) {
                Log.d(TAG, "Caiu no 6 if");
                return especieDAO.getByC3C4C5(classe, c3, c4, c5);
            } else if (aux1 == false && aux2 == false && aux3 == true && aux4 == true) {
                Log.d(TAG, "Caiu no 7 if");
                return especieDAO.getByC2C3(classe, c2, c3);
            } else if (aux1 == false && aux3 == false && aux2 == true && aux4 == true) {
                Log.d(TAG, "Caiu no 8 if");
                return especieDAO.getByC2C4(classe, c2, c4);
            } else if (aux1 == false && aux4 == false && aux2 == true && aux3 == true) {
                Log.d(TAG, "Caiu no 9 if");
                return especieDAO.getByC2C5(classe, c2, c5);
            } else if (aux2 == false && aux3 == false && aux1 == true && aux4 == true) {
                Log.d(TAG, "Caiu no 10 if");
                return especieDAO.getByC3C4(classe, c3, c4);
            } else if (aux2 == false && aux4 == false && aux1 == true && aux3 == true) {
                Log.d(TAG, "Caiu no 11 if");
                return especieDAO.getByC3C5(classe, c3, c5);
            } else if (aux3 == false && aux4 == false && aux1 == true && aux2 == true) {
                Log.d(TAG, "Caiu no 12 if");
                return especieDAO.getByC4C5(classe, c4, c5);
            } else if (aux1 == false && aux2 == true && aux3 == true && aux4 == true) {
                Log.d(TAG, "Caiu no 13 if");
                return especieDAO.getByC2(classe, c2);
            } else if (aux2 == false && aux1 == true && aux3 == true && aux4 == true) {
                Log.d(TAG, "Caiu no 14 if");
                return especieDAO.getByC3(classe, c3);
            } else if (aux3 == false && aux1 == true && aux2 == true && aux4 == true) {
                Log.d(TAG, "Caiu no 15 if");
                return especieDAO.getByC4(classe, c4);
            } else if (aux4 == false && aux1 == true && aux2 == true && aux3 == true) {
                Log.d(TAG, "Caiu no 16 if");
                return especieDAO.getByC5(classe, c5);
            }
            Log.e(TAG, "Algo deu errado");
            return null;
        }

        @Override
        protected void onPostExecute(List<Especie> especies) {
            super.onPostExecute(especies);
            //copia a lista para uso no onQueryTextChange()
            ResultFragment.this.especies = especies;
            //atualiza a view na UIThread
            recyclerView.setAdapter(new EspecieAdapter(getContext(), especies, onClickEspecie())); //Context, fonte de dados, tratador do evento onClick
            progressBar.setVisibility(View.INVISIBLE);
            if(especies.isEmpty()==true){
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Aviso:");
                builder.setMessage("Não há animais com as características escolhidas. Tente novamente com outras informações. ");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                //Prepara o fragment que será inflado
                                identifyFragment = new IdentifyFragment();
                                transaction.replace(R.id.fragment_container, identifyFragment).addToBackStack(null).commit();
                            }
                        });
                builder.show();

            }
        }
    }//fim classe interna

}
