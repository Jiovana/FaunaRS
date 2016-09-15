package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.squareup.picasso.Callback;

import com.squareup.picasso.Picasso;


import com.example.gomes.faunars_1.R;

import java.io.ByteArrayOutputStream;
import java.util.List;

import model.Especie;

/**
 * Created by gomes on 16/08/2016.
 */
public class EspecieAdapter extends RecyclerView.Adapter<EspecieAdapter.EspeciesViewHolder> {
    protected static final String TAG = "Fauna_RS";
    private final List<Especie> especies;
    private final Context context;

    private EspecieOnClickListener especieOnClickListener;

    public EspecieAdapter(Context context, List<Especie> especies, EspecieOnClickListener especieOnClickListener) {
        this.context = context;
        this.especies = especies;
        this.especieOnClickListener = especieOnClickListener;
    }

    @Override
    public int getItemCount() {
        return this.especies != null ? this.especies.size() : 0;
    }

    @Override
    public EspeciesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Infla a view do layout
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_recyclerview_listespecie, viewGroup, false);

        // Cria o ViewHolder
        EspeciesViewHolder holder = new EspeciesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final EspeciesViewHolder holder, final int position) {
        // Atualiza a view
        Especie e = especies.get(position);

        holder.txtNome.setText(e.getNome());
        holder.txtNomeC.setText(e.getEspecie());
        holder.progress.setVisibility(View.VISIBLE);

        Picasso.with(context).load(String.valueOf(e.getImg2())).fit().into(holder.imgP, new Callback() { //ajuda professor ver o tipo de imagem pra puxar do banco, a variavel tá em bytes
            @Override
            public void onSuccess() {
                holder.progress.setVisibility(View.GONE);
            }
            @Override
            public void onError() {
                holder.progress.setVisibility(View.GONE);
            }
        });
        // Click
        if (especieOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    especieOnClickListener.onClickEspecie(holder.itemView, position); // A variável position é final
                }
            });
        }
    }

    public interface EspecieOnClickListener {
         void onClickEspecie(View view, int idx);
    }

    // ViewHolder com as views
    public static class EspeciesViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNome, txtNomeC;
        ImageView imgP;
        ProgressBar progress;

        public EspeciesViewHolder(View view) {
            super(view);
            // Cria as views para salvar no ViewHolder
            txtNome = (TextView) view.findViewById(R.id.txtNome_recycler);
            txtNomeC = (TextView) view.findViewById(R.id.txtNomeC_recycler);
            imgP = (ImageView) view.findViewById(R.id.imgP_recycler);
            progress = (ProgressBar) view.findViewById(R.id.progressbar_cardviewadapter);
        }
    }

}