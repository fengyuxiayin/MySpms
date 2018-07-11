package com.example.lzc.myspms.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.example.lzc.myspms.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2018/6/12.
 */

public class BarChartView extends View {
    public static final String TAG = BarChartView.class.getSimpleName();

    private float itemWidth = 310;
    private float itemHeight = 310;

    private float barWidth; //默认柱子的宽度
    private float barHeight; //柱子的高度
    private float cellHeigh = 1;  //按照list的最大值算出的1点的height
    private float widthSpace; //默认柱子的间距
    private float viewMargin = 20; //整个view的外边距

    private float textHeight; //显示文字高度

    private Paint colorBarPaint; //柱状图画笔
    private TextPaint textPaint; //文字画笔

    private RectF barRectf;

    private List<BarGraphInfo> mList;

    //共有多少个柱状图
    private int barCount;

    private float startX;
    private float startY;


    //不同分辨率的比例 这个好像没用上
    private float scaleWidth = 1;
    private float scaleHeight = 1;

    private float maxBarHeight;//最大的柱状图高度

    //最小的柱子的高度
    private float minBarHeight = 99999;


    private int defualtColor = Color.parseColor("#ffffff"); //默认白色
    private RectF textRectF;
    private RectF numRectF;

    public BarChartView(Context context) {
        this(context, null);
    }

    public BarChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //获取不同屏幕的比例大小  这后来想想取比例没什么意义
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        scaleWidth = width / 1920;
        scaleHeight = height / 1080;
        init();
    }

    public BarChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    private void init() {
        //初始化数据
        //默认初始化数据
        mList = new ArrayList<>();
        mList.add(new BarGraphInfo(Color.WHITE,"默认值",100));
        Log.e("BarChartView", "init: "+mList.size() );
        //初始化画笔
        colorBarPaint = new Paint();
        colorBarPaint.setAntiAlias(true);
        //设置矩形
        barRectf = new RectF();
        textPaint = new TextPaint();
        //subtitle num
        textRectF = new RectF();
        numRectF = new RectF();
        //根据各个手机分辨率不同算出margin的间距大小
        viewMargin = scaleWidth > scaleHeight ? scaleWidth * viewMargin : scaleHeight * viewMargin;
        setList(mList);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            itemWidth = widthSize - (2 * viewMargin); //减去外边距
            startX = viewMargin; //起始位置定位到默认的
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            itemHeight = heightSize - (2 * viewMargin);
            startY = viewMargin;
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        Log.i("tag", "itemWidth" + itemWidth);

        Log.i("tag", "getMeasuredWidth()" + getMeasuredWidth());

        Log.i("tag", "viewMargin" + viewMargin);
        setList(mList);
    }

    private int totalTime = 100;
    private int currentTime = 0; //当前已绘制的次数

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        currentTime++;
        float left = startX + widthSpace;
        //画矩形
        for (int i = 0; i < barCount; i++) {
            //计算出高度
            barHeight = cellHeigh * mList.get(i).num;

            barHeight = barHeight / totalTime * currentTime;

            //设置颜色
            colorBarPaint.setColor(mList.get(i).color);


            float barLeft = left + i * barWidth;
            float top = (startY + textHeight + maxBarHeight) - barHeight;
            float right = left + (i + 1) * barWidth;
            float bottom = startY + textHeight + maxBarHeight;

            barRectf.set(barLeft, top, right, bottom);
            canvas.drawRect(barRectf, colorBarPaint);

            //画subTitle
            float subTitleLeft = left + i * barWidth;
            float subTitleTop = startY + textHeight + maxBarHeight;
            float subTitleRight = left + (i + 1) * barWidth;
            float subTitleBottom = startY + textHeight + maxBarHeight + textHeight;
            textRectF.set(subTitleLeft, subTitleTop, subTitleRight, subTitleBottom);
            drawText(canvas, textRectF, mList.get(i).subTitle, mList.get(i).color);


            //画num

            float numLeft = left + i * barWidth;
            float numTop = (startY + textHeight + maxBarHeight) - barHeight - textHeight;
            float numRight = left + (i + 1) * barWidth;
            float numBottom = (startY + textHeight + maxBarHeight) - barHeight;
            numRectF.set(numLeft, numTop, numRight, numBottom);
            drawText(canvas, numRectF, String.valueOf(mList.get(i).num), mList.get(i).color);
            left += barWidth;
        }

//画百分之50的线
//        float lineY = (startY + textHeight + maxBarHeight - (minBarHeight / totalTime * currentTime));
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setColor(Color.DKGRAY);
//        Path path = new Path();
//        path.moveTo(startX + 5, lineY);
//        path.lineTo(itemWidth + widthSpace, lineY);
//        PathEffect effects = new DashPathEffect(new float[]{15, 15}, 2);
//        paint.setPathEffect(effects);
//        canvas.drawPath(path, paint);


        if (currentTime < totalTime) {
            postInvalidate();
        }
    }


    //画文字居中
    private void drawText(Canvas canvas, RectF rectF, String text, int color) {
        if (color == 0) {
            textPaint.setColor(Color.BLACK);
        } else {
            textPaint.setColor(color);
        }
        textPaint.setTextSize(20);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);

        //该方法即为设置基线上那个点究竟是left,center,还是right  这里我设置为center
        textPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        int baseLineY = (int) (rectF.centerY() - top / 2 - bottom / 2);//基线中间点的y轴计算公式
//        StaticLayout layout = new StaticLayout(text, textPaint, 120, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
//        canvas.translate(rectF.left, rectF.top);
//        layout.draw(canvas);
        canvas.drawText(text, rectF.centerX(), baseLineY, textPaint);
    }


    public class BarGraphInfo {
        private int color;
        private String subTitle;
        private float num;

        public BarGraphInfo(int color, String subTitle, float num) {
            this.color = color;
            this.subTitle = subTitle;
            this.num = num;
        }
    }


    /**
     * 设置数据源
     *
     * @param list
     */
    public void setList(List<BarGraphInfo> list) {

        if (list == null && list.size() <= 0) {
            return;
        }
        mList = list;
        barCount = list.size();//多少个柱状图

        float avgWidth = itemWidth / barCount;
        barWidth = avgWidth / 2;  //获取状图的宽度
        widthSpace = avgWidth / 4;  //获取间隙

        float maxNum = 0;
        float minNum = list.get(0).num;
        for (BarGraphInfo info : mList) {
            //获取最大值
            if (info.num > maxNum) {
                maxNum = info.num;
            }
            if (info.num < minNum) {
                minNum = info.num;
            }
        }
        //获取柱状图最大显示区域  平分高度10份，6份用来显示最大柱状图，2份高度用来显示下面的文字,2份用来显示柱状图上面的文字
        maxBarHeight = (itemHeight / 10) * 6;
        textHeight = (itemHeight / 10) * 2; //文字高度

        cellHeigh = maxBarHeight / maxNum;  //平均每num = 1 时的高度

        minBarHeight = cellHeigh * minNum; //最小柱状图的高度
        postInvalidate();
    }
}