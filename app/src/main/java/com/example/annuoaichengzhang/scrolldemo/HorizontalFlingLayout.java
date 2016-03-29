package com.example.annuoaichengzhang.scrolldemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by niehongtao on 16/3/29.
 */
public class HorizontalFlingLayout extends LinearLayout {

    private Scroller mScroller;
    private float mLastX;
    private float mLastY;
    private View mLeftView;
    private View mRightView;

    public HorizontalFlingLayout(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        mScroller = new Scroller(context);
    }

    public HorizontalFlingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HorizontalFlingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 2) {
            throw new RuntimeException("only need two child view");
        }
        mLeftView = getChildAt(0);
        mRightView = getChildAt(1);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = ev.getX();
                mLastY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float offsetX = ev.getX() - mLastX;
                float offsetY = ev.getY() - mLastY;
                if (Math.abs(offsetX - Math.abs(offsetY)) > ViewConfiguration.getTouchSlop()) {
                    int offset = (int) -offsetX;
                    if (getScaleX() + offset > mRightView.getWidth() || getScaleX() + offset < 0) {
                        return true;
                    }
                    this.scrollBy(offset, 0);
                    mLastX = ev.getX();
                    mLastY = ev.getY();
                    return true;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                int offset = ((getScrollX() / (float)(mRightView.getWidth())) > 0.5) ? mRightView.getWidth() : 0;
                mScroller.startScroll(this.getScrollX(), this.getScrollY(), offset - this.getScrollX(), 0);
                invalidate();
                mLastX = 0;
                mLastY = 0;
                break;

        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
        }
        invalidate();
    }
}
