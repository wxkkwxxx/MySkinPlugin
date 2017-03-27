package com.wxk.leads.skindemo.skin;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;

import com.wxk.leads.skindemo.skin.attr.SkinAttr;
import com.wxk.leads.skindemo.skin.attr.SkinType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/27
 */

public class SkinAttrHelper {

    //解析background src textColor
    public static List<SkinAttr> getSkinAttrs(Context context, AttributeSet attrs) {

        List<SkinAttr> skinAttrs = new ArrayList<>();

        int attributeCount = attrs.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {

            String attributeName = attrs.getAttributeName(i);
            String attributeValue = attrs.getAttributeValue(i);
            Log.e("=-=-=-=-=-=", attributeName +";;"+attributeValue);

            SkinType skinType = getSkinType(attributeName);
            if(skinType != null){
                String resName = getResName(context, attributeValue);

                Log.e("=========", resName);

                if(TextUtils.isEmpty(resName)){
                    continue;
                }
                SkinAttr skinAttr = new SkinAttr(resName, skinType);
                skinAttrs.add(skinAttr);
            }
        }
        return skinAttrs;
    }

    //获取资源名称
    private static String getResName(Context context, String attributeValue) {

        if(attributeValue.startsWith("@")){
            attributeValue = attributeValue.substring(1);

            int resId = Integer.parseInt(attributeValue);

            return context.getResources().getResourceEntryName(resId);
        }
        return null;
    }

    //通过名称获取SkinType
    private static SkinType getSkinType(String attributeName) {
        SkinType[] skinTypes = SkinType.values();
        for (SkinType skinType : skinTypes) {
            if(skinType.getResName().equals(attributeName)){
                return skinType;
            }
        }
        return null;
    }
}
