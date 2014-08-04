package com.demo.jdesousa.sharingexemple.Twitter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by j.desousa on 22/07/2014.
 */
public class AlertDialogManager {
    /**
     * Function to display simple Alert Dialog
     * @param context - application context
     * @param title - alert dialog title
     * @param message - alert message
     * @param status - success/failure (used to set icon)
     *               - pass null if you don't want icon
     * */
    public void showAlertDialog(Context context, String title, String message,
                                Boolean status) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("ALERTTILESTRING")
                .setMessage("alertNameString")
                .setCancelable(false)
                .setNegativeButton("Close",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
