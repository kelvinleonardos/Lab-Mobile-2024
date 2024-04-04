package com.example.instagram;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomGridLayoutManager extends GridLayoutManager {

    // Rasio aspek default
    private float aspectRatio = 1f;

    public CustomGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public CustomGridLayoutManager(Context context, int spanCount, float aspectRatio) {
        super(context, spanCount);
        this.aspectRatio = aspectRatio;
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        // Hitung lebar setiap item berdasarkan lebar RecyclerView dan rasio aspek yang diinginkan
        int width = RecyclerView.LayoutManager.chooseSize(widthSpec, getWidth() - getPaddingLeft() - getPaddingRight(), getMinimumWidth());
        int height = (int) (width / aspectRatio); // Hitung tinggi berdasarkan rasio aspek

        // Tetapkan tinggi setiap item
        setMeasuredDimension(width, height * getItemCount());
    }
}
