package com.example.app.loading.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by PVer on 2017/6/20.
 */
class AlertController {
    private AlertDialog mDialog;
    private Window mWindow;
    private DialogViewHelper mViewHelper;

    public AlertController(AlertDialog alertDialog, Window window) {
        this.mDialog=alertDialog;
        this.mWindow=window;

    }

    public void setViewHelper(DialogViewHelper viewHelper) {
        this.mViewHelper = viewHelper;
    }

    public AlertDialog getDialog() {
        return mDialog;
    }

    /**
     * 获取dialog的window
     * @return
     */
    public Window getWindow() {
        return mWindow;
    }

    public void setText(int viewId, CharSequence charSequence) {
        mViewHelper.setText(viewId,charSequence);
    }

    public  <T extends View> T getView(int viewId) {
       return mViewHelper.getView(viewId);
    }


    public void setOnClickListener(int viewId, View.OnClickListener onClickListener) {
        mViewHelper.setOnClickListener(viewId,onClickListener);
    }


    public static class AlertParams {
        public Context mContext;
        public int mThemeResId;
        public boolean mCancelable = true;//点击空白是否可以取消
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public DialogInterface.OnKeyListener mOnKeyListener;
        public View mView;//布局
        public int mViewLayoutResId;//布局id
        SparseArray<CharSequence> mTextArray= new SparseArray<>();//存放字体修改等
        SparseArray<View.OnClickListener> mClickArray= new SparseArray<>();//存放点击事件
        public int mAnimations = 0;//默认无动画
        public int mGravity = Gravity.CENTER;
        public int mWidth=ViewGroup.LayoutParams.WRAP_CONTENT;//默认宽度
        public int mHeigth=ViewGroup.LayoutParams.WRAP_CONTENT;
        //        SparseArray<WeakReference<View.OnClickListener>> clickArray= new SparseArray<>();//存放点击事件


        public AlertParams(Context context, int themeResId) {

            this.mContext=context;
            this.mThemeResId=themeResId;
        }

        /**
         * 绑定和设置参数
         * @param mAlert
         */
        public void apply(AlertController mAlert) {
            //1.设置布局DialogViewHelper 2.文本3.点击4.自定义效果 全屏，底部弹出 默认动画
            DialogViewHelper viewHelper=null;
            if (mViewLayoutResId!=0){
                viewHelper=new DialogViewHelper(mContext,mViewLayoutResId);
            }

            if (mView!=null){
                viewHelper=new DialogViewHelper();
                viewHelper.setContentView(mView );
            }

            if (viewHelper==null){
                throw new IllegalArgumentException("请设置layout布局");
            }

            //设置布局
            mAlert.getDialog().setContentView(viewHelper.getContentView());
            //设置controller辅助类
            mAlert.setViewHelper(viewHelper);

            //文本
            int textArraySize = mTextArray.size();
            for (int i = 0; i < textArraySize; i++) {
                mAlert.setText(mTextArray.keyAt(i),mTextArray.valueAt(i));
//                viewHelper.setText(mTextArray.keyAt(i),mTextArray.valueAt(i));
            }
            //点击
            int clickArraySize = mClickArray.size();
            for (int i = 0; i < clickArraySize; i++) {
                mAlert.setOnClickListener(mClickArray.keyAt(i),mClickArray.valueAt(i));
//                viewHelper.setOnClickListener(mClickArray.keyAt(i),mClickArray.valueAt(i));
            }

            Window window=mAlert.getWindow();
            window.setGravity(mGravity);
            //设置动画
            if(mAnimations!=0){
                window.setWindowAnimations(mAnimations);
            }
            //设置宽高
            WindowManager.LayoutParams params= window.getAttributes();
            params.width=mWidth;
            params.height=mHeigth;
            window.setAttributes(params);



        }
    }


}


















