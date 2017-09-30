package com.example.aelaf.newsarticlesearch.front.jsonModel;

/**
 * Author: aelaf
 * Created by: ModelGenerator on 9/28/17
 */
public class JsonModel {
    private String status;    // OK
    private String copyright;    // Copyright(c)201
    private Response response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}