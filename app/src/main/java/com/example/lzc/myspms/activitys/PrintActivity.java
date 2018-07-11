package com.example.lzc.myspms.activitys;

import android.content.Context;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.adapters.MyPrintAdapter;
import com.example.lzc.myspms.utils.FileUtils;

import org.w3c.dom.Document;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class PrintActivity extends AppCompatActivity {

    public static final String TAG = PrintActivity.class.getSimpleName();
    //文件存储位置
    private String docPath = "";
    //文件名称
    private String docName = "";
    //html文件存储位置
    private String savePath = "/mnt/sdcard/doc/";
    //    private String savePath = Environment.getExternalStorageDirectory().getPath()+"/doc/";
    private String filePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        filePath = getIntent().getStringExtra("filePath");
        Log.e(TAG, "onCreate: "+filePath );
        //WebView加载显示本地html文件
//        WebView webView = (WebView) this.findViewById(R.id.activity_print_wv);
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setSupportZoom(true);
//        webSettings.setBuiltInZoomControls(true);
//        webView.loadUrl(filePath);
//        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
//        printManager.print("Print  webview", webView.createPrintDocumentAdapter(), null);
        onPrintPdf();
    }
    private void onPrintPdf() {
        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
        PrintAttributes.Builder builder = new PrintAttributes.Builder();
        builder.setColorMode(PrintAttributes.COLOR_MODE_COLOR);
        printManager.print("test pdf print", new MyPrintAdapter(this,"http://120.25.251.167:8088/hfs/source/document/20185/T3.pdf"), builder.build());
    }


}
