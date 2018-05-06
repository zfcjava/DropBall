package com.example.zfc.dropball.stone;

import com.example.zfc.dropball.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zfc on 2018/5/6.
 */

public class StoneGenerator {

    private StonePositionGenerater positionGenerater;
    Random r = new Random();

    public StoneGenerator() {
        positionGenerater = new StonePositionGenerater();
    }


    public List<Stone> generateStone() {
        List<Stone> stones = new ArrayList<>();

        List<Point> points = positionGenerater.getStonePosition();


        for (int i = 0; i < points.size(); i++) {
            Stone tmp = generateRandomStone(points.get(i));
            stones.add(tmp);
        }

        return stones;
    }

    private Stone generateRandomStone(Point center) {

        switch (r.nextInt(3)) {
            case 0:
                return new CircleStone(center);
            case 1:
                return new SquareStone(center);
            case 2:
                return new TriangleStone(center);
            default:
                return new TriangleStone(center);

        }


    }
}
