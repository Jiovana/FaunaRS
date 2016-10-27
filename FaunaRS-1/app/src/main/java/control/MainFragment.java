package control;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.gomes.faunars_1.R;

/**
 * Created by gomes on 24/10/2016.
 */
public class MainFragment extends Fragment {

    private Button btnIdentificar;
    private Button btnPesquisar;
    private ImageView img;
    private IdentifyFragment identifyFragment;
    private SearchFragment searchFragment;
    private LinearLayout layout;
    private View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        btnIdentificar = (Button) view.findViewById(R.id.btnIdentificar);
        btnIdentificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                identifyFragment = new IdentifyFragment(); //cria o fragmento
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //Prepara o fragment que será inflado
                transaction.replace(R.id.fragment_container, identifyFragment).addToBackStack(null).commit();
            }
        });
        btnPesquisar = (Button) view.findViewById(R.id.btnPesquisar);
        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFragment = new SearchFragment(); //cria o fragmento
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //Prepara o fragment que será inflado
                transaction.replace(R.id.fragment_container, searchFragment).addToBackStack(null).commit();
            }
        });
        img = (ImageView) view.findViewById(R.id.img_main);
        layout = (LinearLayout) view.findViewById(R.id.layout_main_button);
        return view;
    }
}

