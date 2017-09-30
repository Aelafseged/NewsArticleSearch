package com.example.aelaf.newsarticlesearch.front;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.aelaf.newsarticlesearch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by aelaf on 9/16/17.
 */

public class DateOnlyDialog extends DialogFragment {
    private DatePicker dtPicker;
   private Button btnDateSelected;
    private int day_month,month,year;

    public interface DateInterface{
        void setDate(int day_month,int month,int year);
    }
    public DateInterface mListener;
    public void register(DateInterface listener){
        mListener = listener;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dateBuilder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.date_only,null);
        dateBuilder.setView(view);
        dtPicker = (DatePicker) view.findViewById(R.id.dtPicker);
        btnDateSelected = (Button)view.findViewById(R.id.btnDateSelected);

        btnDateSelected.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (mListener!=null) {
                    day_month = dtPicker.getDayOfMonth();
                    month = dtPicker.getMonth();
                    year = dtPicker.getYear();

                    mListener.setDate(day_month,month,year);
                    DateOnlyDialog.this.dismiss();
                }
            }
        });

        return dateBuilder.create();
    }
}
