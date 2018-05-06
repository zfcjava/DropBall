package com.example.zfc.dropball.stone;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.zfc.dropball.Point;
import com.example.zfc.dropball.utils.LogUtil;

/**
 * Created by zfc on 2018/5/6.
 */

public abstract class Stone {

    protected int attackNums = 5; //允许撞击的次数
    protected Paint paint;
    protected Point center;

    public void doAtackedAnim(){ //撞击之后的特效

    }

    public Stone(Point center) {
        this.center = center;
        initPaint();
    }

    public Stone() {
        initPaint();
    }


    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }
    public void doDraw(Canvas c){
//        paint.setColor(Color.BLUE);
//        paint.setTextSize(15);
//        LogUtil.e(getClass().getSimpleName(), "draw");
//        c.drawText(attackNums + "", center.x, center.y, paint);
    }

}
