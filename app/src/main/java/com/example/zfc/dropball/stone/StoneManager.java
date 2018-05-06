package com.example.zfc.dropball.stone;

import android.graphics.Canvas;

import com.example.zfc.dropball.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zfc on 2018/5/6.
 */

public class StoneManager {

    private static StoneManager instance;
    List<Stone> stones = new ArrayList<>(); //用于存储现有的石头
    StoneGenerator stoneGenerator;

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
        stoneGenerator = new StoneGenerator();
        getRowStone();
    }


    public void getRowStone(){
        //之前的石头上升一排
        LogUtil.e("GAME", "PRE --" + stones.toString());
        goUpARow(stones);
        LogUtil.e("GAME", "UPLOAD ---");
        List<Stone> newStones = stoneGenerator.generateStone();
        LogUtil.e("GAME", "NEW --" + newStones.toString());
        stones.addAll(newStones);
        LogUtil.e("GAME", "POST --" + stones.toString());
    }

    private void goUpARow(List<Stone> stones) {
        if (stones.size() == 0) {
            return;
        }
        for (Stone stone : stones) {
            if(!stone.doGoUpARow()){
                LogUtil.e("GAME", "GAME OVER");
                return;
            }
        }
    }

    public void doDraw(Canvas c) {
        for (int i = 0; i < stones.size(); i++) {
            stones.get(i).doDraw(c);
        }
    }


}
