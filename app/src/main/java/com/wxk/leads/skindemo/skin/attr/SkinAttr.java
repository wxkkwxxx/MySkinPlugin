package com.wxk.leads.skindemo.skin.attr;

import android.view.View;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class SkinAttr {

    private String mResName;
    private SkinType mSkinType;

    public SkinAttr(String resName, SkinType skinType) {

        this.mResName = resName;
        this.mSkinType = skinType;
    }

    public void skin(View view) {

        mSkinType.skin(view, mResName);
    }
}
