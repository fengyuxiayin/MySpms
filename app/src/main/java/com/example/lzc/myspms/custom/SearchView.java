package com.example.lzc.myspms.custom;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lzc.myspms.R;

/**
 * Created by LZC on 2017/11/29.
 */

public class SearchView extends FrameLayout {
    private EditText mEtSearch;
    private ImageView mIvClear;
    private TextView mTvClose;
    private OnTextChangeListener mOnTextChangeListener;

    public EditText getEtSearch() {
        return mEtSearch;
    }

    public void setEtSearch(EditText etSearch) {
        this.mEtSearch = etSearch;
    }

    public ImageView getIvClear() {
        return mIvClear;
    }

    public void setIvClear(ImageView ivClear) {
        this.mIvClear = ivClear;
    }

    public TextView getTvClose() {
        return mTvClose;
    }

    public void setTvClose(TextView tvClose) {
        this.mTvClose = tvClose;
    }

    public void setOnTextChangeListener(OnTextChangeListener onTextChangeListener) {
        this.mOnTextChangeListener = onTextChangeListener;
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.layout_search_edittext, null);
        mEtSearch = (EditText) rootView.findViewById(R.id.et_search);
        mIvClear = (ImageView) rootView.findViewById(R.id.iv_clear);
        addView(rootView, new LinearLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        mIvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtSearch.setText("");
            }
        });
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    mIvClear.setVisibility(View.VISIBLE);
                } else {
                    mIvClear.setVisibility(View.GONE);
                }
                doTextChange(s);

            }
        });
    }

    private void doTextChange(Editable s) {
        if (mOnTextChangeListener != null) {
            mOnTextChangeListener.onTextChange(s);
        }
    }

    public interface OnTextChangeListener {
        void onTextChange(Editable s);
    }
}
