package chatqq.home.com.app2.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.List;

/**
 * 软件盘处理
 */
public class SoftKeyboardUtil {

    /**
     * 隐藏软键盘(只适用于Activity，不适用于Fragment)
     */
    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 隐藏软键盘(可用于Activity，Fragment)
     */
    public static void hideSoftKeyboard(Context context, List<View> viewList) {
        if (viewList == null) return;

        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);

        for (View v : viewList) {
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 隐藏键盘
     * @param context
     * @param editText
     */
    public static void gonekey(Activity context, EditText editText){
        ((InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 弹出软件盘
     * @param editText
     */
    public static void showSoftKeyboard(EditText editText){
        editText.requestFocus();
        InputMethodManager imm =(InputMethodManager)editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }


//    （一）显示软键盘
//    editText.requestFocus();
//    //弹出软键盘
//    InputMethodManager imm =(InputMethodManager)editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//    imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

//      （二）隐藏软键盘
    //隐藏软件盘
//    View mv = getActivity().getWindow().peekDecorView();
//    if (mv != null){
//        InputMethodManager inputmanger = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputmanger.hideSoftInputFromWindow(mv.getWindowToken(), 0);
//    }

}