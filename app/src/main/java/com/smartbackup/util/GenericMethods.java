package com.smartbackup.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ganu on 1/27/2016.
 */
public class GenericMethods {

    private static ProgressDialog mProgressDialog;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    public static void changeTabsFont(Context context, TabLayout tabLayout) {

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                  //  Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + context.getString(R.string.typeface));
                    //((TextView) tabViewChild).setTypeface(tf, Typeface.NORMAL);
                    //((TextView) tabViewChild).setTextSize(12);
                }
            }
        }
    }

    /**
     * @param inputFormat  input date format
     * @param outputFormat output date format
     * @param input        input string to which format is to be set
     * @return formatted input string
     */
    public static String setDateFormat(String inputFormat, String outputFormat, String input) {
        SimpleDateFormat inputSimpleDateFormat = new SimpleDateFormat(inputFormat);
        SimpleDateFormat outputSimpleDateFormat = new SimpleDateFormat(outputFormat);
        try {
            Date date = inputSimpleDateFormat.parse(input);
            return outputSimpleDateFormat.format(date);
        } catch (Exception e) {
            return e.getMessage();
        }
    }



    public void clearApplicationData(Context contxt) {
        File cache = contxt.getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    deleteDir(new File(appDir, s));

                }
            }
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static void applyFontToMenuItem(Context context, MenuItem mi) {
        //Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/"+context.getString(R.string.typeface));
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
      //  mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }
}
