package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;
import android.util.Log;
import android.widget.Toast;

import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.CheckProjectActivity;
import com.example.lzc.myspms.models.CheckMessageModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.QyJsonModel;
import com.example.lzc.myspms.utils.DateUtil;
import com.example.lzc.myspms.utils.ValidateUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2018/5/22.
 */

public class MyPrintAdapter extends PrintDocumentAdapter {
public static final String TAG = MyPrintAdapter.class.getSimpleName();
    private Context context;
    private int pageHeight;
    private int pageWidth;
    private PdfDocument mPdfDocument;
    private int totalpages = 1;
    private String pdfPath;
    private List<Bitmap> mlist;
    private File file;
    private String fddbr;
    private String lxdh;
    private String jckssj;
    private String qymc;
    private String zgqx;
    private int problemCount;
    private String jcId;
    private Gson gson = new Gson();
    private String jclx;

    private CheckMessageModel.CheckMessageMsgModel checkMessageMsgModel ;

    public MyPrintAdapter(Context context,String pdfPath) {
        this.context = context;
        this.pdfPath = pdfPath;
    }

    public MyPrintAdapter(Context context, String pdfPath, String jcId,String jclx) {
        this.context = context;
        this.pdfPath = pdfPath;
        this.jcId = jcId;
        this.jclx = jclx;
        getCheckInfo();
    }
    private void getCheckInfo() {
        Log.e(TAG, "getCheckInfo: " + jcId);
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkReport/checkMessage")
                .addParams("id", jcId)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: /checkReport/checkMessage" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /checkReport/checkMessage" + response);
                        CheckMessageModel checkMessageModel = gson.fromJson(response, CheckMessageModel.class);
                        if (checkMessageModel.isData()) {
                            checkMessageMsgModel = gson.fromJson(checkMessageModel.getMsg(), CheckMessageModel.CheckMessageMsgModel.class);
                        } else {
                            Toast.makeText(context, checkMessageModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal,
                         final LayoutResultCallback callback,
                         Bundle metadata) {

        mPdfDocument = new PrintedPdfDocument(context, newAttributes); //创建可打印PDF文档对象

        pageHeight = newAttributes.getMediaSize().ISO_A4.getHeightMils() * 72 / 1000;  //设置尺寸
        pageWidth = newAttributes.getMediaSize().ISO_A4.getWidthMils() * 72 / 1000;

        if (cancellationSignal.isCanceled()) {
            callback.onLayoutCancelled();
            return;
        }
        OkHttpUtils.get()
                .url(pdfPath)
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(),"test.pdf") {
                    @Override
                    public void inProgress(float progress) {

                    }

                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(File response) {
                        MyPrintAdapter.this.file = response;
                        ParcelFileDescriptor mFileDescriptor = null;
                        PdfRenderer pdfRender = null;
                        PdfRenderer.Page page = null;
                        try {
                            mFileDescriptor = ParcelFileDescriptor.open(response, ParcelFileDescriptor.MODE_READ_ONLY);
                            if (mFileDescriptor != null)
                                pdfRender = new PdfRenderer(mFileDescriptor);

                            mlist = new ArrayList<>();

                            if (pdfRender.getPageCount() > 0) {
                                totalpages = pdfRender.getPageCount();
                                for (int i = 0; i < pdfRender.getPageCount(); i++) {
                                    if(null != page)
                                        page.close();
                                    page = pdfRender.openPage(i);
                                    Bitmap bmp = Bitmap.createBitmap(page.getWidth()*2,page.getHeight()*2, Bitmap.Config.ARGB_8888);
                                    page.render(bmp, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
                                    mlist.add(bmp);
                                }
                            }
                            if(null != page)
                                page.close();
                            if(null != mFileDescriptor)
                                mFileDescriptor.close();
                            if (null != pdfRender)
                                pdfRender.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (totalpages > 0) {
                            PrintDocumentInfo.Builder builder = new PrintDocumentInfo
                                    .Builder("快速入门.pdf")
                                    .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                                    .setPageCount(totalpages);  //构建文档配置信息

                            PrintDocumentInfo info = builder.build();
                            callback.onLayoutFinished(info, true);
                        } else {
                            callback.onLayoutFailed("Page count is zero.");
                        }
                    }
                });

    }

    @Override
    public void onWrite(final PageRange[] pageRanges, final ParcelFileDescriptor destination, final CancellationSignal cancellationSignal,
                        final WriteResultCallback callback) {
        for (int i = 0; i < totalpages; i++) {
            if (pageInRange(pageRanges, i)) //保证页码正确
            {
                PdfDocument.PageInfo newPage = new PdfDocument.PageInfo.Builder(pageWidth,
                        pageHeight, i).create();
                PdfDocument.Page page =
                        mPdfDocument.startPage(newPage);  //创建新页面

                if (cancellationSignal.isCanceled()) {  //取消信号
                    callback.onWriteCancelled();
                    mPdfDocument.close();
                    mPdfDocument = null;
                    return;
                }
                drawPage(page, i);  //将内容绘制到页面Canvas上
                mPdfDocument.finishPage(page);
            }
        }

        try {
            mPdfDocument.writeTo(new FileOutputStream(
                    destination.getFileDescriptor()));
        } catch (IOException e) {
            callback.onWriteFailed(e.toString());
            return;
        } finally {
            mPdfDocument.close();
            mPdfDocument = null;
        }

        callback.onWriteFinished(pageRanges);
    }

    private boolean pageInRange(PageRange[] pageRanges, int page) {
        for (int i = 0; i < pageRanges.length; i++) {
            if ((page >= pageRanges[i].getStart()) &&
                    (page <= pageRanges[i].getEnd()))
                return true;
        }
        return false;
    }

    //页面绘制（渲染）
    private void drawPage(PdfDocument.Page page,int pagenumber) {
        Canvas canvas = page.getCanvas();
        if(mlist != null){
            Paint paint = new Paint();
            Bitmap bitmap = mlist.get(pagenumber);
            int bitmapWidth = bitmap.getWidth();
            int bitmapHeight = bitmap.getHeight();
            // 计算缩放比例
            float scale = (float)pageWidth/(float)bitmapWidth;
            // 取得想要缩放的matrix参数
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            canvas.drawBitmap(bitmap,matrix,paint);
        }
    }

    @Override
    public void onFinish() {
        Gson gson = new Gson();
        if (checkMessageMsgModel!=null) {
            if (checkMessageMsgModel.getCheckInfo()!=null) {
                String qyJson = checkMessageMsgModel.getCheckInfo().getQyJson();
                QyJsonModel qyJsonModel = gson.fromJson(qyJson, QyJsonModel.class);
                if (qyJsonModel.getFddbr()!=null) {
                    this.lxdh = qyJsonModel.getLxdh();
                    if (ValidateUtil.isPhone(lxdh)) {
                        if ("2".equals(jclx)) {
                            sendSMS(qyJsonModel.getFddbr()+"，你名下/负责的企业\""+qyJsonModel.getQymc()+"\"在"+ DateUtil.long2Date(checkMessageMsgModel.getCheckInfo().getJckssj())+"的安全生产复查中，仍有"+checkMessageMsgModel.getProblemCount()+"项违法违规行为，拒不整改的，将根据《中华人民共和国安全生产法》等法律法规的有关规定依法进行处理。由此造成事故的，依法追究有关人员责任。检查结果详情请登录夏庄安全生产大数据平台企业端或纸质检查文书查看。",lxdh);
                        }else{
                            sendSMS(qyJsonModel.getFddbr()+"，你名下/负责的企业\""+qyJsonModel.getQymc()+"\"在"+ DateUtil.long2Date(checkMessageMsgModel.getCheckInfo().getJckssj())+"的安全生产检查中，有"+checkMessageMsgModel.getProblemCount()+"项违法违规行为，责令你公司于"+DateUtil.long2Date(checkMessageMsgModel.getCheckInfo().getZgqx())+"日前整改完毕。逾期不整改的，将根据《中华人民共和国安全生产法》等法律法规的有关规定依法进行处理。由此造成事故的，依法追究有关人员责任。检查结果详情请登录夏庄安全生产大数据平台企业端或纸质检查文书查看。",lxdh);
                        }
                    }else{
                        Toast.makeText(context, "请检查企业法人联系方式是否为合法手机号", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "该企业没有录入法人数据，无法发送短信", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(context, "检查信息为空，无法发送短信", Toast.LENGTH_SHORT).show();
            }
        }


        super.onFinish();

    }
    private  void sendSMS(String smsBody,String senfTo)
    {
        Uri smsToUri = Uri.parse("smsto:"+lxdh);
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.putExtra("sms_body", smsBody);
        context.startActivity(intent);
    }
}
