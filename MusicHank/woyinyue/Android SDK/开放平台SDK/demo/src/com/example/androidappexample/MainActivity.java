package com.example.androidappexample;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ailk.openplatform.contants.Constants;
import com.ailk.openplatform.entitys.OpenConsumer;
import com.ailk.openplatform.entitys.OpenPlatformProvider;
import com.ailk.openplatform.task.LoginActivity;
import com.ailk.openplatform.utils.OauthUtil;

public class MainActivity extends Activity {

	// 临时授权码地址
	public static String AUTHORIZEHTTPS = "https://10.1.249.32:8443/OpenOauth2/oauth/authorize";

	// 临时授权码地址
	public static String AUTHORIZEHTTP = "http://10.1.249.32:48186/OpenOauth2/oauth/authorize";
	// 获取授权地址
	public static String ACCESS_TOKEN = "http://10.1.249.32:48186/OpenOauth2/oauth/token";

	Button btn=null;
	Button reToken=null;
	Button doGet=null;
	Button doPost=null;
	Button delToken=null;
	Button clean=null;
	TextView tx=null;
	TextView showResult=null;
	EditText et=null;
	EditText appid=null;
	EditText urlParam=null; 
	EditText mustUrlParam=null;
	EditText appsercert=null;
	CheckBox checkAccess=null;
	Boolean checkToken=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn=(Button)this.findViewById(R.id.getAccessToken);
		doPost=(Button)this.findViewById(R.id.doPost);
		doGet=(Button)this.findViewById(R.id.doGet);
		et=(EditText)this.findViewById(R.id.testUrl);
		reToken=(Button)this.findViewById(R.id.reToken);
		delToken=(Button)this.findViewById(R.id.delToken);
		clean=(Button)this.findViewById(R.id.doClean);
		showResult=(TextView)this.findViewById(R.id.showResult);
		checkAccess=(CheckBox)this.findViewById(R.id.checkAccess);
		appid=(EditText)this.findViewById(R.id.appid);
		appsercert=(EditText)this.findViewById(R.id.sercert);
		urlParam=(EditText)this.findViewById(R.id.urlParam);
		mustUrlParam=(EditText)this.findViewById(R.id.mustUrlParam);
		tx=(TextView)this.findViewById(R.id.showToken);
		checkAccess.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				Toast.makeText(MainActivity.this, 
                        arg1?"授权":"取消授权"    , Toast.LENGTH_SHORT).show();
				checkToken=arg1;
			}
			
		});
		SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(this);
		String accessToken=OauthUtil.getAccessToken(prefs);
		if(accessToken!=null&&accessToken.trim()!=""){
			tx.setText(accessToken);
		}else{
			tx.setText("暂时没有TOKEN");
		}
		clean.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				showResult.setText("");
			}
		});
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String appId=appid.getText().toString();
				String appSecret=appsercert.getText().toString();
				String callbackHost="callback";
				String callbackScheme="http://www.baidu.com";
				OpenConsumer consumer=new OpenConsumer(appId,appSecret,callbackHost,callbackScheme);
				Intent intent=new Intent(MainActivity.this,LoginActivity.class);
				intent.putExtra(OpenConsumer.CONSUMER, consumer);

				/*传入授权地址，可以根据http或者https自动判断进行授权*/				
				intent.putExtra("authorize", AUTHORIZEHTTP);
				
				intent.putExtra("access_token", ACCESS_TOKEN);
				startActivityForResult(intent, 1);
			}
		});
		doPost.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String appId=appid.getText().toString();
				String appSecret=appsercert.getText().toString();
				String callbackHost="callback";
				String callbackScheme="http://www.baidu.com";
				String url=et.getText().toString();
				OpenConsumer consumer=new OpenConsumer(appId,appSecret,callbackHost,callbackScheme);
				Map<String,String> params=createUrlParam(urlParam.getText().toString());
				OpenPlatformProvider provider=new OpenPlatformProvider(url,consumer,createUrlParam(mustUrlParam.getText().toString()),MainActivity.this);
			
				new HttpRequestTask(params, provider,showResult).execute(checkToken);
			}
		});
		doGet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String appId=appid.getText().toString();
				String appSecret=appsercert.getText().toString();
				String callbackHost="callback";
				String callbackScheme="http://www.baidu.com";
				String url=et.getText().toString();
				OpenConsumer consumer=new OpenConsumer(appId,appSecret,callbackHost,callbackScheme);
				Map<String,String> params=createUrlParam(urlParam.getText().toString());
				params.put("param1", "123");
				params.put("param2", "1231321");
				//OpenPlatformProvider provider=new OpenPlatformProvider(url,consumer,MainActivity.this);
				OpenPlatformProvider provider=new OpenPlatformProvider(url,consumer,createUrlParam(mustUrlParam.getText().toString()),MainActivity.this);
				new HttpRequestGetTask(provider, params,showResult).execute(checkToken);
			}
		});
		reToken.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(arg0.getContext());
				String accessToken=OauthUtil.getAccessToken(prefs);
				if(accessToken!=null&&accessToken.trim()!=""){
					tx.setText(accessToken);
				}else{
					tx.setText("暂时没有TOKEN");
				}
			}
		});
		delToken.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(arg0.getContext());
				Editor editor=prefs.edit();
				editor.remove(Constants.ResponseParamName.ACCESS_TOKEN);
				editor.commit();
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private Map<String,String> createUrlParam(String urlParam) {
		Map<String,String> result=new HashMap<String, String>();
		if(urlParam!=null&&!urlParam.trim().equals("")){
			String[] params=urlParam.split(";");
			for(String param:params){
				result.put(param.split("=")[0], param.split("=")[1]);
			}
		}
		return result;
	}
}
