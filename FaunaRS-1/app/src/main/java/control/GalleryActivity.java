package control;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.gomes.faunars_1.R;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import adapter.EspecieAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import dao.GaleriaDAO;
import model.Especie;
import model.Galeria;

public class GalleryActivity extends AppCompatActivity {
    public static final String TAG = "GalleryActivity";
    public static final String EXTRA_ID = "imagens";

    private ArrayList<byte[]> imagens;
    private GalleryPagerAdapter adapter;
    private GaleriaDAO galeriaDAO;
    public Integer id;
    private ArrayList<byte[]> galerias;

    @InjectView(R.id.pager)
    ViewPager pager;
    @InjectView(R.id.thumbnails)
    LinearLayout miniaturas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.inject(this);
        galeriaDAO = GaleriaDAO.getInstance(getApplicationContext());

        // o que falta fazer: transformar a lista de objetos galeria para uma lista de imagens de cada objeto da galeria
        galerias = galeriaDAO.getImagemByEsp((Integer) getIntent().getExtras().get("id_especie"));
        Assert.assertNotNull(galerias);

        adapter = new GalleryPagerAdapter(this);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(10); // quantas imagens para carregar na memoria

    }

    class GalleryPagerAdapter extends PagerAdapter {
        Context c;
        LayoutInflater inflater;

        public GalleryPagerAdapter(Context context) {
            c = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return galerias.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = inflater.inflate(R.layout.pager_gallery_item, container, false);
            container.addView(itemView);

            //Pega o tamanho da imagem para mostrar ao redor de cada imagem
            int borderSize = miniaturas.getPaddingTop();
            // Pega o tamanho da miniatura atual
            int tamanhomini = ((FrameLayout.LayoutParams)
                    pager.getLayoutParams()).bottomMargin - (borderSize * 2);
            // Set the thumbnail layout parameters. Adjust as required
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(tamanhomini,tamanhomini);
            params.setMargins(0, 0, borderSize, 0);
            // You could also set like so to remove borders
           // ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    //ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            final ImageView thumbView = new ImageView(c);
            thumbView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            thumbView.setLayoutParams(params);
            thumbView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "miniatura clicada");
                    // Set the pager position when thumbnail clicked
                    pager.setCurrentItem(position);
                }
            });
            miniaturas.addView(thumbView);

            final SubsamplingScaleImageView imageView =
                    (SubsamplingScaleImageView) itemView.findViewById(R.id.image);
            //Carrega assíncronamente a imagem e adiciona a miniatura e o pagerview
            Glide.with(c)
                    .load(galerias.get(position))
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                            imageView.setImage(ImageSource.bitmap(bitmap));
                            thumbView.setImageBitmap(bitmap);
                        }
                    });
            return itemView;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }

}



