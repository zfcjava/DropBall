package com.example.zfc.dropball.draw;

import android.graphics.Canvas;

import com.example.zfc.dropball.Point;

/**
 * Created by zfc on 2018/5/5.
 */

public interface IDrawable {
    void doDraw(Canvas c, Point endPoint);
}
