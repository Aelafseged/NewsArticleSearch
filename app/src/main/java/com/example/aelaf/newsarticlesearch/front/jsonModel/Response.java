package com.example.aelaf.newsarticlesearch.front.jsonModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: aelaf
 * Created by: ModelGenerator on 9/28/17
 */
public class Response {
    private List<DocsItem> docs;
    private Meta meta;

    //to avoid empty object creation


    public Response() {
        docs = new ArrayList<>();

    }

    public List<DocsItem> getDocs() {
        return docs;
    }

    public void setDocs(List<DocsItem> docs) {
        this.docs = docs;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}