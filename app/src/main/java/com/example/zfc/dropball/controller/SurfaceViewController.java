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
import com.example.zfc.dropball.draw.DrawManager;

/**
 * Created by zfc on 2018/5/4.
 */

public class SurfaceViewController extends BaseController<SurfaceView> implements SurfaceHolder.Callback {

    public final int DRAW_DIRECTION_LINE = 2018;
    public final int DRAW_DROP = 2019;
    public final int DRAW_STONE = 2020;
    private final Point startPoint;

    boolean isCanUseSurfaceView = false; //判断当前的surfaceView是否可用
    SurfaceHolder surfaceHolder;

    private DrawHandler drawHandler;
    private Paint paint;
    private DrawManager drawManager; //用来绘制


    public SurfaceViewController(SurfaceView subRootView,Point startPoint) {
        super(subRootView);
        this.startPoint = startPoint;
        initSurfaceView();
        initDrawThead();
        initDrawProcesser();
    }

    private void initDrawProcesser() {
        drawManager = new DrawManager(startPoint, this);
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


    public void doDrawDrop(Point endPoint){
        if (drawHandler == null) {
            throw new RuntimeException("drawHanlder is null");
        }

        drawHandler.sendMessage(drawHandler.obtainMessage(DRAW_DROP, endPoint));
    }

    public void doDrawLine(Point endPoint) {
        drawHandler.sendMessage(drawHandler.obtainMessage(DRAW_DIRECTION_LINE, endPoint));
    }

    public void refreshFrame4Drop(){
        drawHandler.sendMessage(drawHandler.obtainMessage(DRAW_DROP, new Point(-2018, -2018)));
    }

    public void doDrawStone() {
        drawHandler.sendMessage(drawHandler.obtainMessage(DRAW_STONE));
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
                    switch (msg.what) {
                        case DRAW_DIRECTION_LINE:
                            drawManager.doDraw(c, (Point) msg.obj, DrawManager.LINE);
                            break;
                        case DRAW_DROP:
                            drawManager.doDraw(c, (Point) msg.obj, DrawManager.DROP);
                            break;
                        case DRAW_STONE:
                            drawManager.doDraw(c, (Point) msg.obj, DrawManager.STONE);
                            break;
                        default:
                            drawManager.doDraw(c, (Point) msg.obj, DrawManager.DROP);
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                surfaceHolder.unlockCanvasAndPost(c);
            }


        }


    }

    public boolean isDrawingDrop(){
        if (drawManager == null) {
            return false;
        }
        return drawManager.isDrawingDrop();
    }

    @Override
    public void release() {
        super.release();
        if (drawHandler != null) {
            drawHandler.removeCallbacksAndMessages(null);
            drawHandler = null;
        }

        if (drawManager != null) {
            drawManager.release();
            drawManager = null;
        }
    }
}
