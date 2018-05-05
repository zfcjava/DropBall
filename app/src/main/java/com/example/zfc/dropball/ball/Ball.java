package com.example.zfc.dropball.ball;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.zfc.dropball.BallApplication;
import com.example.zfc.dropball.Gradient;
import com.example.zfc.dropball.Point;
import com.example.zfc.dropball.config.Constant;
import com.example.zfc.dropball.utils.LogUtil;
import com.example.zfc.dropball.utils.UIAdapterUtil;

/**
 * Created by zfc on 2018/5/5.
 */

public class Ball {
    public float x;
    public float y;
    private Point startPoint;
    private Gradient gradient;

    public Ball(float x, float y) {
        this.x = x;
        this.y = y;
        startPoint = new Point(x, y);
    }

    public Ball() {
    }

    public boolean isBottom() {
        return y >= UIAdapterUtil.getWindowHeight(BallApplication.getINSTANCE().getApplicationContext());
    }

    public void drawOn(Canvas c, Paint paint) {
        getNextPosition();
        c.drawCircle(x, y, Constant.DROP_BALL_RADIUS, paint);
    }


    private void getNextPosition() {
        float tmpx = x + Constant.DROP_DISTANCE_PER_STEP / gradient.ds * gradient.dx;
        if (tmpx < Constant.DROP_BALL_RADIUS) { //左侧边界
            y = Math.abs((x - Constant.DROP_BALL_RADIUS) / gradient.dx * gradient.dy) + y;
            x = Constant.DROP_BALL_RADIUS;
            gradient.dx *= -1f;

        } else if (tmpx > UIAdapterUtil.getWindowWidth(BallApplication.getINSTANCE().getApplicationContext()) - Constant.DROP_BALL_RADIUS) {
            y = Math.abs((UIAdapterUtil.getWindowWidth(BallApplication.getINSTANCE().getApplicationContext()) - Constant.DROP_BALL_RADIUS - x) / gradient.dx * gradient.dy) + y;
            x = UIAdapterUtil.getWindowWidth(BallApplication.getINSTANCE().getApplicationContext()) - Constant.DROP_BALL_RADIUS;
            gradient.dx *= -1f;
        } else {
            x = tmpx;
            y = y + Constant.DROP_DISTANCE_PER_STEP / gradient.ds * gradient.dy;
        }
        LogUtil.e("dropBall", "x = " + x + ",y = " + y);
    }


    /**
     * 重置坐标
     */
    public void reset(){
        this.x = startPoint.x;
        this.y = startPoint.y;
    }

    public void setGradient(Gradient gradient) {
        this.gradient = gradient;
    }

    public Gradient getGradient() {
        return gradient;
    }
}
