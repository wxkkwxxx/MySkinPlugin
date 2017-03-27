package com.wxk.leads.skindemo;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.VectorEnabledTintResources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;

import com.wxk.leads.skindemo.skin.SkinAttrHelper;
import com.wxk.leads.skindemo.skin.SkinManager;
import com.wxk.leads.skindemo.skin.attr.SkinAppCompatViewInflater;
import com.wxk.leads.skindemo.skin.attr.SkinAttr;
import com.wxk.leads.skindemo.skin.attr.SkinView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//http://blog.csdn.net/goodlixueyong/article/details/51089674
public class MainActivity extends AppCompatActivity implements View.OnClickListener, LayoutInflaterFactory {

    private Button load_skin;
    private ImageView image_view;

    private SkinAppCompatViewInflater mAppCompatViewInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory(layoutInflater, this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load_skin = (Button) findViewById(R.id.load_skin);
        image_view = (ImageView) findViewById(R.id.image_view);

        load_skin.setOnClickListener(this);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

        //拦截view创建
        View view = createView(parent, name, context, attrs);

        //解析属性
        if(view != null){

            List<SkinAttr> skinAttrs = SkinAttrHelper.getSkinAttrs(context, attrs);
            SkinView skinView = new SkinView(view, skinAttrs);

            //交给SkinManager管理
            managerSkinView(skinView);
        }
        return view;
    }

    private void managerSkinView(SkinView skinView) {

        List<SkinView> skinViews = SkinManager.getInstance().getSkinViews(this);
        if(skinViews == null){
            skinViews = new ArrayList<>();
            SkinManager.getInstance().register(this, skinViews);
        }
        skinViews.add(skinView);
    }

    public View createView(View parent, final String name, @NonNull Context context,
                           @NonNull AttributeSet attrs) {
        final boolean isPre21 = Build.VERSION.SDK_INT < 21;

        if (mAppCompatViewInflater == null) {
            mAppCompatViewInflater = new SkinAppCompatViewInflater();
        }

        // We only want the View to inherit its context if we're running pre-v21
        final boolean inheritContext = isPre21 && shouldInheritContext((ViewParent) parent);

        return mAppCompatViewInflater.createView(parent, name, context, attrs, inheritContext,
                isPre21, /* Only read android:theme pre-L (L+ handles this anyway) */
                true, /* Read read app:theme as a fallback at all times for legacy reasons */
                VectorEnabledTintResources.shouldBeUsed() /* Only tint wrap the context if enabled */
        );
    }

    private boolean shouldInheritContext(ViewParent parent) {
        if (parent == null) {
            // The initial parent is null so just return false
            return false;
        }
        final View windowDecor = getWindow().getDecorView();
        while (true) {
            if (parent == null) {
                // Bingo. We've hit a view which has a null parent before being terminated from
                // the loop. This is (most probably) because it's the root view in an inflation
                // call, therefore we should inherit. This works as the inflated layout is only
                // added to the hierarchy at the end of the inflate() call.
                return true;
            } else if (parent == windowDecor || !(parent instanceof View)
                    || ViewCompat.isAttachedToWindow((View) parent)) {
                // We have either hit the window's decor view, a parent which isn't a View
                // (i.e. ViewRootImpl), or an attached view, so we know that the original parent
                // is currently added to the view hierarchy. This means that it has not be
                // inflated in the current inflate() call and we should not inherit the context.
                return false;
            }
            parent = parent.getParent();
        }
    }

    public void jump(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void skin1(View view){

        int result = SkinManager.getInstance().restoreDefault();
    }

    @Override
    public void onClick(View v) {

        String skinPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "bilibili.skin";

        int result = SkinManager.getInstance().loadSkin(skinPath);

    }
}
