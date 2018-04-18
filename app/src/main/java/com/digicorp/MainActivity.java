package com.digicorp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.digi.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Dialog dialog = Utils.showProgress(this, false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Utils.hideProgress(dialog);
                showDialog();
            }
        }, 5000);
    }

    private void showDialog() {
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        dialog.dismiss();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };
        Utils.showAlert(this, "Alert", "Test alert dialog", "YES", "NO", listener, true, true);
    }
}
