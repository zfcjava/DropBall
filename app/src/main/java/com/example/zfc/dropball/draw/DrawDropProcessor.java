package com.example.zfc.dropball.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.zfc.dropball.Point;

/**
 * Created by zfc on 2018/5/5.
 */

public class DrawDropProcessor implements IDrawable{

    private final Point startPoint;
    private Paint paint;

    public DrawDropProcessor(Point startPoint) {
        this.startPoint = startPoint;
        initPaint();
    }


    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public void doDraw(Canvas c, Point endPoint) {
        //这个很重要，清屏操作，清楚掉上次绘制的残留图像
        c.drawColor(Color.BLACK);
//        c.translate(200, 200);
        c.drawCircle(endPoint.x,endPoint.y, 30, paint);
//        if(radius > 100){
//            radius = 10f;
//        }

        //计算动画
    }
}
