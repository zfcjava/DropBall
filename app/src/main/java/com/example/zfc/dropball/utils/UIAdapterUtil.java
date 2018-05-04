package com.example.zfc.dropball.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * 这个实做UI适配的帮助类
 */
public class UIAdapterUtil {


    private static int windowHeightWithStatusBar;

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getWindowWidth(Context context){
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }


    /**
     * 获取视频播放的理论高度
     */

    public static int getRelativeHeght(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return (int) (width * 1f / 750 * 422 + 0.5f);
    }
    /**
     * 获取屏幕高度
     * @param context
     * @return
     */
    public static int getWindowHeight(Context context){
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    /** 
     * 根据手机的分辨率dp 转成(像素)
     */  
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    /** 
     *根据手机的分辨率px(像素) 的单转成dp 
     */  
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
    /**
     * 将px值转换为sp值，保证文字大小不变
     * 
     * @param pxValue
     * @param fontScale（DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(float pxValue, float fontScale) {
     return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * 
     * @param spValue
     * @param fontScale（DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(float spValue, float fontScale) {
    	
     return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取带上状态栏的屏幕高度
     * @param context
     * @return
     */
    public static int getWindowHeightWithStatusBar(Context context) {
        return getWindowHeight(context) + getStatusBar(context);
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBar(Context context) {
        int statusBarHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
            LogUtil.e("UIAdapterUtil", "status bar height dynamic " + statusBarHeight);
        } else {
            //默认就是25dp
            LogUtil.e("UIAdapterUtil", "status bar height default " + statusBarHeight);
            statusBarHeight = UIAdapterUtil.dip2px(context, 25);
        }
        return statusBarHeight;
    }

    public static float getScale(Context context) {
        if ((UIAdapterUtil.getWindowWidth(context) / 1080f) > UIAdapterUtil.getWindowHeight(context) / 1920f) {
            return UIAdapterUtil.getWindowHeight(context) / 1920f;
        } else {
            return UIAdapterUtil.getWindowWidth(context) / 1080f;
        }
    }

    /**
     * 自适应
     * @param context
     * @return
     */
    public static float getReletiveIosByPixel(Context context) {
        return getWindowWidth(context) * 1f / 750;
    }
}

