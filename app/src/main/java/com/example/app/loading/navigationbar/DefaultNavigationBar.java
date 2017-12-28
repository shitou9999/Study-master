package com.example.app.loading.navigationbar;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.loading.R;

/**
 * Created by PVer on 2017/6/25.
 */

public class DefaultNavigationBar<D extends DefaultNavigationBar.Builder.DefaultNavigationParams> extends AbsNavigationBar<DefaultNavigationBar.Builder.DefaultNavigationParams> {

    public DefaultNavigationBar(Builder.DefaultNavigationParams mParams) {
        super(mParams);
    }

//    public DefaultNavigationBar(Builder.AbsNavigationParams mParams) {
//        super(mParams);
//    }

    @Override
    public int bindLayoutId() {
        return R.layout.title_bar;
    }

    @Override
    public void applyView() {
        //绑定效果
        // 给我们的导航条绑定资源
//        setImageResource(R.id.iv_left, getParams().leftIconRes);
//        setImageResource(R.id.iv_right, getParams().rightIconRes);
//        setImageResource(R.id.iv_right_icon, getParams().textRightIconRes);
//        setText(R.id.title_tv, getParams().title);
//        setText(R.id.left_tv, getParams().leftTv);
//        setText(R.id.right_tv, getParams().rightTv);
//        setBackgroundColor(R.id.title_bar, getParams().bgColor);
//        setOnClickListener(R.id.left_ll, getParams().leftOnClickListener);
//        setOnClickListener(R.id.right_ll, getParams().rightOnClickListener);

    }

    // 构建导航条类
    public static class Builder extends AbsNavigationBar.Builder{
        DefaultNavigationParams P;

        public Builder(Context mContext, ViewGroup parent) {
            super(mContext,parent);
            P=new DefaultNavigationParams(mContext,parent);
        }

        public Builder(Context mContext) {
            super(mContext,null);
            P=new DefaultNavigationParams(mContext,null);
        }

        @Override
        public AbsNavigationBar create() {
            DefaultNavigationBar navigationBar=new DefaultNavigationBar(P);
            return navigationBar;
        }


        //1.设置所有效果
        public Builder setTitle(String title) {
            P.title = title;
            return this;
        }

        public Builder setRight(String right) {
            P.rightTv = right;
            return this;
        }

        public Builder setLeft(String left) {
            P.leftTv = left;
            return this;
        }

        public Builder setLeftIcon(int iconRes) {
            P.leftIconRes = iconRes;
            return this;
        }

        public Builder setRightIcon(int iconRes) {
            P.rightIconRes = iconRes;
            return this;
        }

        public Builder setTitleBackgroundColor(int bgColor) {
            P.bgColor = bgColor;
            return this;
        }

        public Builder setLeftOnClickListener(View.OnClickListener onClickListener) {
            P.leftOnClickListener = onClickListener;
            return this;
        }

        public Builder setRightOnClickListener(View.OnClickListener onClickListener) {
            P.rightOnClickListener = onClickListener;
            return this;
        }

        public static class DefaultNavigationParams extends AbsNavigationBar.Builder.AbsNavigationParams {

            //标题
            public String title;
            //左边图片资源
            public int leftIconRes;
            //右边图片资源
            public int rightIconRes;
            //左边的点击事件
            public View.OnClickListener leftOnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity)mContext).finish();
                }
            };
            //右边的点击事件
            public View.OnClickListener rightOnClickListener;
            public String leftTv;
            public String rightTv;
            public int bgColor;



            DefaultNavigationParams(Context context, ViewGroup parent) {
                super(context, parent);
            }

            //所有效果


        }

    }



}
