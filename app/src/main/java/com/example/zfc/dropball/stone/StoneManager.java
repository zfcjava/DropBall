package com.example.zfc.dropball.stone;

import android.graphics.Canvas;

import com.example.zfc.dropball.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zfc on 2018/5/6.
 */

public class StoneManager {

    private static StoneManager instance;
    List<Stone> stones = new ArrayList<>(); //用于存储现有的石头

    public static StoneManager getInstance() {
        if (instance == null) {
            synchronized (StoneManager.class) {
                if (instance == null) {
                    instance = new StoneManager();
                }
            }
        }
        return instance;
    }


    public StoneManager() {
        Stone tmp = new CircleStone(new Point(100, 100));
        stones.add(tmp);
        tmp = new SquareStone(new Point(100, 200));
        stones.add(tmp);
        tmp = new TriangleStone(new Point(100, 300));
        stones.add(tmp);
    }

    public void doDraw(Canvas c) {
        for (int i = 0; i < stones.size(); i++) {
            stones.get(i).doDraw(c);
        }
    }
}
