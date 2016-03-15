package com.asiainfo.android.wo.bp.demo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.ailk.openplatform.entitys.OpenConsumer;
import com.asiainfo.android.wo.bp.BalancePaymentSDK;
import com.asiainfo.android.wo.bp.callback.IPaymentCallback;
import com.asiainfo.android.wo.bp.callback.PayActivityResultHandler;
import com.asiainfo.android.wo.bp.model.PaymentResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Home extends Activity {

    Button btnText;
    Button btnInto;
    Button btnPayWithCode;

    EditText edProductId;
    EditText edProductName;
    EditText edContentId;
    EditText edAmount;
    EditText edTimeout;

    private String TAG = "Home";

    private ProgressDialog pbDialog;

    private PayActivityResultHandler<String> payActivityResultHandler;

    private IPaymentCallback<PaymentResponse<String>> callback = new IPaymentCallback<PaymentResponse<String>>() {

        @Override
        public void onSuccess(PaymentResponse<String> response) {
            Toast.makeText(Home.this, "contentId：" + response.getBody(),
                    Toast.LENGTH_LONG).show();
            Log.i(TAG, "内容ID：" + response.getBody());
            pbDialog.hide();
        }

        @Override
        public void onFailed(String code, String message) {
            Toast.makeText(Home.this, "支付失败，" + code + ":" + message,
                    Toast.LENGTH_LONG).show();
            Log.i(TAG, "支付失败，" + code + ":" + message);
            pbDialog.hide();
        }

        @Override
        public void onException(Throwable throwable) {
            Toast.makeText(Home.this, "支付异常", Toast.LENGTH_LONG).show();
            Log.e(TAG, "支付异常", throwable);
            pbDialog.hide();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        String appId = "";
        String appSecret = "";
        OpenConsumer openConsumer = new OpenConsumer(appId, appSecret, null, null);

        BalancePaymentSDK.init(this, openConsumer, "http://api.10155.com",
                "测试支付应用", "亚信科技有限公司", "010-86000000");

        btnInto = (Button) findViewById(R.id.into_paywithoutcode);
        btnPayWithCode = (Button) findViewById(R.id.into_paywithcode);

        edProductName = (EditText) findViewById(R.id.ed_product_name);
        edProductId = (EditText) findViewById(R.id.ed_product_id);
        edContentId = (EditText) findViewById(R.id.ed_content_id);
        edAmount = (EditText) findViewById(R.id.ed_amount);
        edTimeout = (EditText) findViewById(R.id.ed_timeout);

        edProductName.setText("乐享0.01元按次-1");
        edProductId.setText("0000001201");
        edAmount.setText("0.01");
        edTimeout.setText("60");

        TextView version = (TextView) findViewById(R.id.version);
        version.setText("当前SDK版本: " + BalancePaymentSDK.version());

        pbDialog = new ProgressDialog(this);
    }

    private void beforeClick() {
		/*
		 * 
		 * 前五位为合作方的CPID其余为自行生成
		 * 可用此流水号查询回调通知结果,所以请保证唯一,最长21字节
		 * 内容号中可存放二级应用id,二级应用id可用于代计费渠道和应用之间结算
		 * 示例(请勿用于实际代码):"901552222111111111111",其中2222为二级应用id,之后为流水号
		 */
        edContentId.setText("12345"
                + new SimpleDateFormat("yyMMddHHmmss", Locale.CHINA)
                .format(new Date()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (payActivityResultHandler != null) {
            payActivityResultHandler.handleResult(resultCode, data);
        }
    }

    public void payWithoutCode(View view) {
        beforeClick();

        String productId = edProductId.getText().toString();
        String productName = edProductName.getText().toString();
        String contentId = edContentId.getText().toString();
        String amount = edAmount.getText().toString();
        String timeout = edTimeout.getText().toString();

        payActivityResultHandler = BalancePaymentSDK.payWithoutCode(this,
                productId, productName, contentId, Double.valueOf(amount),
                Integer.valueOf(timeout), callback);
    }

    /**
     * 验证码支付
     */
    public void payWithCode(View view) {
        beforeClick();

        String productId = edProductId.getText().toString();
        String productName = edProductName.getText().toString();
        String contentId = edContentId.getText().toString();
        String amount = edAmount.getText().toString();
        String timeout = edTimeout.getText().toString();

        payActivityResultHandler = BalancePaymentSDK.payWithCode(this,
                productId, productName, contentId, Double.valueOf(amount),
                Integer.valueOf(timeout), callback);
    }
}