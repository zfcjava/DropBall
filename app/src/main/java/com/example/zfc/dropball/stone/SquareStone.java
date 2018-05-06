package com.example.zfc.dropball.stone;

import android.graphics.Canvas;
import android.graphics.Rect;
import com.example.zfc.dropball.Point;

/**
 * Created by zfc on 2018/5/6.
 */

public class SquareStone extends Stone {

    public SquareStone(Point center) {
        super(center);
    }

    @Override
    public void doDraw(Canvas c) {
        Rect rect = new Rect((int) (center.x - StoneConfig.STONE_SQUARE_SIDE_LENGHT / 2 + .5f), (int) (center.y - StoneConfig.STONE_SQUARE_SIDE_LENGHT / 2 + .5f),
                (int)(center.x + StoneConfig.STONE_SQUARE_SIDE_LENGHT / 2+.5f), (int) (center.y + StoneConfig.STONE_SQUARE_SIDE_LENGHT / 2 + .5f));
        c.drawRect(rect, paint);
        super.doDraw(c);
    }
}
