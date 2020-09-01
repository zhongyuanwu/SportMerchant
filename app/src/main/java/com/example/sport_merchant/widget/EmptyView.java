package com.example.sport_merchant.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.sport_merchant.R;

/**
 * created by jump on 2019/10/14.
 * describe:
 */
public class EmptyView extends ConstraintLayout {

    private TextView titleTv;
    private TextView tv_action;
    private ImageView ivBlank;

    public EmptyView(@NonNull Context context) {
        this(context, null);
    }

    public EmptyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_blank, this);
        ivBlank = findViewById(R.id.iv_blank);
        titleTv = findViewById(R.id.tv_blank_title);
        tv_action = findViewById(R.id.tv_action);
    }

    public void show(String titleTxt) {
        setVisibility(View.VISIBLE);
        titleTv.setText(titleTxt);
    }

    public void show(String titleTxt, int imgId) {
        ivBlank.setImageResource(imgId);
        setVisibility(View.VISIBLE);
        titleTv.setText(titleTxt);
    }

    public void hide() {
        setVisibility(View.GONE);
    }

    public void setTitleTv(String str) {
        titleTv.setText(str);
    }

    public EmptyView setBtnTextAndVisibility(String str, int visibility, OnClickListener listener) {
        tv_action.setText(str);
        tv_action.setVisibility(visibility);
        tv_action.setOnClickListener(listener);
        return this;
    }

    public EmptyView init() {
        tv_action.setVisibility(View.GONE);
        return this;
    }
}
