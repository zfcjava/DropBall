package com.example.zfc.dropball.ball;

import com.example.zfc.dropball.BallApplication;
import com.example.zfc.dropball.utils.UIAdapterUtil;

/**
 * Created by zfc on 2018/5/5.
 */

public class Ball {
    public float x;
    public float y;

    public Ball(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Ball() {
    }

    public boolean isBottom() {
        return y >= UIAdapterUtil.getWindowHeight(BallApplication.getINSTANCE().getApplicationContext());
    }
}
