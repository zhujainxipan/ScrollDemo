package com.example.annuoaichengzhang.scrolldemo.qunyingzhuan;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by niehongtao on 16/3/28.
 */
public class DragView extends View {

    int lastX = 0;
    int lastY = 0;

    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        Log.d("dragwiew", "onTouchEvent");

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("dragwiew", "ACTION_DOWN");
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("dragwiew", "ACTION_HOVER_MOVE");

                // 计算偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
                Log.d("dragwiew", getLeft() + "");
                // !!!必须加，不加就会出问题
//                // 重新设置初始化坐标
                lastY = y;
                lastX = x;
                break;
        }
        return true;
    }
}
