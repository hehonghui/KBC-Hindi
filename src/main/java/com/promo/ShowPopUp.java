package com.promo;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class ShowPopUp {
    public void showDefaultPopup(final Context context, final String[] _data, final String _storeType) {
        if (_data != null) {
            int resID = context.getResources().getIdentifier(_data[2], "drawable", context.getPackageName());
            Builder alertDialogBuilder = new Builder(context);
            alertDialogBuilder.setTitle("Try our new apps");
            alertDialogBuilder.setMessage(_data[0]).setCancelable(false).setIcon(resID).setPositiveButton("Yes", new OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    ShowPopUp.this.startIntent(context, _data[1], _storeType);
                }
            }).setNegativeButton("No", new OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            alertDialogBuilder.create().show();
        }
    }

    protected void startIntent(Context context, String pkgName, String _storeType) {
        if (_storeType.equalsIgnoreCase("SAMSUNG")) {
            startSamsung(context, pkgName);
        } else if (_storeType.equalsIgnoreCase("AMAZON")) {
            startAmazon(context, pkgName);
        } else {
            startGoogle(context, pkgName);
        }
    }

    private void startAmazon(Context context, String pkgName) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("amzn://apps/android?p=" + pkgName)));
        } catch (Exception ex) {
            Log.e("Amazon Error", "" + ex.getStackTrace());
        }
    }

    private void startSamsung(Context context, String pkgName) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("samsungapps://ProductDetail/" + pkgName)));
        } catch (Exception ex) {
            Log.e("Samsung Error", "" + ex.getStackTrace());
        }
    }

    private void startGoogle(Context context, String pkgName) {
        try {
            Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + pkgName));
            browserIntent.setFlags(67108864);
            browserIntent.setFlags(3);
            context.startActivity(browserIntent);
        } catch (Exception ex) {
            Log.e("Google Error", "" + ex);
        }
    }
}
