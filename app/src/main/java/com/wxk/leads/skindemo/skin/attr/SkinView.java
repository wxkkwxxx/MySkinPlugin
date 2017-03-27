package com.wxk.leads.skindemo.skin.attr;

import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2017/3/27
 */

public class SkinView {

    //目标view
    private View mView;

    //属性
    private List<SkinAttr> mSkinAttrs;

    public SkinView(View view, List<SkinAttr> skinAttrs) {
        this.mView = view;
        this.mSkinAttrs = skinAttrs;
    }

    public void skin(){

        for (SkinAttr skinAttr : mSkinAttrs) {

            skinAttr.skin(mView);
        }
    }
}
