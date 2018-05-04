package com.example.zfc.dropball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.RelativeLayout;
import com.example.zfc.dropball.controller.SurfaceViewController;
import com.example.zfc.dropball.controller.TouchProcessController;
import com.example.zfc.dropball.utils.ITager;
import com.example.zfc.dropball.utils.StatusBarUtils;
import com.example.zfc.dropball.utils.UIAdapterUtil;

public class MainActivity extends AppCompatActivity implements ITager{

    private RelativeLayout header;
    private Point startPoint;
    private Point endPoint;
    private RelativeLayout rl_content;
    private SurfaceView surfaceView;
    private SurfaceViewController surfaceViewController;
    private TouchProcessController touchProcessController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtils.setStatusBarColor(this, R.color.color_transparent);
        //设置状态栏字体为深色模式
        StatusBarUtils.statusBarDarkMode(this);
        initView();
        initController();
        initListener();
    }



    private void initView() {
        header = findViewById(R.id.header);
        //获取ball的出发点坐标（header中心坐标）
        initBallStartPoint();
        surfaceView = findViewById(R.id.surfaceView);
        rl_content = findViewById(R.id.rl_content);
    }

    private void initBallStartPoint() {
        startPoint = new Point();
        startPoint.x = UIAdapterUtil.getWindowWidth(getApplicationContext()) / 2;
        startPoint.y = header.getLayoutParams().height / 2;
    }

    private void initController() {
        surfaceViewController = new SurfaceViewController(surfaceView);
        touchProcessController = new TouchProcessController(rl_content, new TouchProcessController.TouchListener() {
            @Override
            public void onTouchPositionChanged(Point endPoint) {
                updatePoint(endPoint);
                if (surfaceViewController != null) {
                    surfaceViewController.doDraw(endPoint);
                }
            }

            @Override
            public void onTouchEnd(Point endPoint) {
                updatePoint(endPoint);
                if (surfaceViewController != null) {
                    surfaceViewController.doDraw(endPoint);
                }
            }
        });
    }

    /**
     * 将坐标点减去状态栏的高度
     * @param endPoint
     */
    private void updatePoint(Point endPoint) {
        if (endPoint.y <= UIAdapterUtil.getStatusBar(this)) {
            endPoint.y = 0;
        } else {
            endPoint.y = endPoint.y - UIAdapterUtil.getStatusBar(this);
        }
    }


    private void initListener() {

    }

    @Override
    public String getTag() {
        return getClass().getSimpleName();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (surfaceViewController != null) {
            surfaceViewController.release();
            surfaceViewController = null;
        }

        if (touchProcessController != null) {
            touchProcessController.release();
            touchProcessController = null;
        }
    }
}
