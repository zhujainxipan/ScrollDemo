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
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) getLayoutParams();
                lp.leftMargin = getLeft() + offsetX;
                lp.topMargin = getTop() + offsetY;
                // !!!不能都设置，肯定不能都设置  你想想，一个view，距左边控件和上边控件的距离都确定了，那它的位置就确定了
//                lp.rightMargin = getRight() +offsetX;
//                lp.bottomMargin = getBottom() + offsetY;

                Log.d("dragwiew11", getLeft() + "");

                setLayoutParams(lp);

                Log.d("dragwiew1", getLeft() + "");
                // 重新设置初始化坐标
                // !!!!不能写，因为getLeft()是一直不变的，所以我们只需要记录刚开始点击的那个坐标即可
//                lastY = y;
//                lastX = x;
                break;
        }
        return true;
    }

}
