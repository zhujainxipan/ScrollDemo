package com.example.annuoaichengzhang.scrolldemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by niehongtao on 16/3/28.
 */
public class DragView1 extends View {

    int lastX = 0;
    int lastY = 0;

    public DragView1(Context context) {
        super(context);
    }

    public DragView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                // 计算偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) getLayoutParams();
                lp.leftMargin = getLeft() + offsetX;
                lp.topMargin = getTop() + offsetY;
//                lp.rightMargin = getRight() +offsetX;
//                lp.bottomMargin = getBottom() + offsetY;
                setLayoutParams(lp);
                // 重新设置初始化坐标
//                lastY = y;
//                lastX = x;
                break;
        }
        return true;
    }

}
