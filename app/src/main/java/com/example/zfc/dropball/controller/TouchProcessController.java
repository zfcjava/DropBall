package com.example.zfc.dropball.controller;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.example.zfc.dropball.Point;

/**
 * Created by zfc on 2018/5/4.
 */

public class TouchProcessController extends BaseController<View> implements View.OnTouchListener {

    private  TouchListener touchListener;
    private float touchSlop;
    private float touchX;
    private float touchY;

    public TouchProcessController(View subRootView, TouchListener touchListener) {
        super(subRootView);
        initTouchListener();
        touchSlop = ViewConfiguration.get(subRootView.getContext()).getScaledTouchSlop();
        this.touchListener = touchListener;
    }

    private void initTouchListener() {
        subRootView.setOnTouchListener(this);
    }

    @Override
    public void release() {
        super.release();
        if (this.touchListener != null) {
            touchListener = null;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX = motionEvent.getRawX();
                touchY = motionEvent.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = motionEvent.getRawX() - touchX;
                float dy = motionEvent.getRawY() - touchY;
//                if (dx >= touchSlop || dy >= touchSlop) {
                    touchX = motionEvent.getRawX();
                    touchY = motionEvent.getRawY();
                    if (touchListener != null) {
                        touchListener.onTouchPositionChanged(new Point(touchX,touchY));
                    }
//                }
                break;
            case MotionEvent.ACTION_UP:
                if (touchListener != null) {
                    touchListener.onTouchEnd(new Point(motionEvent.getRawX(),motionEvent.getRawY()));
                }
                break;
        }
        return true;
    }


    public interface TouchListener{
        void onTouchPositionChanged(Point endPoint);

        void onTouchEnd(Point endPoint);
    }
}
