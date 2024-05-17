package com.example.notes;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MoveUpwardBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    public MoveUpwardBehavior() {
        super();
    }

    public MoveUpwardBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        if (dependency instanceof View) {
            dependency.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    Rect r = new Rect();
                    dependency.getWindowVisibleDisplayFrame(r);
                    int screenHeight = dependency.getRootView().getHeight();
                    int keypadHeight = screenHeight - r.bottom;

                    if (keypadHeight > screenHeight * 0.15) {
                        child.animate().translationY(-keypadHeight).setDuration(200).start();
                    } else {
                        child.animate().translationY(0).setDuration(200).start();
                    }
                }
            });
            return true;
        }
        return false;
    }
}
