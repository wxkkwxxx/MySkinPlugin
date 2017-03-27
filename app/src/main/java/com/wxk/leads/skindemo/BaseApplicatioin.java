package com.wxk.leads.skindemo;

import android.app.Application;

import com.wxk.leads.skindemo.skin.SkinManager;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class BaseApplicatioin extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
    }
}
