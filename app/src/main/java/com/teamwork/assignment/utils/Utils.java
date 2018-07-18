package com.teamwork.assignment.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Utils {
    public static final String DD_MM_YYY = "dd MMM yyyy";
    private static final String YYYY_MM_DD = "yyyyMMdd";

    public static String getDateForLocaleFromUtc(String strDate, String format) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern(YYYY_MM_DD);
        DateTime dateTime = dtf.parseDateTime(strDate);
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern(format);
        return dtfOut.print(dateTime);
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
