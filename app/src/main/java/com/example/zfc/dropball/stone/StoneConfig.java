package com.example.zfc.dropball.stone;

import com.example.zfc.dropball.BallApplication;
import com.example.zfc.dropball.utils.UIAdapterUtil;

/**
 * Created by zfc on 2018/5/6.
 */

public class StoneConfig {

    public static float bottomY;
    {
        bottomY = UIAdapterUtil.getWindowHeight(BallApplication.getINSTANCE().getApplicationContext()) - SPACE_ROWS;
    }

    public static float topY = 20f;

    public static final float SPACE_ROWS = 120f;

    public static final float SPACE_LINE = 80f;

    public static final float STONE_CIRCLE_RADIUS = 30f;

    public static final float STONE_SQUARE_SIDE_LENGHT = 60;


    public static final float STONE_TRIANGLE_SIDE_LENGHT = 60;

    public static final float genhao3 = 1.73205f;




}
