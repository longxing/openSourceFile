package com.asiainfo.android.wmm.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.ailk.openplatform.entitys.OpenConsumer;
import com.asiainfo.android.common.util.LogUtils;
import com.asiainfo.android.wmm.demo.activity.ProductListActivity;
import com.asiainfo.android.wmm.demo.activity.ShowRightActivity;
import com.asiainfo.android.wmm.demo.util.UIUtils;
import com.asiainfo.android.wo.music.mall.WMMPaymentSDK;
import com.asiainfo.android.wo.music.mall.util.IWMMCallback;
import com.asiainfo.android.wo.music.mall.util.WMMActivityResultHandler;

public class MainActivity extends Activity {

    Button intoProductListButton;
    Button intoShowRightButton;
    Button resetButton;
    WMMActivityResultHandler<String> mallActivityResultHandler;

    void initHost() {
        String appId = "AK4547";
        String appSecret = "63BEEFC738A6B2F4";
        OpenConsumer openConsumer = new OpenConsumer(appId, appSecret, null, null);
        WMMPaymentSDK.init(this, openConsumer);

        LogUtils.setDefaultLogLevel(LogUtils.LOG_LEVEL_ALL);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initHost();

        intoProductListButton = (Button) findViewById(R.id.intoProductListButton);
        intoShowRightButton = (Button) findViewById(R.id.intoShowRightButton);
        resetButton = (Button) findViewById(R.id.resetButton);

        intoProductListButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ProductListActivity.class);
                startActivity(intent);
            }
        });

        intoShowRightButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ShowRightActivity.class);
                startActivity(intent);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WMMPaymentSDK.reset(MainActivity.this);
            }
        });
    }

    public void bindMember(View view) {
        mallActivityResultHandler = WMMPaymentSDK.bindMember(this, new IWMMCallback<String>() {
            @Override
            public void onSuccess(String result) {
                UIUtils.showToast(MainActivity.this, "用户标识：" + result, 5);
            }

            @Override
            public void onFailed(String code, String message) {
                UIUtils.showToast(MainActivity.this, code + ":" + message, 10);
            }

            @Override
            public void onException(Throwable throwable) {
                UIUtils.showToast(MainActivity.this, "绑定用户异常", 5);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mallActivityResultHandler.handleResult(resultCode, data);
    }
}