package control;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gomes.faunars_1.R;

import model.Especie;

/**
 * Created by gomes on 20/08/2016.
 */
public class EspecieFragment extends android.support.v4.app.Fragment {
    private Especie especie;
    private TextView txttitulo;
    private TextView txtfilo;
    private TextView txtclasse;
    private TextView txtordem;
    private TextView txtfamilia;
    private TextView txtespecie;
    private TextView txtcaracteristica;
    private TextView txthabitos;
    private TextView txtlocalizacao;
    private ImageView imggrande;
    private Button btnG;
    private GalleryActivity galleryActivity;


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

        especie = (Especie) getArguments().get("ESPECIE");

        //um título para a janela
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Detalhes da espécie");

        //titulo
        txttitulo = (TextView) view.findViewById(R.id.txt_titulo_esp);
        txttitulo.setText(especie.nome);

        //imagem
        imggrande = (ImageView) view.findViewById(R.id.img_esp);
        imggrande.setImageBitmap(BitmapFactory.decodeByteArray(especie.img2, 0, especie.img2.length));

        //taxonomia
        txtfilo = (TextView) view.findViewById(R.id.txt_filo_esp);
        txtordem = (TextView) view.findViewById(R.id.txt_ord_esp);
        txtclasse = (TextView) view.findViewById(R.id.txt_class_esp);
        txtfamilia = (TextView) view.findViewById(R.id.txt_fam_esp);
        txtespecie = (TextView) view.findViewById(R.id.txt_esp_esp);
        txtfilo.setText(especie.filo);
        txtordem.setText(especie.ordem);
        txtclasse.setText(especie.classe);
        txtfamilia.setText(especie.familia);
        txtespecie.setText(especie.especie);

        //caracteristicas, habitos e localização
        txtcaracteristica = (TextView) view.findViewById(R.id.txt_carac_esp);
        txthabitos = (TextView) view.findViewById(R.id.txt_hab_esp);
        txtlocalizacao = (TextView) view.findViewById(R.id.txt_loc_esp);
        txtcaracteristica.setText(especie.caracteristicas);
        txthabitos.setText(especie.habitos);
        txtlocalizacao.setText(especie.localizacao);

        //botão da galeria
        btnG = (Button) view.findViewById(R.id.btn_gal_esp);
        btnG.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), GalleryActivity.class);
                        intent.putExtra("id_especie", especie.id);
                        startActivity(intent);
                    }
                })
        );
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


}
