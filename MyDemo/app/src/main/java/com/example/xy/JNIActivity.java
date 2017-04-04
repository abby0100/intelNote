package com.example.xy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * android jni
 *      http://www.2cto.com/kf/201607/526887.html
 * android mk
 *      http://www.cnblogs.com/jymblog/p/5526865.html
 *
 * http://blog.csdn.net/yanbober/article/details/45309049
 */
public class JNIActivity extends Activity {

    private static final String LOG_TAG = "JNIActivity";
    {
        System.loadLibrary("XYJni");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
        Log.d(LOG_TAG, "onCreate");

        startJni();
    }

    private void startJni() {
        Log.d(LOG_TAG, "startJni");
        TextView jniTV = (TextView) findViewById(R.id.tv_jni);
        NdkJniUtil ndkJniUtil = new NdkJniUtil();

        Log.d(LOG_TAG, "ndkJniUtil getCLanguageString");
        jniTV.setText(ndkJniUtil.getCLanguageString());
    }
}
