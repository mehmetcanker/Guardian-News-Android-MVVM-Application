package com.canker.core.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public class KeyboardUtil {
    public static void hideSoftKeyboard(Activity activity) {
        View focusedView = activity.getCurrentFocus();
        if (focusedView != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.CUPCAKE) {
                InputMethodManager inputMethodManager = (InputMethodManager)
                        activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
            }
        }
    }

    public static void showSoftKeyboard(View view) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.CUPCAKE) {
            InputMethodManager inputMethodManager = (InputMethodManager) view.getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            view.requestFocus();
            inputMethodManager.showSoftInput(view, 0);
        }
    }


    private KeyboardUtil() {
    }
}
