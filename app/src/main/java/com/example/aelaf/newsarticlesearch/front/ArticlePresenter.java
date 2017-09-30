package com.example.aelaf.newsarticlesearch.front;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import com.example.aelaf.newsarticlesearch.front.jsonModel.DocsItem;
import com.example.aelaf.newsarticlesearch.front.jsonModel.Headline;
import com.example.aelaf.newsarticlesearch.front.jsonModel.MultimediaItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aelaf on 9/9/17.
 */

public class ArticlePresenter implements Presenter{

    private  Viewer mActivity;
    private Network mNetworkFetch;

    private NewsGridAdapter.OnItemClickListener mOnItemClickListener;

    public ArticlePresenter(Viewer activity,Network networkFetch) {
        this.mActivity = activity;
        this.mNetworkFetch = networkFetch;

    }

    public void startLoadingArticle(String name){
        loadArticleList(name);
    }

    @Override
    public void loadArticleList(String query) {
        List<DocsItem> docsItemList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DocsItem doc = new DocsItem();
            doc.setWeb_url("http://1.1.1.1");
            doc.setDocumentType("News");

            Headline headLine = new Headline();
            headLine.setKicker(null);
            headLine.setPrintHeadline("Print HeadLine");
            headLine.setMain(null);
            doc.setHeadline(headLine);
            List<MultimediaItem> multimediaItems = new ArrayList<>();
            MultimediaItem multimediaItem = new MultimediaItem();
            multimediaItem.setUrl("url");
            multimediaItem.setType("image");
            multimediaItem.setSubtype("thumbnail");
            multimediaItems.add(multimediaItem);
            doc.setMultimedia(multimediaItems);
            doc.setSnippet("Gson based -Snow white Snippet");
           docsItemList.add(doc) ;
           mActivity.showArticlesLists(docsItemList);
        }

     /*   mNetworkFetch.articleListFetch(query, new Network.GetNetworkArticleList() {
            @Override
            public void onArticleListFound(List<DocsItem> docsItemList) {
                mActivity.showArticlesLists(docsItemList);
            }

            @Override
            public void onArticleListNotFound() {
            mActivity.showNoArticleList();
            }
        });*/
    }

    @Override
    public void loadArticle(String id) {

    }


}
