package com.example.aelaf.newsarticlesearch.front;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.aelaf.newsarticlesearch.R;
import com.example.aelaf.newsarticlesearch.front.jsonModel.DocsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsDetailActivity extends AppCompatActivity implements Viewer {
public static final String TAG = "NEWSdETAILaCTIVITY";
    private Presenter presenter;
    private NewsGridAdapter viewer;
    @BindView(R.id.imgDetail)
    ImageView imgViewDetail;
    @BindView(R.id.webViewContent)
    WebView webViewContent;
   /* @BindView(R.id.txtWebUrl)
    TextView txtWebUrl;*/
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       //create both Presenter and viewer implementation
     DocsItem mDocItem =   getIntent().getParcelableExtra("docItem");
        Log.d(TAG, "onCreate: "+mDocItem.getWeb_url()+"");
        presenter = new NewsDetailPresenter(this,mDocItem);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void showArticlesLists(List<DocsItem> docsItemList) {

    }

    @Override
    public void showNoArticleList() {

    }

    @Override
    public void showArticleDetail(DocsItem docsItem) {
       /* Glide.with(this).load(docsItem.getMultimedia().get(1).getUrl()).centerCrop()
                .placeholder(R.drawable.ic_search_black_24dp)
                .error(R.drawable.ic_search_black_24dp)
                .into(imgViewDetail);*/
        /*txtWebUrl.setText(docsItem.getSnippet());*/
        //working with webView
        Log.d(TAG, "showArticleDetail: "+docsItem);
        String title_headLines;
        if(docsItem.getHeadline().getKicker()==null && docsItem.getHeadline().getPrintHeadline()==null){
             title_headLines = ""+docsItem.getHeadline().getMain();

        }
        else{
            title_headLines =  (docsItem.getHeadline().getKicker()==null)?docsItem.getHeadline().getPrintHeadline():
                    docsItem.getHeadline().getKicker();
        }



        if(title_headLines.length()>15){
         title_headLines = title_headLines.substring(0,15)+"...";
        }
        toolbar.setTitle(title_headLines);

        webViewContent.getSettings().setLoadsImagesAutomatically(true);
        webViewContent.getSettings().setJavaScriptEnabled(true);
        webViewContent.getSettings().setSupportZoom(true);
        webViewContent.getSettings().setBuiltInZoomControls(true);
        webViewContent.getSettings().setDisplayZoomControls(false);
        webViewContent.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webViewContent.setWebViewClient(new CustomBrowser());
        webViewContent.loadUrl(docsItem.getWeb_url());
    }

    @Override
    public void showNoArticle() {

    }

    private class CustomBrowser extends WebViewClient {

        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
           view.loadUrl(url);
            return true;
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }
}


