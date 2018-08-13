package com.example.lzc.myspms.activitys.homepageactivitys.newcheckactivitys;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.lzc.myspms.R;

import java.io.File;

import es.voghdev.pdfviewpager.library.PDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.BasePDFPagerAdapter;
import es.voghdev.pdfviewpager.library.asset.CopyAsset;
import es.voghdev.pdfviewpager.library.asset.CopyAssetThreadImpl;

public class PreviewSdActivity extends AppCompatActivity {
public static final String TAG = PreviewSdActivity.class.getSimpleName();
    PDFViewPager pdfViewPager;
    File pdfFolder;
    private String scnr;
    private String[] sampleAssets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_sd);
        scnr = getIntent().getStringExtra("scnr");
        sampleAssets = new String[]{"adobe.pdf", scnr};
        Log.e(TAG, "onCreate: scnr"+scnr );
        pdfFolder = Environment.getExternalStorageDirectory();
        copyAssetsOnSDCard();
    }
    protected void copyAssetsOnSDCard() {
        final Context context = this;
        CopyAsset copyAsset = new CopyAssetThreadImpl(getApplicationContext(), new Handler(), new CopyAsset.Listener() {
            @Override
            public void success(String assetName, String destinationPath) {
                pdfViewPager = new PDFViewPager(context, getPdfPathOnSDCard());
                setContentView(pdfViewPager);
            }

            @Override
            public void failure(Exception e) {
                e.printStackTrace();
//                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        for (String asset : sampleAssets) {
            copyAsset.copy(asset, new File(pdfFolder, asset).getAbsolutePath());
        }
    }

    protected String getPdfPathOnSDCard() {
        File f = new File(pdfFolder, scnr);
        return f.getAbsolutePath();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (pdfViewPager != null) {
            ((BasePDFPagerAdapter) pdfViewPager.getAdapter()).close();
        }
    }

    public static void open(Context context) {
        Intent i = new Intent(context, PreviewSdActivity.class);
        context.startActivity(i);
    }
}
