package com.smartbackup.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.WindowManager;

import com.smartbackup.SharedPrefConst;
import com.smartbackup.model.UserModel;

/**
 * Created by ganu on 4/26/2016.
 */
public class DialogHelper {
    static  DialogHelper instance;
    static  Context mContext;
    public static DialogHelper getInstance(Context context)
    {
        if(instance==null)
            instance = new DialogHelper();
        mContext=context;
        return instance;
   }
    public void showNoConnectionDialog()
    {
        new AlertDialog.Builder(mContext)
                .setTitle("No internet connection")
                .setMessage("Please Connect to the Internet!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //mContext.finish();
                    }
                }).show();
    }
    public static void showLogoutDialog(final Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        final AlertDialog alertDialog = new AlertDialog.Builder(context).setCancelable(false)
                .setTitle("Logout")
                .setMessage("Are you sure to Logout")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPrefConst.getInstance(context).setIsLoggedIn(false);
                        SharedPrefConst.getInstance(context).clearSharedPref();
                        //SharedPrefConst.getInstance(context).setUserData(new UserModel());
                        //MotoMojoApp.getInstance().clearApplicationData();
                        SharedPrefConst.getInstance(context).setIsFirstLaunch(false);
                       // Intent intent = new Intent(context, DashboardActivity.class);
                        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        //intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                       // context.startActivity(intent);
                        dialog.dismiss();

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
       // alertDialog.getWindow().setLayout(display.getWidth() * 8 / 10, display.getHeight() * 8 / 10);
        alertDialog.show();
    }
}
