package com.example.zfc.dropball.draw;

import android.graphics.Canvas;

import com.example.zfc.dropball.Point;
import com.example.zfc.dropball.controller.SurfaceViewController;
import com.example.zfc.dropball.inter.IReleaseable;

/**
 * Created by zfc on 2018/5/5.
 */

public class DrawManager implements IReleaseable{

    public static final int LINE = 1;
    public static final int DROP = 2;

    private  BaseDrawProcessor drawDropProcessor;
    private BaseDrawProcessor drawLineProcessor;
    private BaseDrawProcessor drawStoneProcessor;



    public DrawManager(Point startPoint, SurfaceViewController surfaceViewController) {
        this.drawLineProcessor = new DrawLineProcessor(startPoint);
        this.drawDropProcessor = new DrawDropProcessor(startPoint, surfaceViewController);
        this.drawStoneProcessor = new DrawStoneProcessor(startPoint);
    }

    public void doDraw(Canvas c, Point endPoint, int strategy) {
        switch (strategy) {
            case LINE:
                drawLineProcessor.doDraw(c, endPoint);
                break;
            case DROP:
                drawDropProcessor.doDraw(c, endPoint);
                break;
        }
        drawStoneProcessor.doDraw(c, endPoint);
    }


    /**
     * 是否正在绘制下降时间
     * @return
     */
    public boolean isDrawingDrop() {
        if (drawDropProcessor == null) {
            return false;
        }
        return drawDropProcessor.isDrawingDrop();
    }

    @Override
    public void release() {
        if (drawDropProcessor != null) {
            drawDropProcessor.release();
            drawDropProcessor = null;
        }

        if (drawLineProcessor != null) {
            drawLineProcessor.release();
            drawLineProcessor = null;
        }
    }
}
