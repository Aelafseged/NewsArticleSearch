package com.example.aelaf.newsarticlesearch.front;


import com.example.aelaf.newsarticlesearch.front.jsonModel.DocsItem;

/**
 * Created by aelaf on 9/24/17.
 */

public class NewsDetailPresenter implements Presenter {

    private Viewer mViewer;
    private DocsItem mDocsItem;
    public NewsDetailPresenter(Viewer viewer, DocsItem docsItem) {
        mViewer = viewer;
        mDocsItem = docsItem;
        loadArticle(docsItem.getWeb_url());

    }

    @Override
    public void loadArticleList(String title) {


    }

    @Override
    public void loadArticle(String docId) {
    mViewer.showArticleDetail(mDocsItem);
    }
}
