package com.asiainfo.android.wmm.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.asiainfo.android.wmm.demo.R;
import com.asiainfo.android.wo.music.mall.model.WMMOrder;
import com.asiainfo.android.wo.music.mall.util.IWMMCallback;
import com.asiainfo.android.wo.music.mall.WMMPaymentSDK;

import com.asiainfo.android.wmm.demo.adapter.HistoryOrderAdapter;
import com.asiainfo.android.wmm.demo.util.UIUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryOrderActivity extends Activity {
    ListView historyOrderListView;

    List<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);

        list = new ArrayList<Map<String, Object>>();

        WMMPaymentSDK.listOrders(HistoryOrderActivity.this, new IWMMCallback<List<WMMOrder>>() {
            @Override
            public void onSuccess(List<WMMOrder> result) {
                if (result != null && result.size() > 0) {
                    Map<String, Object> map;
                    for (WMMOrder item : result) {
                        map = new HashMap<String, Object>();
                        map.put("title", item.getItems().get(0).getProductName());
                        map.put("info", item.getStatusName());
                        map.put("date", new SimpleDateFormat("yyyy-MM-dd").format(item.getCreateTime()));
                        map.put("no", item.getOrderNo());
                        list.add(map);
                    }
                    historyOrderListView.setAdapter(new HistoryOrderAdapter(HistoryOrderActivity.this, list));
                } else {
                    UIUtils.showToast(HistoryOrderActivity.this, "listOrders is null", 5);
                }
            }

            @Override
            public void onFailed(String code, String message) {

            }

            @Override
            public void onException(Throwable throwable) {

            }
        });

        historyOrderListView = (ListView) findViewById(R.id.historyOrderListView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
