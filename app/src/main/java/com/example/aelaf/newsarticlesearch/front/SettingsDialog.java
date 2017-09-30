package com.example.aelaf.newsarticlesearch.front;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.SharedPreferences;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import com.example.aelaf.newsarticlesearch.R;

import java.util.Calendar;

/**
 * Created by aelaf on 9/16/17.
 */

public class SettingsDialog extends DialogFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private Button btnSettings;
    private Button btnDateBeginPicker;
    private Button btnDateEndPicker;
    private Spinner spnrSettings;
    private CheckBox chkArts;
    private CheckBox chkFashion;
    private CheckBox chkSports;
    //variables to send back to mother ship
    private boolean mArts,mFashion,mSports;
    private int mBeginDay, mBeginMonth, mBeginYear,mEndDay,mEndMonth,mEndYear;
    private boolean mOrder;

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        /*if (Constants.ARTPREF.equals(key)) {

            chkArts.setChecked(sharedPreferences.getBoolean(Constants.ARTPREF,false));
        }*/
    }


    //working with preference
public interface SettingsInterface{
        void done(boolean art,boolean fashion,boolean sport,int dayBegin,int monthBegin,int yearBegin,boolean order);

    }

    public SettingsInterface mListener;

    public void registerListener(SettingsInterface listener ){
        mListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//registering the shared pref and setting the values based on shared preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        preferences.registerOnSharedPreferenceChangeListener(this);
        final AlertDialog.Builder masterBuilder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.settings_dialog,null);
        masterBuilder.setView(view);
        btnSettings = (Button)view.findViewById(R.id.btnSettings);
        btnDateBeginPicker = (Button) view.findViewById(R.id.btnDateBeginPicker);
       // btnDateEndPicker = (Button) view.findViewById(R.id.btnDateEndPicker);
        spnrSettings = (Spinner)view.findViewById(R.id.spnrSettings);
        chkArts= (CheckBox) view.findViewById(R.id.chkArts);
        chkFashion = (CheckBox) view.findViewById(R.id.chkFashion);
        chkSports = (CheckBox) view.findViewById(R.id.chkSports);


        //setting check boxes
        //
        boolean chkArtVal = preferences.getBoolean(Constants.ARTPREF,false);
        chkArts.setChecked(chkArtVal);
        mArts = chkArtVal?true:false;
        boolean chkFashionVal = preferences.getBoolean(Constants.FASHIONPREF,false);
        chkFashion.setChecked(chkFashionVal);
        mFashion = chkFashionVal?true:false;
        boolean chkSportsVal = preferences.getBoolean(Constants.SPORTPREF,false);
        chkSports.setChecked(chkSportsVal);
        mSports = chkSportsVal?true:false;

        int dayBegin =  preferences.getInt(Constants.DAYBEGINPREF,0);
        int monthBegin = preferences.getInt(Constants.MONTHBEGINPREF,0);
        int yearBegin = preferences.getInt(Constants.YEARBEGINPREF,0);

       mBeginDay = dayBegin;
        mBeginMonth = monthBegin;
        mBeginYear = yearBegin;
        if(dayBegin!=0 && monthBegin!=0 && yearBegin!=0)
        btnDateBeginPicker.setText(""+dayBegin+"/"+monthBegin+"/"+yearBegin);
        Calendar cal =  Calendar.getInstance();


        //setting spinner
        boolean orderRes = preferences.getBoolean(Constants.ORDERPREF,false);
       settingSpinner(spnrSettings, orderRes);
       mOrder = orderRes;
        //check box listener
        CompoundButton.OnCheckedChangeListener checkBoxListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean checked) {
                switch (view.getId()){
                    case R.id.chkArts:
                        if (mListener!=null) {

                            if (checked) {
                                mArts = checked;
                            } else {
                                mArts = checked;
                            }
                        }
                        break;
                    case R.id.chkFashion:
                        if (mListener!=null) {

                            if (checked) {
                                mFashion = checked;
                            } else {
                                mFashion = checked;
                            }
                        }
                        break;
                    case R.id.chkSports:
                        if (mListener!=null) {

                            if (checked) {
                                mSports = checked;
                            } else {
                                mSports = checked;
                            }
                        }

                }
            }
        };


     spnrSettings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

         @Override
         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
             String spinnerSelectedValue = spnrSettings.getSelectedItem().toString();
             if (mListener!=null) {

                 if (spinnerSelectedValue.equalsIgnoreCase("Older")) {
                    // itemClickListener.spinnerPicked(true);
                     mOrder=true;
                 } else if (spinnerSelectedValue.equalsIgnoreCase("Newer")) {
                    // itemClickListener.spinnerPicked(false);
                     mOrder= false;

                 }
             }


         }

         @Override
         public void onNothingSelected(AdapterView<?> adapterView) {

         }
     });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener!=null) {
                    //allow the entire views data to be sent at once to the parent activity
                    mListener.done(mArts,mFashion,mSports, mBeginDay, mBeginMonth, mBeginYear,mOrder);
                }

            }
        });

        btnDateBeginPicker.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                DateOnlyDialog dateOnlyDialog = new DateOnlyDialog();
                DateOnlyDialog.DateInterface dateInterface = new DateOnlyDialog.DateInterface() {
                    @Override
                    public void setDate(int day_month, int month, int year) {

                        btnDateBeginPicker.setText(""+day_month+"/"+month+"/"+year);
                        mBeginDay = day_month;
                        mBeginMonth =month;
                        mBeginYear = year;
                        /*if (itemClickListener!=null) {
                            itemClickListener.dateSelected(day_month,month,year);
                        }*/
                    }
                };
                dateOnlyDialog.show(getFragmentManager(),null);
                dateOnlyDialog.register(dateInterface);



            }
        });

        chkSports.setOnCheckedChangeListener(checkBoxListener);
        chkArts.setOnCheckedChangeListener(checkBoxListener);
        chkFashion.setOnCheckedChangeListener(checkBoxListener);

        return masterBuilder.create();

    }

    private void settingSpinner(Spinner spnrSettings, boolean aBoolean) {
        if (aBoolean){
            spnrSettings.setSelection(0);
        }
        else spnrSettings.setSelection(1);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        //unregister
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        preferences.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroyView();
    }
}
