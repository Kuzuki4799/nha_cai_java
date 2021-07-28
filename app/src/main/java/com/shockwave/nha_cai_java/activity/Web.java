package com.shockwave.nha_cai_java.activity;

import android.os.Bundle;
import android.os.Message;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.shockwave.nha_cai_java.R;
import com.shockwave.nha_cai_java.base.BaseActivity;
import com.shockwave.nha_cai_java.utils.ParamUtils;

public class Web extends BaseActivity {

    private WebView webViewContent;

    @Override
    protected int getLayoutId() {
        return R.layout.web;
    }

    @Override
    protected void initView() {
        webViewContent = findViewById(R.id.webViewContent);
    }

    @Override
    protected void initData() {
        Bundle intent = getIntent().getBundleExtra(ParamUtils.BUNDLE_KEY);

        webViewContent.getSettings().setJavaScriptEnabled(true);
        this.webViewContent.setWebViewClient(new WebViewClient());
        this.webViewContent.getSettings().setDomStorageEnabled(true);
        this.webViewContent.getSettings().setLoadWithOverviewMode(true);
        webViewContent.getSettings().setJavaScriptEnabled(true);
        this.webViewContent.getSettings().setSaveFormData(true);
        this.webViewContent.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                WebView webView = new WebView(view.getContext());
                webView.setWebViewClient(new WebViewClient());
                webView.getSettings().setDomStorageEnabled(true);
                webView.getSettings().setLoadWithOverviewMode(true);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setSaveFormData(true);
                webView.getSettings().setAllowFileAccess(true);
                webView.getSettings().setAllowFileAccess(true);
                webView.getSettings().setAllowContentAccess(true);
                webView.getSettings().setSupportMultipleWindows(true);
                webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                webView.getSettings().setAppCacheEnabled(true);
                webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
                webView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36");

                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        webViewContent.loadUrl(url);
                        return true;
                    }
                });
                ((WebView.WebViewTransport) resultMsg.obj).setWebView(webView);
                resultMsg.sendToTarget();
                return true;

            }
        });
        this.webViewContent.getSettings().setAllowFileAccess(true);
        this.webViewContent.getSettings().setAllowFileAccess(true);
        this.webViewContent.getSettings().setAllowContentAccess(true);
        this.webViewContent.getSettings().setSupportMultipleWindows(true);
        this.webViewContent.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.webViewContent.getSettings().setAppCacheEnabled(true);
        this.webViewContent.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        this.webViewContent.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36");
        this.webViewContent.loadUrl(intent.getString(ParamUtils.URL));
    }

    @Override
    protected void listener() {

    }

    @Override
    public void onBackPressed() {
        if (this.webViewContent.canGoBack()) {
            this.webViewContent.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
