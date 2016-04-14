package com.geotag.tagx5.geotag;

/**
 * Created by csastudent2015 on 4/14/16.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by csastudent2015 on 2/25/16.
 */
public class DialogHelper {
    public static AlertDialog createErrorDialog(Context context, String title, String message, String yesButton){

        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(yesButton, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        dialog.dismiss();
                    }})
                .create();
    }
}
