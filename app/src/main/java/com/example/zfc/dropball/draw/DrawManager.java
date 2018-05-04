package com.example.zfc.dropball.draw;

import android.graphics.Canvas;

import com.example.zfc.dropball.Point;

/**
 * Created by zfc on 2018/5/5.
 */

public class DrawManager {

    public static final int LINE = 1;
    public static final int DROP = 2;

    private  DrawDropProcessor drawDropProcessor;
    private DrawLineProcessor drawLineProcessor;



    public DrawManager(Point startPoint) {
        this.drawLineProcessor = new DrawLineProcessor(startPoint);
        this.drawDropProcessor = new DrawDropProcessor(startPoint);
    }

    public void doDraw(Canvas c, Point endPoint, int strategy) {
        switch (strategy) {
            case LINE:
                drawLineProcessor.doDraw(c, endPoint);
                break;
            case DROP:
                drawDropProcessor.doDraw(c, endPoint);
                break;
        }
    }
}
