package com.example.aelaf.newsarticlesearch.front;



import com.example.aelaf.newsarticlesearch.front.jsonModel.DocsItem;

import java.util.List;

/**
 * Created by aelaf on 9/9/17.
 */

public interface Network {
     interface GetNetworkArticleList{
        void onArticleListFound(List<DocsItem> docsItemList);
         void onArticleListNotFound();
    }

    interface  GetNetworkArticle{
        void onArticleFound();
        void onArticleNotFound();
    }
    void articleListFetch(String query,GetNetworkArticleList callback);
    void articleFetch(String query,GetNetworkArticle callback);

}
