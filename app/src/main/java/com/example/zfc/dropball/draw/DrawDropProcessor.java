package com.example.zfc.dropball.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;

import com.example.zfc.dropball.Gradient;
import com.example.zfc.dropball.Point;
import com.example.zfc.dropball.ball.Ball;
import com.example.zfc.dropball.ball.BallManager;
import com.example.zfc.dropball.controller.SurfaceViewController;
import com.example.zfc.dropball.utils.LogUtil;

/**
 * Created by zfc on 2018/5/5.
 */

public class DrawDropProcessor extends BaseDrawProcessor{

    private SurfaceViewController surfaceViewController;
    private Handler drawHandler;


    public DrawDropProcessor(Point startPoint, SurfaceViewController surfaceViewController) {
        super(startPoint);
        BallManager.getInstance().addBall(new Ball(startPoint.x, startPoint.y)); //TODO 暂时放入3个小球
        BallManager.getInstance().addBall(new Ball(startPoint.x, startPoint.y));
        BallManager.getInstance().addBall(new Ball(startPoint.x, startPoint.y));
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
            //求一下斜率
            float dx = endPoint.x - startPoint.x;
            float dy = endPoint.y - startPoint.y;
            if (dy <= 0) {
                return; //不考虑
            }
            float distance = (float) Math.sqrt(dx * dx + dy * dy);
            BallManager.getInstance().setGradient(new Gradient(dx, dy, distance));
        }

        c.drawColor(Color.BLACK);
        BallManager.getInstance().drawOn(c, paint);


        if (BallManager.getInstance().isDropFinshed()) {
            BallManager.getInstance().resetAllBalles();
            drawHandler.removeCallbacksAndMessages(null);
            return;
        }

        drawHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (drawHandler == null) {
                    return;
                }
               surfaceViewController.refreshFrame4Drop();
            }
        }, 10);

    }

    @Override
    public boolean isDrawingDrop() {
        return !(BallManager.getInstance().isDropFinshed());
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
