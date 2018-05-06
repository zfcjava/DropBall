package com.example.zfc.dropball.stone;

import android.graphics.Canvas;
import android.graphics.Path;
import com.example.zfc.dropball.Point;

/**
 * Created by zfc on 2018/5/6.
 */

public class TriangleStone extends Stone {

    public TriangleStone(Point center) {
        super(center);
    }

    @Override
    public void doDraw(Canvas c) {
        Path path = new Path();
        path.reset();
        path.moveTo(center.x - StoneConfig.STONE_TRIANGLE_SIDE_LENGHT / 2, center.y + StoneConfig.genhao3 / 6 * StoneConfig.STONE_TRIANGLE_SIDE_LENGHT);
        path.lineTo(center.x, center.y - StoneConfig.genhao3 * 1 / 3 * StoneConfig.STONE_TRIANGLE_SIDE_LENGHT);
        path.lineTo(center.x + StoneConfig.STONE_TRIANGLE_SIDE_LENGHT / 2, center.y + StoneConfig.genhao3 / 6 * StoneConfig.STONE_TRIANGLE_SIDE_LENGHT);
        path.close();
        c.drawPath(path,paint);
        super.doDraw(c);
    }
}
