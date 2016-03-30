package com.example.annuoaichengzhang.scrolldemo.qunyingzhuan;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by niehongtao on 16/3/28.
 */
public class DragView2 extends View {

    int lastX = 0;
    int lastY = 0;

    public DragView2(Context context) {
        super(context);
    }

    public DragView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // ！！！是getX，不是getRawX
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
                ((View)getParent()).scrollBy(- offsetX, -offsetY);

                // ??? 这里需要加进去吗
                // 重新设置初始化坐标
//                lastY = y;
//                lastX = x;

                break;
        }
        return true;
    }

}
