package com.mowen.androidedituedemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED);

        EditText etContent = (EditText) findViewById(R.id.etContent);
//        etContent.setImeOptions(EditorInfo.);
//        etContent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(!hasFocus) {
//                    hideKeyboard(v);
//                }
//            }
//        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //解决软键盘弹出和隐藏问题
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                hideKeyboard(v);
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }


    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


    /**
     getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);



     OnClickListener onClickListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
    ((View) etComment.getParent()).setOnClickListener(null);
    mHandler.postDelayed(new Runnable() {
    @Override
    public void run() {
    if(divdeBindTripTypeArea.getVisibility() == View.VISIBLE) {
    scrollView.scrollTo(0, divdeBindTripTypeArea.getHeight());
    } else {
    scrollView.scrollTo(0, divdeTag.getHeight());
    }
    }
    }, 300);
    }
    };
     etComment.setOnClickListener(onClickListener);

     //解决软键盘弹出和隐藏问题
     @Override
     public boolean dispatchTouchEvent(MotionEvent ev){
     if (ev.getAction() == MotionEvent.ACTION_DOWN) {
     View v = getCurrentFocus();
     if (isShouldHideInput(v, ev)) {
     hideSoftInput();
     }
     return super.dispatchTouchEvent(ev);
     }
     // 必不可少，否则所有的组件都不会有TouchEvent了
     if (getWindow().superDispatchTouchEvent(ev)) {
     return true;
     }
     return onTouchEvent(ev);
     }

     public  boolean isShouldHideInput(View v, MotionEvent event) {
     if (v != null && (v instanceof EditText)) {
     int[] leftTop = { 0, 0 };
     //获取输入框当前的location位置
     v.getLocationInWindow(leftTop);
     int left = leftTop[0];
     int top = leftTop[1];
     int bottom = top + v.getHeight();
     int right = left + v.getWidth();
     if (event.getX() > left && event.getX() < right
     && event.getY() > top && event.getY() < bottom) {
     // 点击的是输入框区域，保留点击EditText的事件
     return false;
     } else {
     return true;
     }
     }
     return false;
     }
     * **/
}
