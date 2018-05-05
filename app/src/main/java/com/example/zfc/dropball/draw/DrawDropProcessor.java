package com.example.zfc.dropball.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import com.example.zfc.dropball.Point;
import com.example.zfc.dropball.ball.Ball;
import com.example.zfc.dropball.controller.SurfaceViewController;
import com.example.zfc.dropball.utils.LogUtil;

import java.lang.ref.PhantomReference;

/**
 * Created by zfc on 2018/5/5.
 */

public class DrawDropProcessor extends BaseDrawProcessor{

    private SurfaceViewController surfaceViewController;
    private Handler drawHandler;
    private Ball ball;


    public DrawDropProcessor(Point startPoint, SurfaceViewController surfaceViewController) {
        super(startPoint);
        ball = new Ball(startPoint.x, startPoint.y);
        this.surfaceViewController = surfaceViewController;
        //计算动画
        initDrawThread();
    }



    @Override
    public void doDraw(final Canvas c, final Point endPoint) {
        //这个很重要，清屏操作，清楚掉上次绘制的残留图像
//        c.drawColor(Color.BLACK);
//        c.translate(200, 200);
//        c.drawCircle(endPoint.x,endPoint.y, 30, paint);
//        if(radius > 100){
//            radius = 10f;
//        }

        if (endPoint.x != -2018 || endPoint.y != -2018) { //这是在做帧刷新
//            ball = new Ball(endPoint.x, endPoint.y);
        }


        c.drawColor(Color.BLACK);
        c.drawCircle(ball.x, ball.y += 30, 30, paint);


        if (ball.isBottom()) {
            ball.x = startPoint.x;
            ball.y = startPoint.y;
            drawHandler.removeCallbacksAndMessages(null);
            return;
        }

        drawHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LogUtil.e("ball", ball.y + "------");
                if (drawHandler == null) {
                    return;
                }
               surfaceViewController.refreshFrame4Drop();
            }
        }, 300);

    }

    @Override
    public boolean isDrawingDrop() {
        return !(ball.x == startPoint.x &&
                ball.y == startPoint.y);
    }

    /**
     * 创建一个子线程用来渲染滚动中的小球
     */
    private void initDrawThread() {
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                drawHandler = new Handler(Looper.myLooper());
                Looper.loop();
            }
        }.start();
    }

    @Override
    public void release() {
        super.release();
        if (drawHandler != null) {
            drawHandler.removeCallbacksAndMessages(null);
            drawHandler = null;
        }
        if (surfaceViewController != null) {
            surfaceViewController = null;
        }
    }
}
