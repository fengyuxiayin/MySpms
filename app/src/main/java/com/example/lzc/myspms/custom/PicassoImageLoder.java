package com.example.lzc.myspms.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.youth.banner.loader.ImageLoader;

import static com.example.lzc.myspms.R.mipmap.view;

/**
 * Created by LZC on 2018/5/21.
 */

public class PicassoImageLoder extends ImageLoader {

    private int screenWidth;
    private double targetWidth;

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        if (path!=null) {
            String path1 = (String) path;
//            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//            DisplayMetrics displayMetrics = new DisplayMetrics();
//            wm.getDefaultDisplay().getMetrics(displayMetrics);
//            //获得屏幕宽度
//            screenWidth = displayMetrics.heightPixels;
//            targetWidth = screenWidth /8;
//            if (path1.length()>0) {
//                Transformation transformation =
//                        new Transformation() {
//                            @Override
//                            public Bitmap transform(Bitmap source) {
//                                if (source.getWidth() == 0 || source.getHeight() == 0) {
//                                    return source;
//                                }
//                                //得到图片宽高比,每个参数必须强转成double型
//                                double ratio = (double) source.getWidth() / (double) source.getHeight();
//
//                                Bitmap bitmapResult=null;
//                                if(source!=null){
//                                    bitmapResult = Bitmap.createScaledBitmap(source, (int) (targetWidth +0.5), (int) ((targetWidth / ratio)+0.5), false);
//                                }
//                                if (source != bitmapResult) {
//                                    source.recycle();
//                                }
//
//                                return bitmapResult;
//                            }
//
//                            @Override
//                            public String key() {
//                                return "transformation" + screenWidth ;
//                            }
//                        };
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                Picasso.with(context).load((String)path).into(imageView);
//            }
        }
    }
}
