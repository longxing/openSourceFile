package com.asiainfo.android.wmm.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.asiainfo.android.wmm.demo.R;
import com.asiainfo.android.wo.music.mall.model.WMMOrder;
import com.asiainfo.android.wo.music.mall.model.WMMProduct;
import com.asiainfo.android.wo.music.mall.model.WMMUserRight;
import com.asiainfo.android.wo.music.mall.util.WMMActivityResultHandler;
import com.asiainfo.android.wo.music.mall.util.IWMMCallback;
import com.asiainfo.android.wo.music.mall.WMMPaymentSDK;

import com.asiainfo.android.wmm.demo.adapter.ShowRightAdapter;
import com.asiainfo.android.wmm.demo.util.UIUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowRightActivity extends Activity {
    private WMMActivityResultHandler<WMMOrder> mallActivityResultHandler;

    ListView showRightlistView;

    List<Map<String, Object>> list;

    Button showRightRenewButton;
    Button showRightUpdateButton;
    Button showRightRefreshButton;
    Button showRightHistoryButton;

    WMMOrder result;
    WMMUserRight right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_right);

        list = new ArrayList<Map<String, Object>>();
        showRightlistView = (ListView) findViewById(R.id.showRightListView);

        result = (WMMOrder) getIntent().getSerializableExtra("result");
        WMMPaymentSDK.getUserRight(ShowRightActivity.this, WMMProduct.TYPE_TRAFFIC, new IWMMCallback<WMMUserRight>() {
            @Override
            public void onSuccess(WMMUserRight result) {
                if (result != null) {
                    right = result;
                    Map<String, Object> map;
                    map = new HashMap<String, Object>();
                    map.put("title", "日期");
                    map.put("info", new SimpleDateFormat("yyyy-MM-dd").format(result.getThruDate()));
                    list.add(map);

                    map = new HashMap<String, Object>();
                    map.put("title", "名称");
                    map.put("info", result.getProductName());
                    list.add(map);
                    showRightlistView.setAdapter(new ShowRightAdapter(ShowRightActivity.this, list));
                } else {
                    UIUtils.showToast(ShowRightActivity.this, "没有任何权益", 5);
                }
            }

            @Override
            public void onFailed(String code, String message) {
                UIUtils.showToast(ShowRightActivity.this, message, 5);
            }

            @Override
            public void onException(Throwable throwable) {
                UIUtils.showToast(ShowRightActivity.this, "getUserRights error", 5);
            }
        });

        showRightRenewButton = (Button) findViewById(R.id.showRightRenewButton);
        showRightUpdateButton = (Button) findViewById(R.id.showRightUpdateButton);
        showRightRefreshButton = (Button) findViewById(R.id.showRightRefreshButton);
        showRightHistoryButton = (Button) findViewById(R.id.showRightHistoryButton);

        showRightRenewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    mallActivityResultHandler = WMMPaymentSDK.renew(ShowRightActivity.this, right.getProductId(), 1, new IWMMCallback<WMMOrder>() {

                        @Override
                        public void onSuccess(WMMOrder result) {
                            showRightRefreshButton.performClick();
                            UIUtils.showToast(ShowRightActivity.this, "续订成功!", 5);
                        }

                        @Override
                        public void onFailed(String code, String message) {
                            UIUtils.showToast(ShowRightActivity.this, code + ":" + message, 5);
                        }

                        @Override
                        public void onException(Throwable throwable) {
                            UIUtils.showToast(ShowRightActivity.this, "renewWithProductId error！", 5);
                        }
                    });
                } catch (Exception e) {
                    UIUtils.showToast(ShowRightActivity.this, "续订失败!", 5);
                }
            }
        });
        showRightUpdateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WMMPaymentSDK.getProduct(ShowRightActivity.this, right.getProductId(), new IWMMCallback<WMMProduct>() {

                    @Override
                    public void onSuccess(WMMProduct result) {
                        Bundle mBundle = new Bundle();
                        mBundle.putSerializable("result", result);
                        Intent intent = new Intent();
                        intent.setClass(ShowRightActivity.this, UpdateProductActivity.class);
                        intent.putExtras(mBundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailed(String code, String message) {
                        UIUtils.showToast(ShowRightActivity.this, code + ":" + message, 5);
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        UIUtils.showToast(ShowRightActivity.this, "getProduct error！", 5);
                    }
                });
            }
        });
        showRightRefreshButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WMMPaymentSDK.getUserRight(ShowRightActivity.this, WMMProduct.TYPE_TRAFFIC, new IWMMCallback<WMMUserRight>() {
                    @Override
                    public void onSuccess(WMMUserRight result) {
                        if (result != null) {
                            list.clear();

                            Map<String, Object> map;
                            map = new HashMap<String, Object>();
                            map.put("title", "日期");
                            map.put("info", new SimpleDateFormat("yyyy-MM-dd").format(result.getThruDate()));
                            list.add(map);

                            map = new HashMap<String, Object>();
                            map.put("title", "名称");
                            map.put("info", result.getProductName());
                            list.add(map);

                            showRightlistView.setAdapter(new ShowRightAdapter(ShowRightActivity.this, list));
                            UIUtils.showToast(ShowRightActivity.this, "刷新成功", 5);
                        } else {
                            UIUtils.showToast(ShowRightActivity.this, "没有任何权益", 5);
                        }
                    }

                    @Override
                    public void onFailed(String code, String message) {
                        UIUtils.showToast(ShowRightActivity.this, code + ":" + message, 5);
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        UIUtils.showToast(ShowRightActivity.this, "getUserRights error", 5);
                    }
                });
            }
        });
        showRightHistoryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ShowRightActivity.this, HistoryOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mallActivityResultHandler.handleResult(resultCode, data);
    }
}
