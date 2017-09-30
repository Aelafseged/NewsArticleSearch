package com.example.aelaf.newsarticlesearch.front;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aelaf.newsarticlesearch.R;
import com.example.aelaf.newsarticlesearch.front.jsonModel.DocsItem;
import com.example.aelaf.newsarticlesearch.front.jsonModel.MultimediaItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by aelaf on 9/9/17.
 */

public class NewsGridAdapter extends RecyclerView.Adapter<NewsGridAdapter.ViewHolder> {

    private Context mContext;
    private List<DocsItem> docsItemList;

    //working with click listener
    public OnItemClickListener itemClickListener;
    //WORKING WITH lONG CLICK LISTENER;
    public OnItemLongClickListener itemLongClickListener;
    public interface OnItemLongClickListener{
        void onItemLongClick(String webUrl);
    }

    public void registerItemLongCickListener(OnItemLongClickListener listener){
        itemLongClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(DocsItem docsItem);
    }
    public void registerItemClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }

    public NewsGridAdapter(Context context, List<DocsItem> docsItemList) {
        this.mContext = context;
        this.docsItemList = docsItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.a_grid, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DocsItem docsItem = docsItemList.get(position);
        //working with title & or headlines
     String title_headLines =  (docsItem.getHeadline().getKicker()=="")?docsItem.getHeadline().getPrintHeadline():
              docsItem.getHeadline().getKicker();
        holder.mTextViewTitle.setText(title_headLines);
        String imageLink = "";
        //working with Type of Material
       String typeOfMaterial =  (docsItem.getTypeOfMaterial()!=null)?docsItem.getTypeOfMaterial():"";
        if(!typeOfMaterial.isEmpty()){
           typeOfMaterial =  typeOfMaterial.toUpperCase();
            holder.mTextTypeOfMaterial.setVisibility(View.VISIBLE);
            holder.mTextTypeOfMaterial.setText(typeOfMaterial);
        }
        //working with multimedia
        List<MultimediaItem> multimediaItemList = docsItemList.get(position).getMultimedia();

        for (int i = 0; i < multimediaItemList.size(); i++) {
            MultimediaItem multimediaItem = multimediaItemList.get(i);

            if(multimediaItem.getType().equalsIgnoreCase("image")&&
                    multimediaItem.getSubtype().equalsIgnoreCase("thumbnail")){
                imageLink = APiUtil.NYT_WEB+"/"+multimediaItem.getUrl();
            }
        }

            Log.d(TAG, "onBindViewHolder: " + imageLink);

            //work of picasso
            Glide.with(mContext).load(imageLink).centerCrop()
                    .placeholder(R.drawable.ic_search_black_24dp)
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .into(holder.mImageViewThumbnail);
            holder.mTextSnippet.setText(docsItem.getSnippet());


    }

    @Override
    public int getItemCount() {
        return docsItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        @BindView(R.id.title)
        TextView mTextViewTitle;
        @BindView(R.id.img)
        ImageView mImageViewThumbnail;
        @BindView(R.id.txtTypeOfMaterial) TextView mTextTypeOfMaterial;
        @BindView(R.id.snippet)
        TextView mTextSnippet;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
               //intent ...
                if (itemClickListener !=null) {
                    itemClickListener.onItemClick(docsItemList.get(position));
                    /*Intent intent = new Intent(mContext,NewsDetailActivity.class);
                    mContext.startActivity(intent);*/
                }
            }
        }


        @Override
        public boolean onLongClick(View view) {
            int position  = getAdapterPosition();
            if(position!=RecyclerView.NO_POSITION){
                if (itemLongClickListener!=null){
                    itemLongClickListener.onItemLongClick(docsItemList.get(position).getWeb_url());
                }
            }
            return true;
        }
    }
}
