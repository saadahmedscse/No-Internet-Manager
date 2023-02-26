package com.saadahmedev.networkutil.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.saadahmedev.networkutil.R;

public class DialogUtil {

    public static void show(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_no_internet);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
