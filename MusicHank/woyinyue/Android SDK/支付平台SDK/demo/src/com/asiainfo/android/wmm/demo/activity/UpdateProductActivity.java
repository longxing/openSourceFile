package com.asiainfo.android.wmm.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.asiainfo.android.wmm.demo.R;
import com.asiainfo.android.wo.music.mall.model.WMMOrder;
import com.asiainfo.android.wo.music.mall.model.WMMProduct;
import com.asiainfo.android.wo.music.mall.util.WMMActivityResultHandler;
import com.asiainfo.android.wo.music.mall.util.IWMMCallback;
import com.asiainfo.android.wo.music.mall.WMMPaymentSDK;

import com.asiainfo.android.wmm.demo.adapter.UpdateProductAdapter;
import com.asiainfo.android.wmm.demo.util.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateProductActivity extends Activity {
    ListView updateProductlistView;

    private WMMActivityResultHandler<WMMOrder> mallActivityResultHandler;

    List<Map<String, Object>> list;
    static WMMProduct aResult;

    WMMProduct result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        result = (WMMProduct) getIntent().getSerializableExtra("result");
        list = new ArrayList<Map<String, Object>>();
        updateProductlistView = (ListView) findViewById(R.id.updateProductListView);

        Map<String, Object> map;
        for (WMMProduct aResult : result.getUpgradeProducts()) {
            UpdateProductActivity.aResult = aResult;
            map = new HashMap<String, Object>();
            map.put("title", aResult.getProductName());
            map.put("info", String.valueOf(aResult.getPrice()));
            map.put("amount", " ");
            map.put("img", R.drawable.badge);
            map.put("btnName", "升级");
            map.put("btnMethod", new View.OnClickListener() {
                String productId = UpdateProductActivity.aResult.getProductId();

                @Override
                public void onClick(View v) {
                    mallActivityResultHandler = WMMPaymentSDK.upgrade(UpdateProductActivity.this, productId,
                            new IWMMCallback<WMMOrder>() {
                                @Override
                                public void onSuccess(WMMOrder result) {
                                    UIUtils.showToast(UpdateProductActivity.this, result.getOrderNo(), 5);
                                    Bundle mBundle = new Bundle();
                                    mBundle.putSerializable("result", result);
                                    Intent intent = new Intent();
                                    intent.setClass(UpdateProductActivity.this, ShowRightActivity.class);
                                    intent.putExtras(mBundle);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailed(String code, String message) {
                                    UIUtils.showToast(UpdateProductActivity.this, code + ":" + message, 5);

                                    Intent intent = new Intent();
                                    intent.setClass(UpdateProductActivity.this, HistoryOrderActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onException(Throwable throwable) {
                                    UIUtils.showToast(UpdateProductActivity.this, "orderWithProductId error", 5);
                                }
                            }

                    );
                }
            });
            list.add(map);
            updateProductlistView.setAdapter(new UpdateProductAdapter(this, list));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mallActivityResultHandler.handleResult(resultCode, data);
    }
}
