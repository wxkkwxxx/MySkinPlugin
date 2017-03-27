package com.wxk.leads.skindemo.skin;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/3/27
 * 皮肤资源
 */

public class SkinResource {

    private Resources mResources;
    private String mPackageName;

    public SkinResource(Context context, String skinPath) {

        try {

            Resources superRes = context.getResources();
            //创建AssetManager
            AssetManager assetManager = AssetManager.class.newInstance();
            //添加本地下载好的资源
            Method method = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            method.setAccessible(true);
            //反射执行方法
            method.invoke(assetManager, Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "bilibili.skin");

            mResources = new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());

            //获取包名
            mPackageName = context.getPackageManager().getPackageArchiveInfo(
                    skinPath, PackageManager.GET_ACTIVITIES).packageName;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //通过名字获取drawable
    public Drawable getDrawableByName(String resName){

        try {

            Log.e("----", resName+":::"+mPackageName);

            int resId = mResources.getIdentifier(resName, "drawable", mPackageName);
            Log.e("----", resId+"-----");
            Drawable drawable = mResources.getDrawable(resId);
            return  drawable;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //通过名字获取颜色
    public ColorStateList getColorByName(String resName){

        try {

            int resId = mResources.getIdentifier(resName, "color", mPackageName);
            ColorStateList colorStateList = mResources.getColorStateList(resId);
            return  colorStateList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
