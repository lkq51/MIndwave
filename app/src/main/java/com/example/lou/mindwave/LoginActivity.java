package com.example.lou.mindwave;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    String accountString;
    EditText username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        username=(EditText)findViewById(R.id.username);
             /*、
        *   设置EditText的焦点监听事件，使得EdiText聚焦时hint消失
        * */
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText editText = (EditText) v;
                if (!hasFocus) {
                    editText.setHint(editText.getTag().toString());
                } else {
                    String hint = editText.getHint().toString();
                    editText.setTag(hint);
                    editText.setHint("");
                }
            }
        });
    }
    @SuppressLint("NewApi")
    public void next(View view){
        accountString = username.getText().toString();
        if (!ifAccountOk(accountString)) {
            /**
             * 设置晃动动画
             */
            username.setText("");
            username.clearFocus();
            username.startAnimation(shakeAnimation(5));
        }else {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("account",accountString);
            startActivity(intent);
        }
    }
    /*判断账号是否符合要求--函数*/
    public Boolean ifAccountOk(String accountString){
        if(accountString.length()>=3){
            if (accountString.matches("^[\\da-zA-Z]*$")){
                return true;
            }
        }
//        如果不符合要求则将EditText里的账号要求变为红色提示用户

        username.setHintTextColor(getResources().getColor(R.color.red));
        return false;
    }

    /**
     * 晃动动画
     * @param counts 1秒钟晃动多少下
     * @return
     */
    public static Animation shakeAnimation(int counts){
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }
}
