package com.example.zfc.dropball.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import com.example.zfc.dropball.Point;

/**
 * Created by zfc on 2018/5/5.
 */

public class DrawDropProcessor extends BaseDrawProcessor{
    private Handler drawHandler;

    public DrawDropProcessor(Point startPoint) {
        super(startPoint);
        //计算动画
        initDrawThread();
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
    }
}
