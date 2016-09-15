package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by gomes on 22/04/2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context context;
    private final int[] imagens;

    public ImageAdapter(Context c, int[] imagens){
        this.context = c;
        this.imagens = imagens;
    }
    @Override
    public int getCount() {
        return imagens.length;
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
        ImageView img = new ImageView(context);
        img.setImageResource(imagens[position]);
        img.setAdjustViewBounds(true);
        //img.setLayoutParams(params);
        return img;

    }
}
