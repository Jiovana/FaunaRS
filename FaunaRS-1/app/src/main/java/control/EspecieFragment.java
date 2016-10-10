package control;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gomes.faunars_1.R;

import model.Especie;

/**
 * Created by gomes on 20/08/2016.
 */
public class EspecieFragment extends android.support.v4.app.Fragment {
    private Especie especie;
    private TextView txttitulo;
    private TextView txtfilo;
    private TextView txtordem;
    private TextView txtclasse;
    private TextView txtgenero;
    private TextView txtespecie;
    private TextView txtcaracteristica;
    private TextView txthabitos;
    private TextView txtlocalizacao;
    private ImageView imggrande;


    public EspecieFragment() {
        super();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //infla o layout
        View view = inflater.inflate(R.layout.fragment_especie, container, false);

        //um título para a janela
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Detalhes da espécie:");

        //titulo
        txttitulo = (TextView) view.findViewById(R.id.txt_titulo_esp);
        txttitulo.setText(especie.nome);

        //imagem
        imggrande = (ImageView) view.findViewById(R.id.img_esp);
        imggrande.setImageBitmap(BitmapFactory.decodeByteArray(especie.img1,0,especie.img1.length));

        //taxonomia
        txtfilo = (TextView) view.findViewById(R.id.txt_filo_esp);
        txtordem = (TextView) view.findViewById(R.id.txt_ord_esp);
        txtclasse = (TextView) view.findViewById(R.id.txt_clas_esp);
        txtgenero = (TextView) view.findViewById(R.id.txt_gen_esp);
        txtespecie = (TextView) view.findViewById(R.id.txt_esp_esp);
        txtfilo.setText(especie.filo);
        txtordem.setText(especie.ordem);
        txtclasse.setText(especie.classe);
        txtgenero.setText(especie.genero);
        txtespecie.setText(especie.especie);

        //caracteristicas, habitos e localização
        txtcaracteristica = (TextView) view.findViewById(R.id.txt_carac_esp);
        txthabitos = (TextView) view.findViewById(R.id.txt_hab_esp);
        txtlocalizacao = (TextView) view.findViewById(R.id.txt_loc_esp);
        txtcaracteristica.setText(especie.caracteristicas);
        txthabitos.setText(especie.habitos);
        txtlocalizacao.setText(especie.localizacao);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void onClickGaleria(){
        Toast.makeText(getContext(),"Em construção", Toast.LENGTH_SHORT).show();

    }
}
