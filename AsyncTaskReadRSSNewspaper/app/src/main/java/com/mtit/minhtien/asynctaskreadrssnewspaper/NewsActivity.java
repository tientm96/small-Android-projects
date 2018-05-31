package com.mtit.minhtien.asynctaskreadrssnewspaper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        webView = (WebView) findViewById(R.id.webviewTinTuc);

        Intent intent = getIntent(); //lấy dl ra
        String link = intent.getStringExtra("linkTinTuc");

        //load link báo lên
        webView.loadUrl(link);

        //dòng này giúp khi click vào nhiều link nó ko nhảy ra ngoài app để mở bằng trình duyệt mặc định, mà
        //sẽ dùng luôn app của mình, giúp nhanh hơn.
        webView.setWebViewClient(new WebViewClient());

    }
}
