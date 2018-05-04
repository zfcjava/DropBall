package com.example.zfc.dropball.controller;

import android.view.View;

import com.example.zfc.dropball.inter.IReleaseable;

/**
 * Created by zfc on 2018/5/4.
 */

public class BaseController<T extends View> implements IReleaseable {

    protected T subRootView;

    public BaseController(T subRootView) {
        this.subRootView = subRootView;
    }

    @Override
    public void release() {
        if (subRootView != null) {
            subRootView = null;
        }
    }
}
