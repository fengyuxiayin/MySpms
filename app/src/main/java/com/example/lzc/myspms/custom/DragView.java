package com.example.lzc.myspms.custom;

/**
 * Created by LZC on 2018/2/23.
 */
import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
public class DragView extends RelativeLayout implements View.OnClickListener {
    private View fgView , bgView;
    private ViewDragHelper mDrager;
    private DragStateListener mDragStateListener;
    private final int DRAG_LEFT = -1 , DRAG_RIGHT = 1;
    private int dragMode = DRAG_LEFT;
    private float minX , maxX;
    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mDrager = ViewDragHelper.create(this, 5f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == fgView;
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                return bgView.getMeasuredWidth();
            }


            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return getPositionX(left);
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return 0;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if(Math.abs(fgView.getLeft()) != 0 || Math.abs(fgView.getLeft()) != bgView.getMeasuredWidth()){
                    float x = fgView.getLeft() + 0.1f * xvel;
                    mDrager.smoothSlideViewTo(fgView,
                            Math.abs(getPositionX(x)) > bgView.getMeasuredWidth() / 2 ? bgView.getMeasuredWidth() * dragMode : 0, 0);
                    postInvalidate();
                }

            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
                if(changedView == fgView)
                    getParent().requestDisallowInterceptTouchEvent(fgView.getLeft() != 0 ? true : false);
                if(mDragStateListener != null){
                    if(left == 0){
                        mDragStateListener.onClosed(DragView.this);
                    }else if(Math.abs(left) == bgView.getMeasuredWidth()){
                        mDragStateListener.onOpened(DragView.this);
                    }
                }
            }
        });
    }

    public View getForegroundView() {
        return fgView;
    }

    public View getBackgroundView() {
        return bgView;
    }

    public void open(){
        fgView.offsetLeftAndRight(dragMode * (bgView.getMeasuredWidth() - fgView.getLeft()));
    }
    public void close(){
        fgView.offsetLeftAndRight(-fgView.getLeft());
    }
    public void openAnim(){
        mDrager.smoothSlideViewTo(fgView, bgView.getMeasuredWidth() * dragMode, 0);
        postInvalidate();
    }
    public void closeAnim(){
        mDrager.smoothSlideViewTo(fgView, 0, 0);
        postInvalidate();
    }

    public boolean isOpen(){
        return Math.abs(fgView.getLeft()) == bgView.getMeasuredWidth();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //drag range
        if(dragMode == DRAG_LEFT){
            minX = -bgView.getMeasuredWidth();
            maxX = 0;
        }else{
            minX = 0;
            maxX = bgView.getMeasuredWidth();
        }
    }
    private int getPositionX(float x){
        if(x < minX) x = minX;
        if(x > maxX) x = maxX;
        return (int) x;
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(getChildCount() != 2)
            throw new IllegalArgumentException("must contain only two child view");
        fgView = getChildAt(1);
        bgView = getChildAt(0);
        if(!(fgView instanceof ViewGroup && bgView instanceof ViewGroup))
            throw new IllegalArgumentException("ForegroundView and BackgoundView must be a subClass of ViewGroup");
        RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams)bgView.getLayoutParams();
        param.addRule(dragMode == DRAG_LEFT ? RelativeLayout.ALIGN_PARENT_RIGHT : RelativeLayout.ALIGN_PARENT_LEFT);
        param.width = LayoutParams.WRAP_CONTENT;

        //bind onClick Event
        fgView.setOnClickListener(this);
        int bgViewCount = ((ViewGroup)bgView).getChildCount();
        for (int i = 0; i < bgViewCount; i++) {
            View child = ((ViewGroup) bgView).getChildAt(i);
            if(child.isClickable()) child.setOnClickListener(this);
        }
    }

    @Override
    public void computeScroll() {
        if(mDrager.continueSettling(true)){
            postInvalidate();
        }
    }

    public void setOnDragStateListener(DragStateListener listener){
        mDragStateListener = listener;
    }

    @Override
    public void onClick(View v) {
        if(mDragStateListener != null) {
            if(v == fgView){
                if(isOpen()){
                    closeAnim();
                    return;
                }
                mDragStateListener.onForegroundViewClick(DragView.this , v);
            }else {
                mDragStateListener.onBackgroundViewClick(DragView.this , v);
            }
        }
    }

    public interface DragStateListener{
        void onOpened(DragView dragView);
        void onClosed(DragView dragView);
        void onForegroundViewClick(DragView dragView, View v);
        void onBackgroundViewClick(DragView dragView, View v);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDrager.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDrager.processTouchEvent(event);
        return true;
    }
}
