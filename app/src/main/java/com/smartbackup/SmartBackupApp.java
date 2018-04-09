package com.smartbackup;


import android.app.Application;
 import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;




public class SmartBackupApp extends Application {
    private final String TAG = SmartBackupApp.this.getClass().getSimpleName();
    public static  final String URL="http://mobisoftseo.com/joyband/api/";
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static SmartBackupApp mInstance;
    public static final String OFFLINE_DIR = "";

    @Override
    public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());
        mInstance = this;
      // MultiDex.install(this);
       //Thread.setDefaultUncaughtExceptionHandler(new RuntimeExceptionHandler(this));
    }

    public static synchronized SmartBackupApp getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }
public static int Counter=0;
    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            //mImageLoader = new ImageLoader(this.mRequestQueue,  new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


}
