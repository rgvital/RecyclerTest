package com.example.richellevital.recyclertest;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tina on 7/20/15.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private List<Animal> animals;

    public SpacesItemDecoration(int space, List<Animal> animals) {
        this.space = space;
        this.animals = new ArrayList<Animal>(animals);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.bottom = space;

        // Add top margin only for the first item to avoid double space between items
        if(animals.get(parent.getChildPosition(view)).top == true) {
            outRect.top = space;
        }
        if(animals.get(parent.getChildPosition(view)).right == true) {
            outRect.right = space;
        }
        if(animals.get(parent.getChildPosition(view)).left == true) {
            outRect.left = space;
        }
    }
}