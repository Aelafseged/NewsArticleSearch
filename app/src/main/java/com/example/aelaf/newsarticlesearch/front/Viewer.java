package com.example.aelaf.newsarticlesearch.front;


import com.example.aelaf.newsarticlesearch.front.jsonModel.DocsItem;

import java.util.List;

/**
 * Created by aelaf on 9/9/17.
 */

public interface Viewer {
    void showArticlesLists(List<DocsItem> docsItemList);
    void showNoArticleList();
    void showArticleDetail(DocsItem docsItem);
    void showNoArticle();

}
