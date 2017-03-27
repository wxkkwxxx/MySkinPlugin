package com.wxk.leads.skindemo;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//http://blog.csdn.net/goodlixueyong/article/details/51089674
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button load_skin;
    private ImageView image_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load_skin = (Button) findViewById(R.id.load_skin);
        image_view = (ImageView) findViewById(R.id.image_view);

        load_skin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {

            //读取一个本地的.skin里面的资源
            Configuration configuration = new Configuration();
            DisplayMetrics metrics = new DisplayMetrics();
            //创建AssetManager
            AssetManager assetManager = AssetManager.class.newInstance();
            //添加本地下载好的资源

            Method method = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            method.setAccessible(true);
            //反射执行方法
            method.invoke(assetManager, Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "bilibili.skin");

            Resources resources = new Resources(assetManager, metrics, configuration);

            //文件名, 资源类型, 包名
            int drawableId = resources.getIdentifier("bilibili", "drawable", "com.wxk.leads.skinplugin");
            Drawable drawable = resources.getDrawable(drawableId);
            image_view.setImageDrawable(drawable);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
