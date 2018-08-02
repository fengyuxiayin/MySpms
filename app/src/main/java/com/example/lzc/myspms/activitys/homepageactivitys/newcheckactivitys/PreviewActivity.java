package com.example.lzc.myspms.activitys.homepageactivitys.newcheckactivitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.widget.LinearLayout;

import com.example.lzc.myspms.R;

import es.voghdev.pdfviewpager.library.PDFViewPager;
import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

public class PreviewActivity extends AppCompatActivity implements DownloadFile.Listener {
public static final String TAG = PreviewActivity.class.getSimpleName();
    private PDFViewPager pdfViewPager;
    private String url;
    private RemotePDFViewPager remotePDFViewPager;
    private PDFPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        url = getIntent().getStringExtra("url");
        pdfViewPager = (PDFViewPager) findViewById(R.id.pdf);
        setDownloadListener();
    }
    /*设置监听*/
    protected void setDownloadListener() {
        final DownloadFile.Listener listener = this;
        Log.e(TAG, "setDownloadListener: "+url );
        remotePDFViewPager = new RemotePDFViewPager(this, url, listener);
        remotePDFViewPager.setId(R.id.pdf);
        setContentView(remotePDFViewPager);
    }

    @Override
    public void onSuccess(String url, String destinationPath) {
        Log.e(TAG, "onSuccess: " );
        adapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        int count = adapter.getCount();
        Log.e(TAG, "onSuccess: "+count );
        remotePDFViewPager.setAdapter(adapter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            super.onTouchEvent(event);
        } catch (IllegalArgumentException  e) {
            Log.e( "ImageOriginPager-error" , "IllegalArgumentException 错误被活捉了！");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            return super.dispatchTouchEvent(ev);

        } catch (IllegalArgumentException  e) {
            Log.e( "ImageOriginPager-error" , "IllegalArgumentException 错误被活捉了！");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void onFailure(Exception e) {
        Log.e(TAG, "onFailure: "+e.getMessage() );
    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }
}



