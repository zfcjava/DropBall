package com.example.zfc.dropball.controller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.zfc.dropball.Point;

/**
 * Created by zfc on 2018/5/4.
 */

public class SurfaceViewController extends BaseController<SurfaceView> implements SurfaceHolder.Callback {

    boolean isCanUseSurfaceView = false; //判断当前的surfaceView是否可用
    SurfaceHolder surfaceHolder;

    private DrawHandler drawHandler;
    private Paint paint;


    public SurfaceViewController(SurfaceView subRootView) {
        super(subRootView);
        initSurfaceView();
        initDrawThead();
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }


    private void initSurfaceView() {
        SurfaceHolder holder =  subRootView.getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        isCanUseSurfaceView = true;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        isCanUseSurfaceView = false;
        this.surfaceHolder = null;
    }


    private void initDrawThead() {
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                drawHandler = new DrawHandler(Looper.myLooper());
                Looper.loop();
            }
        }.start();
    }


    public void doDraw(Point endPoint){
        if (drawHandler == null) {
            throw new RuntimeException("drawHanlder is null");
        }

        drawHandler.sendMessage(drawHandler.obtainMessage(1, endPoint));
    }

    public class DrawHandler extends Handler{

        public DrawHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            if (!isCanUseSurfaceView || surfaceHolder == null) { //不处理消息
                return;
            }
            Canvas c = null;
            try{
                synchronized (surfaceHolder) {
                    c = surfaceHolder.lockCanvas(null);
                    doDraw(c, (Point) msg.obj);
                    //通过它来控制帧数执行一次绘制后休息50ms
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                surfaceHolder.unlockCanvasAndPost(c);
            }


        }


    }

    private void doDraw(Canvas c, Point p) {
        //这个很重要，清屏操作，清楚掉上次绘制的残留图像
        c.drawColor(Color.BLACK);
//        c.translate(200, 200);
        c.drawCircle(p.x,p.y, 30, paint);
//        if(radius > 100){
//            radius = 10f;
//        }
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
