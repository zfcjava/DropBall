package com.example.zfc.dropball.stone;

import android.graphics.Canvas;

import com.example.zfc.dropball.Point;

/**
 * Created by zfc on 2018/5/6.
 */

public class CircleStone extends Stone {

    public CircleStone(Point center) {
        super(center);
    }

    @Override
    public void doDraw(Canvas c) {
        c.drawCircle(center.x, center.y, StoneConfig.STONE_CIRCLE_RADIUS, paint);
        super.doDraw(c);
    }
}
