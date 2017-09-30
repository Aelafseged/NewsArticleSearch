package com.example.aelaf.newsarticlesearch.front;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import static android.content.ContentValues.TAG;
import com.example.aelaf.newsarticlesearch.front.jsonModel.*;
import static android.media.CamcorderProfile.get;
import static android.os.Build.VERSION_CODES.N;




/**
 * Created by aelaf on 9/9/17.
 */

public class NetWorkFetch implements Network {
    private Context mContext;
    public String mApiUrl;

    HttpUrl.Builder urlBuilder ;
    HttpLoggingInterceptor logging;
    OkHttpClient client ;
    public NetWorkFetch(Context context,String url) {
        this.mContext = context;
        this.mApiUrl = url;

    }


    @Override
    public void articleListFetch(String query, final GetNetworkArticleList callback) {
        //working with query filters and shared prefe
        //request shared preferences
        urlBuilder = HttpUrl.parse(mApiUrl).newBuilder();
        logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        client =  new OkHttpClient.Builder().addInterceptor(logging).build();

        if(httpResonder()){


                    //all the code


        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(mContext);

        if (!query.isEmpty())
        urlBuilder.addQueryParameter("q",query);

        if (sharedPreferences.getBoolean(Constants.ARTPREF,false) ||
                sharedPreferences.getBoolean(Constants.FASHIONPREF,false ||
                       sharedPreferences.getBoolean(Constants.SPORTPREF,false) )) {

            String newsVar = "";
            StringBuffer newsBuffer = new StringBuffer();
            
            if (sharedPreferences.getBoolean(Constants.ARTPREF, false))
                newsBuffer.append("\"art\" ");
                newsVar.concat("\"art\" ");
            if (sharedPreferences.getBoolean(Constants.FASHIONPREF, false)) {
                newsBuffer.append("\"fashion\" ");

                newsVar.concat("\"fashion\" ");

            }
            if (sharedPreferences.getBoolean(Constants.SPORTPREF, false))
                newsBuffer.append("\"sports\" ");

            newsVar.concat("\"sports\" ");
            if (!newsBuffer.toString().isEmpty()) {
                urlBuilder.addQueryParameter("fq", "news_desk:("+newsBuffer.toString().trim()+")");
            }
            Log.i(TAG, "news_desk:("+newsBuffer.toString().trim()+")");
        }
        //begin_date
        //sort

        if (sharedPreferences.getInt(Constants.YEARBEGINPREF,0)!=0 &&
                sharedPreferences.getInt(Constants.MONTHBEGINPREF,0)!=0 &&
                sharedPreferences.getInt(Constants.DAYBEGINPREF,0)!=0) {
            int beginDay = sharedPreferences.getInt(Constants.DAYBEGINPREF,0);
            int beginMonth = sharedPreferences.getInt(Constants.MONTHBEGINPREF,0);
            String beginDayString = (beginDay<10)?"0"+beginDay:beginDay+"";
            String beginMonthString = (beginMonth<10)?"0"+beginMonth:beginMonth+"";
            String beginDate = sharedPreferences.getInt(Constants.YEARBEGINPREF,0)+""+
                    beginMonthString+beginDayString;
            Log.i(TAG, "articleListFetch: "+sharedPreferences.getInt(Constants.MONTHBEGINPREF,0));
            urlBuilder.addQueryParameter("begin_date", beginDate);
        }
        Calendar cal = Calendar.getInstance();

        if (sharedPreferences.getBoolean(Constants.ORDERPREF,false)) {
            urlBuilder.addQueryParameter("sort","oldest");
        }

     Log.i(TAG, "articleListFetch: "+ urlBuilder.toString());
            urlBuilder.addQueryParameter("api-key",APiUtil.API_key);
        String mUrl = urlBuilder.toString();
        Request request = new Request.Builder().url(mUrl).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.i(TAG, "onFailure: failed to bring"+e.getMessage());

                Handler mainHandler = new Handler(Looper.getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // code to interact with UI
                        callback.onArticleListNotFound();
                    }
                });

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                //json
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                try{
                    String resultResponse = response.body().string();
                    Log.d(TAG, "onResponse: "+resultResponse);
                    Gson gson = new GsonBuilder().create();
                    JsonModel jsonModel = gson.fromJson(resultResponse,JsonModel.class);
                    Log.d(TAG, "onResponse: "+jsonModel.getResponse().toString());
                    //gson example...
                final  List<DocsItem> docsList =    jsonModel.getResponse().getDocs();
                    Log.d(TAG, "onResponse: "+docsList);
                    Handler mainHandlerx = new Handler(Looper.getMainLooper());
                    mainHandlerx.post(new Runnable() {
                        @Override
                        public void run() {
                            // code to interact with UI
                            callback.onArticleListFound(docsList);
                        }
                    });
                    //gson example


            }
                catch(Exception ex){
                    ex.printStackTrace();
                    Handler mainHandler = new Handler(Looper.getMainLooper());
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            // code to interact with UI
                            callback.onArticleListNotFound();

                        }
                    });
                }


            }

        });


        }
        else{

        }

    }

    private boolean httpResonder() {
        String murl = urlBuilder.toString();
        Request request = new Request.Builder().url(murl).build();
        /*try {
            Response response =  client.newCall(request).execute();
            if(response.code()==200){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

return true;
    }

    @Override
    public void articleFetch(String query, GetNetworkArticle callback) {

    }
    //many other network requests


}
