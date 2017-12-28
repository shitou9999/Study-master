package com.example.app.loading.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Dialog的View的辅助类
 * Created by PVer on 2017/6/20.
 */
class DialogViewHelper {


    private View mContentView = null;
    private SparseArray<WeakReference<View>> mViews;



    public DialogViewHelper(Context context, int layoutResId) {
        this();
        mContentView= LayoutInflater.from(context).inflate(layoutResId,null);
    }

    public DialogViewHelper() {
        mViews=new SparseArray<>();
    }

    public void setContentView(View contentView) {
        this.mContentView = contentView;
    }


    public void setText(int viewId, CharSequence charSequence) {
        TextView tv = getView(viewId);
        if (tv!=null){
            tv.setText(charSequence);
        }
    }

    public  <T extends View> T getView(int viewId) {
        WeakReference<View> viewWeakReference=mViews.get(viewId);
        View view=null;
        if (viewWeakReference!=null){
            view=viewWeakReference.get();
        }
        if (view==null){
            view=mContentView.findViewById(viewId);
            if (view!=null) {
                mViews.put(viewId,new WeakReference<>(view));
            }
        }

        return (T) view;
    }


    public void setOnClickListener(int viewId, View.OnClickListener onClickListener) {
        View view = getView(viewId);
        if (view!=null){
            view.setOnClickListener(onClickListener);
        }
    }

    //获取内容view
    public View getContentView() {
        return mContentView;
    }
}
