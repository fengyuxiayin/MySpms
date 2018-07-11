package com.example.lzc.myspms.custom;

/**
 * Created by LZC on 2018/3/28.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.ViewfinderView;

import java.util.ArrayList;
import java.util.List;
public class ZxingFinderView extends ViewfinderView{
    public int laserLinePosition=0;
    public float[] position=new float[]{0f,0.5f,1f};
    public int[] colors=new int[]{0x00ffffff,0xffffffff,0x00ffffff};
    public LinearGradient linearGradient ;
    public ZxingFinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 重写draw方法绘制自己的扫描框
     * @param canvas
     */
    @Override
    public void onDraw(Canvas canvas) {
        refreshSizes();
        if (framingRect == null || previewFramingRect == null) {
            return;
        }

        Rect frame = framingRect;
        Rect previewFrame = previewFramingRect;

        int width = canvas.getWidth();
        int height = canvas.getHeight();
        //绘制4个角

        paint.setColor(0xFFFFFFFF);//定义画笔的颜色
        canvas.drawRect(frame.left, frame.top, frame.left+70, frame.top+10, paint);
        canvas.drawRect(frame.left, frame.top, frame.left + 10, frame.top + 70, paint);

        canvas.drawRect(frame.right-70, frame.top, frame.right, frame.top+10, paint);
        canvas.drawRect(frame.right-10, frame.top, frame.right, frame.top+70, paint);

        canvas.drawRect(frame.left, frame.bottom-10, frame.left+70, frame.bottom, paint);
        canvas.drawRect(frame.left, frame.bottom-70, frame.left+10, frame.bottom, paint);

        canvas.drawRect(frame.right-70, frame.bottom-10, frame.right, frame.bottom, paint);
        canvas.drawRect(frame.right-10, frame.bottom-70, frame.right, frame.bottom, paint);
        // Draw the exterior (i.e. outside the framing rect) darkened
        paint.setColor(resultBitmap != null ? resultColor : maskColor);
        canvas.drawRect(0, 0, width, frame.top, paint);
        canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
        canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1, paint);
        canvas.drawRect(0, frame.bottom + 1, width, height, paint);

        if (resultBitmap != null) {
            // Draw the opaque result bitmap over the scanning rectangle
            paint.setAlpha(CURRENT_POINT_OPACITY);
            canvas.drawBitmap(resultBitmap, null, frame, paint);
        } else {
            //  paint.setAlpha(SCANNER_ALPHA[scannerAlpha]);
            //  scannerAlpha = (scannerAlpha + 1) % SCANNER_ALPHA.length;
            int middle = frame.height() / 2 + frame.top;
            laserLinePosition=laserLinePosition+5;
            if(laserLinePosition>frame.height())
            {
                laserLinePosition=0;
            }
            linearGradient= new LinearGradient(frame.left + 1, frame.top+laserLinePosition , frame.right -1 , frame.top +10+laserLinePosition, colors, position, Shader.TileMode.CLAMP);
            // Draw a red "laser scanner" line through the middle to show decoding is active

            //  paint.setColor(laserColor);
            paint.setShader(linearGradient);
            //绘制扫描线
            canvas.drawRect(frame.left + 1, frame.top+laserLinePosition , frame.right -1 , frame.top +10+laserLinePosition, paint);
            paint.setShader(null);
            float scaleX = frame.width() / (float) previewFrame.width();
            float scaleY = frame.height() / (float) previewFrame.height();

            List<ResultPoint> currentPossible = possibleResultPoints;
            List<ResultPoint> currentLast = lastPossibleResultPoints;
            int frameLeft = frame.left;
            int frameTop = frame.top;
            if (currentPossible.isEmpty()) {
                lastPossibleResultPoints = null;
            } else {
                possibleResultPoints = new ArrayList<>(5);
                lastPossibleResultPoints = currentPossible;
                paint.setAlpha(CURRENT_POINT_OPACITY);
                paint.setColor(resultPointColor);
                for (ResultPoint point : currentPossible) {
                    canvas.drawCircle(frameLeft + (int) (point.getX() * scaleX),
                            frameTop + (int) (point.getY() * scaleY),
                            POINT_SIZE, paint);
                }
            }
            if (currentLast != null) {
                paint.setAlpha(CURRENT_POINT_OPACITY / 2);
                paint.setColor(resultPointColor);
                float radius = POINT_SIZE / 2.0f;
                for (ResultPoint point : currentLast) {
                    canvas.drawCircle(frameLeft + (int) (point.getX() * scaleX),
                            frameTop + (int) (point.getY() * scaleY),
                            radius, paint);
                }
            }
            postInvalidateDelayed(16,
                    frame.left ,
                    frame.top ,
                    frame.right ,
                    frame.bottom);
            // postInvalidate();

        }
    }
}
