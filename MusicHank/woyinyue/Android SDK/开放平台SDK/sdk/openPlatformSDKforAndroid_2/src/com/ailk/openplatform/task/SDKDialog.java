package com.ailk.openplatform.task;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ailk.openplatform.exception.AuthException;
import com.ailk.openplatform.exception.DialogException;
import com.ailk.openplatform.utils.LogUtil;
import com.ailk.openplatform.utils.NetworkHelper;
import com.ailk.openplatform.utils.Utility;

public class SDKDialog extends Dialog {
	private static final String TAG = "SDKDialog";
	private Context mContext;
	private RelativeLayout mRootContainer;
	private RelativeLayout mWebViewContainer;
	private ProgressDialog mLoadingDlg;
	private WebView mWebView;
	private boolean mIsDetached = false;
	private String mAuthUrl;
	private SDKAuthListener mListener;
	private SDKAuth mSDKAuth;
	private static int theme = 16973840;

	public SDKDialog(Context context, String authUrl, SDKAuthListener listener,
			SDKAuth sDKAuth) {
		super(context, theme);
		this.mAuthUrl = authUrl;
		this.mListener = listener;
		this.mContext = context;
		this.mSDKAuth = sDKAuth;
	}

	public void onBackPressed() {
		super.onBackPressed();

		if (this.mListener != null)
			this.mListener.onCancel();
	}

	public void dismiss() {
		if (!this.mIsDetached) {
			if ((this.mLoadingDlg != null) && (this.mLoadingDlg.isShowing())) {
				this.mLoadingDlg.dismiss();
			}

			super.dismiss();
		}
	}

	public void onAttachedToWindow() {
		this.mIsDetached = false;
		super.onAttachedToWindow();
	}

	public void onDetachedFromWindow() {
		if (this.mWebView != null) {
			this.mWebViewContainer.removeView(this.mWebView);
			this.mWebView.stopLoading();
			this.mWebView.removeAllViews();
			this.mWebView.destroy();
			this.mWebView = null;
		}

		this.mIsDetached = true;
		super.onDetachedFromWindow();
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initWindow();

		initLoadingDlg();

		initWebView();

		initCloseButton();
	}

	private void initWindow() {
		requestWindowFeature(1);
		getWindow().setFeatureDrawableAlpha(0, 0);
		getWindow().setSoftInputMode(16);

		this.mRootContainer = new RelativeLayout(getContext());
		this.mRootContainer.setBackgroundColor(0);
		addContentView(this.mRootContainer, new ViewGroup.LayoutParams(-1, -1));
	}

	private void initLoadingDlg() {
		this.mLoadingDlg = new ProgressDialog(getContext());

		this.mLoadingDlg.requestWindowFeature(1);

	}

	@SuppressLint({ "SetJavaScriptEnabled" })
	private void initWebView() {
		this.mWebViewContainer = new RelativeLayout(getContext());
		this.mWebView = new WebView(getContext());
		this.mWebView.getSettings().setJavaScriptEnabled(true);

		this.mWebView.getSettings().setSavePassword(false);
		this.mWebView.setWebViewClient(new SDKWebViewClient());
		this.mWebView.requestFocus();
		this.mWebView.setScrollBarStyle(0);
		this.mWebView.setVisibility(4);
		// 设置可以支持缩放
		this.mWebView.getSettings().setSupportZoom(true);
		// 设置默认缩放方式尺寸是far
		this.mWebView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
		// 设置出现缩放工具
		this.mWebView.getSettings().setBuiltInZoomControls(true); 

		NetworkHelper.clearCookies(this.mContext, this.mAuthUrl);
		this.mWebView.loadUrl(this.mAuthUrl);
		RelativeLayout.LayoutParams webViewContainerLayout = new RelativeLayout.LayoutParams(
				-1, -1);
		RelativeLayout.LayoutParams webviewLayout = new RelativeLayout.LayoutParams(
				-1, -1);
		DisplayMetrics dm = getContext().getResources().getDisplayMetrics();

		float density = dm.density;
		int margin = (int) (10.0F * density); 
		webviewLayout.setMargins(margin, margin, margin, margin); 

		this.mWebViewContainer.addView(this.mWebView, webviewLayout);
		this.mWebViewContainer.setGravity(17);
 
		webViewContainerLayout.setMargins(30, 30, 30, 30);
		this.mRootContainer.addView(this.mWebViewContainer,
				webViewContainerLayout);
	}

