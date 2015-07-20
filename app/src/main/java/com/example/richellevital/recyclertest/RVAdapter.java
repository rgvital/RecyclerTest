package com.example.richellevital.recyclertest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by richellevital on 7/19/15.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.AnimalViewHolder>{

    List<Animal> animals;
    int widthScreen;
    int heightScreen;
    Context context;

    // Instance varaibles
    int padding = 1;
    int padPPP = padding*4;
    int paddingPL = padding*3;
    int paddingL = padding*2;

    int portraitWidth, landscape1Width, landscape2Width;

    GridLayoutManager glm;
    int sizeOrient = 1;
    RVAdapter(List<Animal> animals, Context context, GridLayoutManager glm){
        this.glm = glm;
        this.animals = animals;
        this.context = context;
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        widthScreen = size.x;
        heightScreen = size.y;
        Log.v("ADAPT", "WidthScreen = " + widthScreen);
        Log.v("ADAPT", "HeightScreen = " + heightScreen);
        //item in recyclerview
        portraitWidth = (widthScreen - padPPP)/3;
        landscape1Width = widthScreen - paddingPL - portraitWidth;
        landscape2Width = widthScreen - paddingL;
        Log.v("ADAPT", "PortraitWidth = " + portraitWidth);
        Log.v("ADAPT", "RegLandscapeWidth = " +  landscape1Width);
        Log.v("ADAPT", "BigLandscapeWidth = " + landscape2Width);

        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Log.v("ADAPT", "SIZEORIENT = " + sizeOrient);
                switch (sizeOrient) {
                    case 1:
                        return 1;
                    case 2:
                        return 2;
                    case 3:
                        return 3;
                    default:
                        return 1;
                }
            }
        });

    }

    public static class AnimalViewHolder extends RecyclerView.ViewHolder {

        ImageView photo;

        AnimalViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView)itemView.findViewById(R.id.photo);
        }
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }
/*
 * method is called when the custom viewholder needs to be initialized
 */
    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) { //specifying layout of eachu

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        AnimalViewHolder pvh = new AnimalViewHolder(v);
        return pvh;
    }

    //setting values
    @Override
    public void onBindViewHolder(AnimalViewHolder animalViewHolder, int i) {

        // There should be a set height for all of this...
        double scale;
        int portraitHeight, landscapeHeight;

        Bitmap bitmap = animals.get(i).image;

        int bitmapWidth = animals.get(i).image.getWidth();
        int bitmapHeight = animals.get(i).image.getHeight();

        sizeOrient = animals.get(i).sizeOrient;



        switch(sizeOrient) {
            case 1:

                scale = ((double) portraitWidth)/bitmapWidth;
                bitmapHeight = (int) (bitmapHeight*scale);
                bitmapWidth = portraitWidth;
                break;

            case 2:
                scale = ((double) landscape1Width)/bitmapWidth;
                bitmapHeight = (int) (bitmapHeight*scale);
                bitmapWidth = landscape1Width;

                break;

            case 3:

                scale = ((double) landscape2Width)/bitmapWidth;
                bitmapHeight = (int) (bitmapHeight*scale);
                bitmapWidth = landscape2Width;
                break;

        }

        Log.v("ADAPT", "bitmapHeight = " +  bitmapHeight);
        Log.v("ADAPT", "bitmapWidth = " + bitmapWidth);

                animalViewHolder.photo.setImageBitmap(Bitmap.createScaledBitmap(bitmap, bitmapWidth, bitmapHeight, true));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
