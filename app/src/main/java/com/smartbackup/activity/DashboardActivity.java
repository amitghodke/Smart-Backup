package com.smartbackup.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;

import com.smartbackup.R;

import java.lang.reflect.Method;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard,menu);
        return true;
    }
    @Override
    public boolean onMenuOpened(int featureId, Menu menu)
    {
     //   if(featureId == Window.FEATURE_ACTION_BAR && menu != null)
        {
            if(menu.getClass().getSimpleName().equals("MenuBuilder")){
                try{
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                }
                catch(NoSuchMethodException e){
                    Log.d("onMenuOpened", e+"");
                }
                catch(Exception e){
                    throw new RuntimeException(e);
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }
}
