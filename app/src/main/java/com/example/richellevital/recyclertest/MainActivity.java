package com.example.richellevital.recyclertest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<Animal> animals;
    Animal[] posts = new Animal[3];
    boolean[] porOrLan =  new boolean[3];

    public int height;
    public int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels;
        width = displaymetrics.widthPixels;

        initializeData();

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);

        GridLayoutManager llm = new GridLayoutManager(this, 3);
        rv.setLayoutManager(llm);

        RVAdapter adapter = new RVAdapter(animals, this, llm);
        rv.setAdapter(adapter);

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // This method creates an ArrayList that has three Animal objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData() {
        this.animals = new ArrayList<>();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.cuteturtle, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;

        boolean isPortrait = false;

        if(imageHeight > imageWidth) {
            isPortrait = true;
        }
        porOrLan[0] = isPortrait;


        BitmapFactory.decodeResource(getResources(), R.drawable.kappa, options);
        imageHeight = options.outHeight;
        imageWidth = options.outWidth;

        isPortrait = false;

        if(imageHeight > imageWidth) {
            isPortrait = true;
        }
        porOrLan[1] = isPortrait;


        BitmapFactory.decodeResource(getResources(), R.drawable.richelle, options);
        imageHeight = options.outHeight;
        imageWidth = options.outWidth;

        isPortrait = false;

        if(imageHeight > imageWidth) {
            isPortrait = true;
        }
        porOrLan[2] = isPortrait;

        reorganize();

    }

    private void reorganize() {
        animals = new ArrayList<Animal>();

        if(porOrLan[0] == true && porOrLan[1] == true && porOrLan[2] == true) {
             // PPP
            Log.v("HERE", "PPP");
            Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.cuteturtle);
            animals.add(new Animal(icon, 1));



        } else if(porOrLan[0] == true && porOrLan[1] == true && porOrLan[2] == false) {
            Log.v("HERE", "P1L,P2");
            //P1 L
            //P2
        } else if(porOrLan[0] == true && porOrLan[1] == false && porOrLan[2] == false) {
            Log.v("HERE", "PL,L");
            //PL
            //L
            Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.cuteturtle);
            animals.add(new Animal(icon, 1));

            animals.add(new Animal(BitmapFactory.decodeResource(this.getResources(), R.drawable.kappa), 2));

            animals.add(new Animal(BitmapFactory.decodeResource(this.getResources(), R.drawable.richelle), 3));


        } else if(porOrLan[0] == true && porOrLan[1] == false && porOrLan[2] == true) {
            Log.v("HERE", "PL,P3");
            // PL
            // P3
        } else if(porOrLan[0] == false && porOrLan[1] == false && porOrLan[2] == false) {
            Log.v("HERE", "LLL");
            //L
            //L
            //L
        } else if(porOrLan[0] == false && porOrLan[1] == false && porOrLan[2] == true) {
            Log.v("HERE", "L,LP");
            //L
            //LP
        } else if(porOrLan[0] == false && porOrLan[1] == true && porOrLan[2] == false) {
            Log.v("HERE", "LP,L");
            //LP
            //L
        } else if(porOrLan[0] == false && porOrLan[1] == true && porOrLan[2] == true) {
            Log.v("HERE", "LP,P3");
            //LP
            //P3
        } else {
            Log.v("HERE", "tfisgoingon");
        }
    }



}
