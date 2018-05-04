package com.example.zfc.dropball.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.zfc.dropball.Point;
import com.example.zfc.dropball.config.Constant;

/**
 * Created by zfc on 2018/5/5.
 */

public class DrawLineProcessor implements IDrawable {

    private final Point startPoint;
    private Paint paint;

    public DrawLineProcessor(Point startPoint) {
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
        c.drawColor(Color.BLACK);
        float dx = endPoint.x - startPoint.x;
        float dy = endPoint.y - startPoint.y;
        if (dy <= 0) {
            return; //不考虑
        }
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        for (int i = 1; i <= Constant.DIRECTION_BALL_NUM; i++) {
            float newX = startPoint.x + i * (2 * Constant.DIRECTION_BALL_RADIUS + Constant.DIRECTION_BALL_SPACE) / distance * dx;
            float newy = startPoint.y + i * (2 * Constant.DIRECTION_BALL_RADIUS + Constant.DIRECTION_BALL_SPACE) / distance * dy;
            c.drawCircle(newX, newy, Constant.DIRECTION_BALL_RADIUS, paint);
        }
    }
}
