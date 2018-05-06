package com.example.zfc.dropball.ball;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.zfc.dropball.Gradient;
import com.example.zfc.dropball.stone.StoneManager;
import com.example.zfc.dropball.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zfc on 2018/5/5.
 */

public class BallManager {

    List<Ball> dropBalls = new ArrayList<>();
    List<Ball> dropingBalls = new ArrayList<>();

    private static BallManager instance;

    public static BallManager getInstance(){
        if (instance == null) {
            synchronized (BallManager.class) {
                if (instance == null) {
                    instance = new BallManager();
                }
            }
        }
        return instance;
    }

    public void addBall(Ball ball) {
        dropBalls.add(ball);
    }


    public void setGradient(Gradient gradient) {
        for (int i=0;i<dropBalls.size();i++) {
            dropBalls.get(i).setGradient(gradient.copySelf());
        }

        //开始定时Droping中放入小球
        new Thread(){
            @Override
            public void run() {
                for (int i = 0;i<dropBalls.size();i++) {
                    dropingBalls.add(dropBalls.get(i));
                    LogUtil.e("dropingBalls", "size = " + dropingBalls.size());
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    /**
     * 绘制小球
     * @param c
     * @param paint
     */
    public void drawOn(Canvas c, Paint paint) {
        for (int i = 0; i <dropingBalls.size() ; i++) {
            Ball tmpBall = dropingBalls.get(i);
            if (tmpBall.isBottom()) {
                dropingBalls.remove(tmpBall);
                i--;
            } else {
                dropingBalls.get(i).drawOn(c, paint);
            }
        }
    }

    public boolean isDropFinshed() {
        return dropingBalls.size() == 0;
    }

    public void resetAllBalles() {
        for (int i = 0; i < dropBalls.size(); i++) {
            dropBalls.get(i).reset();
        }
    }
}
