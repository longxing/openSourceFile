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

import com.asiainfo.android.wmm.demo.adapter.ProductListAdapter;
import com.asiainfo.android.wmm.demo.util.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductListActivity extends Activity {

    ListView productlistView;

    private WMMActivityResultHandler<WMMOrder> mallActivityResultHandler;

    List<Map<String, Object>> list;
    static WMMProduct aResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        list = new ArrayList<Map<String, Object>>();
        productlistView = (ListView) findViewById(R.id.productlistView);

        WMMPaymentSDK.listProducts(ProductListActivity.this, new IWMMCallback<List<WMMProduct>>() {

            @Override
            public void onSuccess(List<WMMProduct> result) {
                Map<String, Object> map;
                for (WMMProduct aResult : result) {
                    ProductListActivity.aResult = aResult;
                    map = new HashMap<String, Object>();
                    map.put("title", aResult.getProductName());
                    map.put("info", String.valueOf(aResult.getPrice()));
                    map.put("amount", " ");
                    map.put("img", R.drawable.badge);
                    map.put("btnName", "订购");
                    map.put("btnMethod", new View.OnClickListener() {
                        String productId = ProductListActivity.aResult.getProductId();

                        @Override
                        public void onClick(View v) {
                            mallActivityResultHandler = WMMPaymentSDK.order(ProductListActivity.this, productId, 1, null,
                                    new IWMMCallback<WMMOrder>() {
                                        @Override
                                        public void onSuccess(WMMOrder result) {
                                            UIUtils.showToast(ProductListActivity.this, result.getOrderNo(), 5);
                                            Bundle mBundle = new Bundle();
                                            mBundle.putSerializable("result", result);
                                            Intent intent = new Intent();
                                            intent.setClass(ProductListActivity.this, ShowRightActivity.class);
                                            intent.putExtras(mBundle);
                                            startActivity(intent);
                                        }

                                        @Override
                                        public void onFailed(String code, String message) {
                                            Intent intent = new Intent();
                                            intent.setClass(ProductListActivity.this, HistoryOrderActivity.class);
                                            startActivity(intent);
                                        }

                                        @Override
                                        public void onException(Throwable throwable) {
                                            UIUtils.showToast(ProductListActivity.this, "orderWithProductId error", 5);
                                        }
                                    }
                            );
                        }
                    });
                    list.add(map);
                    productlistView.setAdapter(new ProductListAdapter(ProductListActivity.this, list));
                }
            }

            @Override
            public void onFailed(String code, String message) {
                UIUtils.showToast(ProductListActivity.this, code + ":" + message, 5);
            }

            @Override
            public void onException(Throwable throwable) {
                UIUtils.showToast(ProductListActivity.this, "listProducts error", 5);
            }
        });

        productlistView = (ListView) findViewById(R.id.productlistView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mallActivityResultHandler.handleResult(resultCode, data);
    }
}
