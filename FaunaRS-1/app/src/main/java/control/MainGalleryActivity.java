package control;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.gomes.faunars_1.R;

import java.util.ArrayList;


public class MainGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button galleryButton = (Button) findViewById(R.id.btn_gallery);
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGalleryActivity();
            }
        });

        startGalleryActivity();
    }

    public void startGalleryActivity() {
        ArrayList<String> images = new ArrayList<String>();
        images.add("http://sourcey.com/images/stock/salvador-dali-metamorphosis-of-narcissus.jpg");
        images.add("http://sourcey.com/images/stock/salvador-dali-the-dream.jpg");
        images.add("http://sourcey.com/images/stock/salvador-dali-persistence-of-memory.jpg");
        images.add("http://sourcey.com/images/stock/simpsons-persistence-of-memory.jpg");
        images.add("http://sourcey.com/images/stock/salvador-dali-the-great-masturbator.jpg");
        Intent intent = new Intent(MainGalleryActivity.this, GalleryActivity.class);
        intent.putStringArrayListExtra(GalleryActivity.EXTRA_NAME, images);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
            //return true;
        //}

        return super.onOptionsItemSelected(item);
    }
}
