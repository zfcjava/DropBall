package com.example.zfc.dropball;

/**
 * Created by zfc on 2018/5/5.
 */

public class Gradient {

    public float dx;
    public float dy;
    public float ds; //斜边

    public Gradient(float dx, float dy, float distance) {
        this.dx = dx;
        this.dy = dy;
        this.ds = distance;
    }

    public Gradient(Gradient gradient) {
        this.dx = gradient.dx;
        this.dy = gradient.dy;
        this.ds = gradient.ds;
    }

    /**
     * 深度拷贝自己
     * @return
     */
    public Gradient copySelf() {
        return new Gradient(this);
    }
}
