package com.example.studentmanagesystem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Notify {
    public static void exit(Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Xác nhận để thoát..!!!");
        alertDialogBuilder.setIcon(R.drawable.dauchamhoi);
        alertDialogBuilder.setMessage("Bạn có muốn thoát không?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(1);
            }
        });
        alertDialogBuilder.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}
