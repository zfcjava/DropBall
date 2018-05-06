package com.example.zfc.dropball.draw;

import android.graphics.Canvas;

import com.example.zfc.dropball.Point;
import com.example.zfc.dropball.stone.StoneManager;

/**
 * Created by zfc on 2018/5/6.
 */

public class DrawStoneProcessor extends BaseDrawProcessor {


    public DrawStoneProcessor(Point startPoint) {
        super(startPoint);
    }

    @Override
    public void doDraw(Canvas c, Point endPoint) {
        StoneManager.getInstance().doDraw(c);
    }
}
