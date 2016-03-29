package com.example.annuoaichengzhang.scrolldemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by niehongtao on 16/3/28.
 */
public class DragView3 extends View {

    int lastX = 0;
    int lastY = 0;
    private Scroller mScroller;

    public DragView3(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        mScroller = new Scroller(context);
    }

    public DragView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DragView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            ((View)getParent()).scrollTo(
                    mScroller.getCurrX(),
                    mScroller.getCurrY()
            );
            invalidate();
        }
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
            case MotionEvent.ACTION_UP:
                View parent = (View) getParent();
                mScroller.startScroll(
                        parent.getScrollX(),
                        parent.getScrollY(),
                        -parent.getScrollX(),
                        -parent.getScrollY());
                invalidate();
                break;
        }
        return true;
    }

}
