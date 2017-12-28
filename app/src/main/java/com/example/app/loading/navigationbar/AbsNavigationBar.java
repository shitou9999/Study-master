package com.example.app.loading.navigationbar;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 头部基类
 * Created by PVer on 2017/6/25.
 */
public abstract class AbsNavigationBar<P extends AbsNavigationBar.Builder.AbsNavigationParams> implements INavigation {

    private P mParams;
    private View view;

    public AbsNavigationBar(P mParams) {
        this.mParams = mParams;
        creatAndBindView();
    }

    //绑定和创建view -------Activity和AppCompatActivity不一样的
    private void creatAndBindView() {
        if (mParams.mParent == null) {
            //获取actitvty的根布局，view源码
            ViewGroup actitvtyRoot = (ViewGroup) ((Activity)mParams.mContext).findViewById(android.R.id.content);
            mParams.mParent = (ViewGroup) actitvtyRoot.getChildAt(0);
            return;
        }

        if (mParams.mParent == null) {
            return;
        }

        view = LayoutInflater.from(mParams.mContext).inflate(bindLayoutId(), mParams.mParent, false);
        mParams.mParent.addView(view, 0);
        applyView();

    }

    //绑定和创建view
    /*
    private void creatAndBindView() {
        if (mParams == null) {
            return;
        }
        view = LayoutInflater.from(mParams.mContext).inflate(bindLayoutId(), mParams.mParent, false);
        mParams.mParent.addView(view, 0);
        applyView();
    }*/

    protected String getString(int id) {
        return this.mParams.mContext.getResources().getString(id);
    }

    protected int getColor(int id) {
        return ContextCompat.getColor(this.mParams.mContext, id);
    }


    public P getParams() {
        return mParams;
    }

    protected void setText(int viewId, CharSequence text) {
        TextView tv = findViewById(viewId);
        if (tv != null) {
            tv.setText(text);
        }
    }

    protected void setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = findViewById(viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    protected void setImageResource(int viewId, int resourceId) {
        ImageView imageView = findViewById(viewId);
        if (imageView != null) {
            imageView.setImageResource(resourceId);
        }
    }

    protected <T extends View> T findViewById(int id) {
        return (T) view.findViewById(id);
    }


    public static abstract class Builder{

        public Builder(Context mContext, ViewGroup parent) {

        }

        // 构建导航条方法
        public abstract AbsNavigationBar create();

        //负责参数处理  // 默认的配置参数
        public static class AbsNavigationParams{
            //这里面都是public
            public Context mContext;
            public ViewGroup mParent;

            AbsNavigationParams(Context context, ViewGroup parent){
                this.mContext=context;
                this.mParent=parent;
            }



        }

    }






}
