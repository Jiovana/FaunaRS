package adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import model.Galeria;

/**
 * Created by gomes on 22/04/2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context context;
    private Galeria galeria;
    private List<Galeria> galeriaList;

    public ImageAdapter(Context c, List<Galeria> galerias){
        this.context = c;
        this.galeriaList = galerias;
    }
    @Override
    public int getCount() {
        return galeriaList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        galeria = galeriaList.get(position);
        ImageView img = new ImageView(context);
        img.setImageBitmap(BitmapFactory.decodeByteArray(galeria.imagem,0,galeria.imagem.length));
        img.setAdjustViewBounds(true);
        //img.setLayoutParams(params);
        return img;

    }
}
