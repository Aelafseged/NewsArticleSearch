package com.example.aelaf.newsarticlesearch.front;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aelaf.newsarticlesearch.R;
import com.example.aelaf.newsarticlesearch.front.jsonModel.DocsItem;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Viewer,ItemListDialogFragment.Listener{
    @BindView(R.id.toolbar_main) Toolbar toolbar;
    @BindView(R.id.recycler) RecyclerView recyclerView;
    @BindView(R.id.progressBar2) ProgressBar progressBar;
    //ProgressBar mprogressBar;

    NewsGridAdapter adapter;
    private SharedPreferences preferences;
   private Network networkCall;
    private  ArticlePresenter presenter;
    private boolean artb,fashionb,sportb,orderb;
    private int dayb, monthb, yearb,dayE,monthE,yearE;

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //working with toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

         networkCall = new NetWorkFetch(this,APiUtil.ABSOLUTE_PATH);
         presenter = new ArticlePresenter(this,networkCall);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
       final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

                @Override
                public boolean onQueryTextSubmit(String query) {
                    //calling finished query
                    //call presenter with the data
                    /*
                    //trial job
                    DocsItem docsItemX = new DocsItem();
                    List<DocsItem> docsItemListX = new ArrayList<>();
                    docsItemX.setWeb_url("my web url");
                    docsItemX.setAbstractField("my Abstract field");
                    docsItemX.setLeadParagraph("justLead paragraph");
                    docsItemX.setSnippet("le snippet");
                    docsItemListX.add(docsItemX);
                    for (int i = 0; i <10;i++) {
                        docsItemListX.add(docsItemX);

                    }
                    showArticlesLists(docsItemListX);*/
                    if(checkConnected()){
                        progressBar.setVisibility(View.VISIBLE);
                        presenter.startLoadingArticle(query);
                    }
                    else{
                        Snackbar.make(toolbar,"Not Connected",Snackbar.LENGTH_SHORT).show();
                    }

                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    //calling a continued queri
                    return false;
                }
            });
        return super.onCreateOptionsMenu(menu);
    }

    private boolean checkConnected() {
            boolean result = true;
            Runtime runtime = Runtime.getRuntime();
            try {
                Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
                int     exitValue = ipProcess.waitFor();
                result =  (exitValue == 0);
            } catch (IOException e)          { e.printStackTrace(); }
            catch (InterruptedException e) { e.printStackTrace(); }
            return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.app_bar_search:
                Toast.makeText(this,"search",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.btmSheet:
              settingsJob();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void settingsJob() {
        //setting dialogo
        final SettingsDialog settingsDialog =   new SettingsDialog();
        //working with setting dialogo interface
         SettingsDialog.SettingsInterface settingsInterface = new SettingsDialog.SettingsInterface(){

            @Override
            public void done(boolean art, boolean fashion, boolean sport, int dayBegin, int monthBegin, int yearBegin,
                             boolean order) {
                //saving to shared preferences based on 1...
              //
                artb = art?true:false;
                fashionb = fashion?true:false;
                sportb = sport?true:false;
                dayb = dayBegin;
                monthb = monthBegin;
                yearb = yearBegin;
                orderb = order;

                /*dayE =dayEnd;
                monthE = monthEnd;
                yearE = yearEnd;*/



                preferences.edit().putBoolean(Constants.ARTPREF,artb).apply();
                preferences.edit().putBoolean(Constants.FASHIONPREF,fashionb).apply();
                preferences.edit().putBoolean(Constants.SPORTPREF,sportb).apply();

                preferences.edit().putInt(Constants.DAYBEGINPREF, dayb).apply();
                preferences.edit().putInt(Constants.MONTHBEGINPREF,monthb).apply();
                preferences.edit().putInt(Constants.YEARBEGINPREF,yearb).apply();
/*
                preferences.edit().putInt(Constants.DAYENDPREF, dayE).apply();
                preferences.edit().putInt(Constants.MONTHENDPREF,monthE).apply();
                preferences.edit().putInt(Constants.YEARENDPREF,yearE).apply();*/

                preferences.edit().putBoolean(Constants.ORDERPREF,order).apply();



                settingsDialog.dismiss();
            }


         };
        settingsDialog.show(getSupportFragmentManager(),null);
        settingsDialog.registerListener(settingsInterface);
    }

    @Override
    public void showArticlesLists(List<DocsItem> docsItemList) {
        progressBar.setVisibility(View.GONE);
        adapter = new NewsGridAdapter(this,docsItemList);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter.registerItemClickListener(new NewsGridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocsItem docsItem) {

               /* Intent intent = new Intent(MainActivity.this,NewsDetailActivity.class);
                intent.putExtra("docItem",docsItem);
                Log.d("METAG", "onItemClick: "+docsItem);
              startActivity(intent);*/
               //ditching web view for chrome tab
                //intent
                String mUrl = docsItem.getWeb_url();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, mUrl);
                //pending intent
                int requestCode = 100;

                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,
                        requestCode,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_action_name);


                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();

                builder.setActionButton(bitmap,"Share Link",pendingIntent,true);
                builder.setToolbarColor(ContextCompat.getColor(MainActivity.this,R.color.colorSnow));
               CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(MainActivity.this,Uri.parse(mUrl));
            }
        });
        adapter.registerItemLongCickListener(new NewsGridAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(String webUrl) {
                String uriText ="mailto:"+"?subject=" + Uri.encode("Article From NYTimes") +
                                "&body=" + Uri.encode(webUrl);

                Uri uri = Uri.parse(uriText);

                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(uri);
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(sendIntent, "Send email"));
                }
            }
        });

    }

    @Override
    public void showNoArticleList() {

        progressBar.setVisibility(View.GONE);
        Toast.makeText(this,"Content not found",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showArticleDetail(DocsItem docsItem) {

    }

    @Override
    public void showNoArticle() {

    }

    @Override
    public void onItemClicked(int position) {

    }

}
