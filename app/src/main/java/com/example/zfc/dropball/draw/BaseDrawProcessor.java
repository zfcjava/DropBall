package com.example.zfc.dropball.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.zfc.dropball.Point;
import com.example.zfc.dropball.inter.IReleaseable;

/**
 * Created by zfc on 2018/5/5.
 */

public class BaseDrawProcessor implements IDrawable,IReleaseable {

    protected final Point startPoint;
    protected Paint paint;

    public BaseDrawProcessor(Point startPoint) {
        this.startPoint = startPoint;
        initPaint();
    }


    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public void release() {

    }

    @Override
    public void doDraw(Canvas c, Point endPoint) {

    }

    public boolean isDrawingDrop() {
        return false;
    }
}
