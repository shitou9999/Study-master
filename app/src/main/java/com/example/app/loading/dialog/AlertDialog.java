package com.example.app.loading.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.loading.R;


/**
 * Created by PVer on 2017/6/20.
 */
public class AlertDialog extends Dialog{


    private AlertController mAlert;

    public AlertDialog(Context context, int themeResId) {
        super(context, themeResId);
        mAlert=new AlertController(this,getWindow());
    }


    public void setText(int viewId, CharSequence charSequence) {
        mAlert.setText(viewId,charSequence);
    }

    public  <T extends View> T getView(int viewId) {
       return mAlert.getView(viewId);
    }


    public void setOnClickListener(int viewId, View.OnClickListener onClickListener) {
        mAlert.setOnClickListener(viewId,onClickListener);
    }




    public static class Builder{
        private final AlertController.AlertParams P;
        /**
         * Creates a builder for an alert dialog that uses the default alert
         * dialog theme.
         * <p>
         * The default alert dialog theme is defined by
         * {@link android.R.attr#alertDialogTheme} within the parent
         * {@code context}'s theme.
         *
         * @param context the parent context
         */
        public Builder(Context context) {
            this(context, R.style.Dialog);//dialog默认主题
        }

        public Builder(Context context, int themeResId) {
            P = new AlertController.AlertParams(context,themeResId);
        }


        public AlertDialog create() {
            // Context has already been wrapped with the appropriate theme.
            final AlertDialog dialog = new AlertDialog(P.mContext,P.mThemeResId);
            P.apply(dialog.mAlert);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }

        public AlertDialog show() {
            final AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }

        public Builder setContentView(View view) {
            P.mView = view;
            P.mViewLayoutResId = 0;
            return this;
        }

        public Builder setContentView(int layoutResId) {
            P.mView = null;
            P.mViewLayoutResId = layoutResId;
            return this;
        }

        public Builder setText(int viewId,CharSequence text){
            P.mTextArray.put(viewId,text);
            return this;
        }

        public Builder setOnClickListener(int view,View.OnClickListener listener){
            P.mClickArray.put(view,listener);
            return this;
        }

        public Builder fullWidth(){
            P.mWidth= ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        public Builder fromBottom(Boolean isAnimation){
            if (isAnimation){
                P.mAnimations=R.style.ActionSheetDialogAnimation;
            }
            P.mGravity= Gravity.BOTTOM;
            return this;
        }

        public Builder setWidthOrHeight(int width,int height){
            P.mWidth=width;
            P.mHeigth=height;
            return this;
        }

        /**
         * 设置默认动画
         * @return
         */
        public Builder setDefaultAnimation(){
//            P.mAnimations=R.style
            return this;
        }

        public Builder setAnimation(int styleAnimaiton){
            P.mAnimations=styleAnimaiton;
            return this;
        }

        public  Builder setCancelable(boolean cancelable) {
            P.mCancelable = cancelable;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            P.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            P.mOnKeyListener = onKeyListener;
            return this;
        }

    }

}
