package com.example.richellevital.recyclertest;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by richellevital on 7/19/15.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.AnimalViewHolder>{

    List<Animal> animals;
    int widthScreen;
    int heightScreen;

    // Instance varaibles
    int padding = 5;
    int padPPP = padding*4;
    int paddingPL = padding*3;
    int paddingL = padding*2;

    RVAdapter(List<Animal> animals, Context context){
        this.animals = animals;
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        widthScreen = size.x;
        heightScreen = size.y;
    }

    public static class AnimalViewHolder extends RecyclerView.ViewHolder {

        ImageView photo;

        AnimalViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView)itemView.findViewById(R.id.person_photo);
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
    public AnimalViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) { //specifying layout of each
        //item in recyclerview
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        AnimalViewHolder pvh = new AnimalViewHolder(v);
        return pvh;
    }

    //setting values
    @Override
    public void onBindViewHolder(AnimalViewHolder animalViewHolder, int i) {

        animalViewHolder.photo.setImageResource(animals.get(i).image);


        int bitmapWidth = animals.get(i).image.getWidth();
        int bitmapHeight = animals.get(i).image.getHeight();

        int sizeOrient = animals.get(i).sizeOrient;

        // int  = 1
        int portraitWidth = (widthScreen - padPPP)/3;
        int scale = portraitWidth/bitmapWidth;
        int portraightHeight = bitmapHeight*scale;

        //int = 2
        int landscape1Width = screenWidth - paddingPL - portraitWidth;
        scale = landscape1Width/bitmapWidth;
        int landscapeHeight = bitmapHeight*scale;

        //int = 3
        int landscape2Width = screenWidth - paddingL;
        scale = landscape2Width/bitmapWidth;
        landscapeHeight = bitmapHeight*scale;

        Picasso.with(this)
                .load(R.drawable.large)
                .resize(50, 50)
                .into(imageView)

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
