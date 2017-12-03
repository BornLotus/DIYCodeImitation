package com.bornlotus.diycodeimitation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {

    Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }

    public void toastShort(String msg){
        toast(msg,Toast.LENGTH_SHORT);
    }

    public void toastLong(String msg){
        toast(msg,Toast.LENGTH_LONG);
    }

    public void toast(String msg,int type){
        if (mToast == null){
            mToast = Toast.makeText(this,msg,type);
        }else{
            mToast.setText(msg);
            mToast.setDuration(type);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mToast.show();
            }
        });
    }

}
