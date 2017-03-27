package com.wxk.leads.skindemo.skin;

import android.app.Activity;
import android.content.Context;

import com.wxk.leads.skindemo.skin.attr.SkinView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/27
 * 皮肤管理类
 */

public class SkinManager {

    private static SkinManager mInstance;
    private Context mContext;
    private Map<Activity, List<SkinView>> mSkinViews = new HashMap<>();
    private SkinResource mSkinResource;

    static {

        mInstance = new SkinManager();
    }

    public static SkinManager getInstance() {

        return mInstance;
    }

    public void init(Context context){
        this.mContext = context;
    }

    //恢复默认
    public int restoreDefault() {
        return 0;
    }

    //加载皮肤
    public int loadSkin(String skinPath) {

        mSkinResource = new SkinResource(mContext, skinPath);

        Set<Activity> keys = mSkinViews.keySet();
        for (Activity key : keys) {

            List<SkinView> skinViews = mSkinViews.get(key);
            for (SkinView skinView : skinViews) {
                skinView.skin();
            }
        }
        return 0;
    }

    //获取view
    public List<SkinView> getSkinViews(Activity activity) {
        return mSkinViews.get(activity);
    }

    //注册
    public void register(Activity activity, List<SkinView> skinViews) {

        mSkinViews.put(activity, skinViews);
    }

    //获取当前皮肤资源管理
    public SkinResource getSkinResource() {
        return mSkinResource;
    }
}
