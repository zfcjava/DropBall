package com.example.zfc.dropball.stone;

import com.example.zfc.dropball.BallApplication;
import com.example.zfc.dropball.Point;
import com.example.zfc.dropball.utils.LogUtil;
import com.example.zfc.dropball.utils.UIAdapterUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zfc on 2018/5/6.
 */

public class StonePositionGenerater {

    Random random = new Random();
    int[] xPositons = new int[5];

    public int WINDOW_WIDTH;
    public int WINDOW_HEIGHT;


    public StonePositionGenerater() {
        WINDOW_WIDTH = UIAdapterUtil.getWindowWidth(BallApplication.getINSTANCE());
        WINDOW_HEIGHT = UIAdapterUtil.getWindowHeight(BallApplication.getINSTANCE());
        resetXPositions();
    }


    public void resetXPositions(){
        for (int i = 0;i<xPositons.length;i++) {
            xPositons[i] = -1;
        }
    }

    public List<Point> getStonePosition(){

        List<Point> points = new ArrayList<>();

        int num = 0;
        while (num == 0) { //生成该排stone的个数
            num = random.nextInt(4);
        }

        resetXPositions();
        for (int i=0;i<num;i++) {
            int tmpX = 0;

            while (tmpX <= 0) { //label1
                tmpX = random.nextInt(WINDOW_WIDTH);
            }

            if (positionIsLeggal(tmpX)) {
                xPositons[i] = tmpX;
            } else {
                i--;  //相当于goto  label1
            }
        }

        for (int i=0;i<num;i++) {
            Point p = new Point(xPositons[i], WINDOW_HEIGHT - StoneConfig.SPACE_ROWS);
            points.add(p);
        }

        LogUtil.e("positions ", "positions = " + points.toString());

        return points;
    }


    private boolean positionIsLeggal(int tmpX) {
        for (int i=0;i<xPositons.length;i++) {
            if (xPositons[i] > 0) {
                if (Math.abs(xPositons[i] - tmpX) < StoneConfig.SPACE_LINE) {
                    return false;
                }
            }
        }
        return true;
    }
}
