package com.wxk.leads.skindemo.skin.attr;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxk.leads.skindemo.skin.SkinManager;
import com.wxk.leads.skindemo.skin.SkinResource;

/**
 * Created by Administrator on 2017/3/27
 */

public enum SkinType {

    TEXT_COLOR("textColor"){
        @Override
        public void skin(View view, String resName) {
            SkinResource skinResource = getSkinResource();
            ColorStateList colorStateList = skinResource.getColorByName(resName);
            if(colorStateList == null){
                return;
            }
            TextView textView = (TextView) view;
            textView.setTextColor(colorStateList);
        }
    }, BACKGROUND("background") {
        @Override
        public void skin(View view, String resName) {
            SkinResource skinResource = getSkinResource();
            Drawable drawable = skinResource.getDrawableByName(resName);
            if(drawable != null){

                ImageView imageView = (ImageView) view;
                imageView.setBackgroundDrawable(drawable);
                return;
            }

            ColorStateList colorStateList = skinResource.getColorByName(resName);
            if(colorStateList != null){
                view.setBackgroundColor(colorStateList.getDefaultColor());
            }
        }
    }, SRC("src") {
        @Override
        public void skin(View view, String resName) {

            SkinResource skinResource = getSkinResource();
            Drawable drawable = skinResource.getDrawableByName(resName);
            if(drawable != null){

                ImageView imageView = (ImageView) view;
                imageView.setImageDrawable(drawable);
                return;
            }
        }
    };

    private static SkinResource getSkinResource() {
        return SkinManager.getInstance().getSkinResource();
    }

    private String mResName;

    SkinType(String resName) {
        this.mResName = resName;
    }

    public abstract void skin(View view, String resName);

    public String getResName() {
        return mResName;
    }
}
