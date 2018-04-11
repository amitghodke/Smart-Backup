package com.smartbackup.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The Class BaseActivity.
 */
public abstract class BaseActivity extends AppCompatActivity {
	/** The m progress dialog. */
	private static ProgressDialog mProgressDialog;

	/** The Constant ADD_FRAGMENT. */
	public static final int ADD_FRAGMENT = 0;

	/** The Constant REPLACE_FRAGMENT. */
	public static final int REPLACE_FRAGMENT = 1;	

	/** The Constant DEFAULT. */
	public static final int DEFAULT = 001;
	private Toolbar mToolbar;
    static IClickListener mIClickListener;
	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#on Pause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu)
	{
		if(featureId == Window.FEATURE_ACTION_BAR && menu != null){
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
    public static AlertDialog showMessageDialog(Context ctx,String msg) {
        return new AlertDialog.Builder(ctx)
                .setTitle("Message")
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
   public static interface  IClickListener
    {
        public void onClick();
       // public void onClick(Object t);
    }
    @Override
    protected void onDestroy() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
        super.onDestroy();
    }
    public static AlertDialog showMessageDialog(Context ctx,String msg,IClickListener iIClickListener) {
        mIClickListener=iIClickListener;
       return   new AlertDialog.Builder(ctx)
                .setTitle("Message")
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if(mIClickListener!=null)
                            mIClickListener.onClick();
                    }
                }).show();

    }
    public String setDate(Calendar calendar, SimpleDateFormat sdf,int year, int month, int day) {
        calendar.set(year, month, day);
        return sdf.format(calendar.getTime());
    }
    public String setDate( Date date,SimpleDateFormat sdf) {
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(date);
        return sdf.format(calendar.getTime());
    }
    public void showNoConnectionDialog()
    {
        new android.support.v7.app.AlertDialog.Builder(this)
                .setTitle("No internet connection")
                .setMessage("Please Connect to the Internet!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).show();
    }
	/**
	 * Log e.
	 *
	 * @param clazz the clazz
	 * @param msg the msg
	 */
	public void logE(Class<?> clazz, String msg) {
		Log.e(clazz.getCanonicalName(), msg);
	}

	/**
	 * Log d.
	 *
	 * @param clazz the clazz
	 * @param msg the msg
	 */
	public void logD(Class<?> clazz, String msg) {
		Log.d(clazz.getCanonicalName(), msg);
	}

	/**
	 * Log i.
	 *
	 * @param clazz the clazz
	 * @param msg the msg
	 */
	public void logI(Class<?> clazz, String msg) {
		Log.i(clazz.getCanonicalName(), msg);
	}

	/**
	 * Log v.
	 *
	 * @param clazz the clazz
	 * @param msg the msg
	 */
	public void logV(Class<?> clazz, String msg) {
		Log.v(clazz.getCanonicalName(), msg);
	}

	/**
	 * Log w.
	 *
	 * @param clazz the clazz
	 * @param msg the msg
	 */
	public void logW(Class<?> clazz, String msg) {
		Log.w(clazz.getCanonicalName(), msg);
	}

	/**
	 * Short toast.
	 *
	 * @param msg the msg
	 */
	public void shortToast(String msg) {
		Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * Long toast.
	 *
	 * @param msg the msg
	 */
	public void longToast(String msg) {
		Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_LONG).show();
	}

	/**
	 * Show progress dialog.
	 *
	 * @param context the context
	 * @param msg the msg
	 */
	public static void showProgressDialog(BaseActivity context,String msg) {
		mProgressDialog = new ProgressDialog(context);
		mProgressDialog.setMessage(msg);
		mProgressDialog.setCancelable(true);
        mProgressDialog.show();
	}

	/**
	 * Sets the message progress.
	 *
	 * @param msg the new message progress
	 */
	public static void setMessageProgress(String msg){
		if(mProgressDialog != null)
			mProgressDialog.setMessage(msg);
	}

	/**
	 * Close progress dialog.
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public  void closeProgressDialog() {
        if(BaseActivity.this.isDestroyed() || this.isFinishing())
        {
            return;
        }
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
			mProgressDialog = null;
		}
	}

	/**
	 * Fragment transaction.
	 *
	 * @param container the container
	 * @param transactionType the transaction type
	 * @param fragment the fragment
	 * @param isAddToBackStack the is add to back stack
	 * @param tag the tag
	 */
	public void fragmentTransaction(int container, int transactionType,
			Fragment fragment, boolean isAddToBackStack, String tag) {

		FragmentTransaction trans = getSupportFragmentManager()
				.beginTransaction();
		switch (transactionType) {
		case (ADD_FRAGMENT):

			trans.add(container, fragment, tag);
		break;
		case (REPLACE_FRAGMENT):

			trans.replace(container, fragment, tag);
		if (isAddToBackStack)
			trans.addToBackStack(null);

		break;

		}
		trans.commit();
	}

	/**
	 * Show toast.
	 *
	 * @param msg the msg
	 */
	protected void showToast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	protected Typeface setMyTypeface(String font){
		return Typeface.createFromAsset(getAssets(),font);

	}
	protected void setAppToolbar(Toolbar mToolbar,String mTitle) {
		this.mToolbar=mToolbar;
		setSupportActionBar(mToolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		//getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setTitle(mTitle);
	}
	protected Toolbar getAppToolbar(){
		if(null!=mToolbar)
			return mToolbar;
		return mToolbar;
	}
	protected int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