	private void initCloseButton() {
		ImageView closeImage = new ImageView(this.mContext); 
		closeImage.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				SDKDialog.this.dismiss();

				if (SDKDialog.this.mListener != null)
					SDKDialog.this.mListener.onCancel();
			}
		});
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				-2, -2);
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) this.mWebViewContainer
				.getLayoutParams(); 
		this.mRootContainer.addView(closeImage, layoutParams);
	}

	private void handleRedirectUrl(String url) {
		Bundle values = Utility.parseUrl(url);

		String errorType = values.getString("error");
		String errorCode = values.getString("error_code");
		String errorDescription = values.getString("error_description");

		if ((errorType == null) && (errorCode == null))
			this.mListener.onComplete(values);
		else
			this.mListener.onSDKException(new AuthException(errorCode,
					errorType, errorDescription));
	}

	private class SDKWebViewClient extends WebViewClient {
		private boolean isCallBacked = false;

		private SDKWebViewClient() {
		}

		/**
		 * https授权
		 */
		public void onReceivedSslError(WebView view, SslErrorHandler handler,
				SslError error) {
			handler.proceed(); // 接受所有网站的证书
		}

		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			LogUtil.i(TAG, "load URL: " + url);

			if (url.startsWith("sms:")) {
				Intent sendIntent = new Intent("android.intent.action.VIEW");
				sendIntent.putExtra("address", url.replace("sms:", ""));
				sendIntent.setType("vnd.android-dir/mms-sms");
				SDKDialog.this.getContext().startActivity(sendIntent);
				return true;
			}
			return super.shouldOverrideUrlLoading(view, url);
		}

		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			LogUtil.d(TAG, "onReceivedError: errorCode = " + errorCode
					+ ", description = " + description + ", failingUrl = "
					+ failingUrl);
			super.onReceivedError(view, errorCode, description, failingUrl);

			if (SDKDialog.this.mListener != null) {
				SDKDialog.this.mListener.onSDKException(new DialogException(
						description, errorCode, failingUrl));
			}
			SDKDialog.this.dismiss();
		}

		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			LogUtil.d(TAG, "onPageStarted URL: " + url);
			System.out
					.println("lww...mSDKAuth.getConsumer().getCallBackScheme() = "
							+ mSDKAuth.getConsumer().getCallBackScheme());
			if (url.startsWith(mSDKAuth.getConsumer().getCallBackScheme())
					&& (!this.isCallBacked)) {
				this.isCallBacked = true;
				SDKDialog.this.handleRedirectUrl(url);
				view.stopLoading();
				SDKDialog.this.dismiss();
				return;
			}

			super.onPageStarted(view, url, favicon);

			if ((!SDKDialog.this.mIsDetached)
					&& (SDKDialog.this.mLoadingDlg != null)
					&& (!SDKDialog.this.mLoadingDlg.isShowing()))
				SDKDialog.this.mLoadingDlg.show();
		}

		public void onPageFinished(WebView view, String url) {
			LogUtil.d(TAG, "onPageFinished URL: " + url);
			super.onPageFinished(view, url);
			if ((!SDKDialog.this.mIsDetached)
					&& (SDKDialog.this.mLoadingDlg != null)) {
				SDKDialog.this.mLoadingDlg.dismiss();
			}

			if (SDKDialog.this.mWebView != null)
				SDKDialog.this.mWebView.setVisibility(0);
		}
	}
}
