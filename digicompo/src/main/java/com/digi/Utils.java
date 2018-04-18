package com.digi;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yogesh.kamaliya on 11/08/17.
 */

public class Utils {

    /**
     * check whether string is integer or not
     *
     * @param text - input string
     * @return true if String is integer, otherwise false
     */
    public static boolean isInteger(String text) {
        for (int i = 1; i < text.length(); i++) {
            // Check that current character is number.

            int value = (int) text.charAt(i);
            int c = value - '0';
            if (((c < 0) || (c > 9)))
                return false;
        }
        return true;
    }

    /**
     * get Application Version Code
     *
     * @param context
     * @return ApplicationVersionCode
     */
    public static int getApplicationVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException ex) {

        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * get Application Version Name
     *
     * @param context
     * @return ApplicationVersionName
     */
    public static String getApplicationVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException ex) {

        } catch (Exception e) {
        }
        return "";
    }

    /**
     * check for ExternalStorage is Available or not
     *
     * @return true if ExternalStorage is available, otherwise false
     */
    public static boolean checkExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * check for internet connection
     *
     * @param context
     * @return true if internet connection is available, otherwise false
     */
    public static final boolean isNetworkAvailable(Context context) {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = connectivityManager.getActiveNetworkInfo();
        if (nf != null && (nf.isConnected() || nf.isConnectedOrConnecting())) {
            connected = true;
        } else
            connected = false;

        return connected;
    }

    /**
     * Validate string with particular pattern
     *
     * @param strPattern
     * @param strToValidate
     * @return true if strToValidate is match with strPattern, otherwise false
     */
    public static boolean matchPattern(String strPattern, String strToValidate) {
        return Pattern.matches(strPattern, strToValidate);
    }

    /**
     * Get the unique device id
     *
     * @param context
     * @return device id
     */
    public static String getDeviceId(Context context) {
        String deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return deviceId;

    }

    /**
     * @param inputStr
     * @return true if string is not empty false if string is empty
     */
    public static boolean isEmptyString(String inputStr) {
        if (inputStr != null && !inputStr.equals("") && inputStr.toString().trim().length() > 0) {
            return false;
        } else
            return true;
    }

    /**
     * @param email
     * @return true if email is match with strPattern, otherwise false
     */
    public static boolean isEmailValid(String email) {
        String regExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@" + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|" + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
            return true;
        else
            return false;
    }

    /**
     * @param dateFormat - date format
     * @return today date
     */
    public static String getTodayDate(String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.US);
        Date today = new Date();
        return formatter.format(today);
    }

    /**
     * @param strDate
     * @param inputPattern  - inout date format
     * @param outputPattern - output date format
     * @return get formatted date
     */
    public static String changeDateFormat(String strDate, String inputPattern, String outputPattern) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = "";

        try {
            date = inputFormat.parse(strDate);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * Get date with suffix exmp 2nd, 11th
     *
     * @param strDate
     * @param inputPattern
     * @param outputPattern
     * @return
     */
    public static String getDateWithDayOfMonthSuffix(String strDate, String inputPattern, String outputPattern) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = "";

        try {
            date = inputFormat.parse(strDate);
            str = outputFormat.format(date);
            String day = changeDateFormat(strDate, inputPattern, "dd");
            if (!isEmptyString(day)) {
                str = day + getDayOfMonthSuffix(Integer.valueOf(day)) + " " + str;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getDayOfMonthSuffix(final int n) {
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    /**
     * hide input keyboard
     *
     * @param context
     */
    public static void hideSoftKeyboard(Activity context) {
        if (context.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * encode image into Base64
     *
     * @param bm
     * @return base64 string
     */
    public static String encodeImageBase64(Bitmap bm) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return encoded;
    }


    /**
     * convert SP to pixel
     *
     * @param context
     * @param sp
     * @return
     */
    public static float convertSpTopx(Context context, float sp) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        final float scale = metrics.scaledDensity;
        return sp * scale;
    }

    /**
     * convert DP to pixel
     *
     * @param dp
     * @param context
     * @return
     */
    public static float convertDpToPixel(Context context, float dp) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * show progress
     *
     * @param activity
     * @param cancelable - set cancelable true or false
     * @return
     */
    public static Dialog showProgress(Activity activity, boolean cancelable) {
        Dialog dialog;
        dialog = new Dialog(activity, R.style.AppTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custome_progress_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
        dialog.setCancelable(cancelable);
        return dialog;
    }

    /**
     * hide progress
     *
     * @param dialog
     */
    public static void hideProgress(Dialog dialog) {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    /**
     * show alert dialog
     *
     * @param context
     * @param title               - main title
     * @param message             - message
     * @param positiveButtonTitle - positive button name
     * @param negativeButtonTitle - negative button name
     * @param dialogClickListener - button listener
     * @param cancelable          - set cancelable true or false
     */
    public static void showAlert(Context context, String title, CharSequence message, String positiveButtonTitle, String negativeButtonTitle, DialogInterface.OnClickListener dialogClickListener, boolean cancelable, boolean isAnimation) {
        AlertDialog.Builder ab = new AlertDialog.Builder(context, R.style.DigiAlertDialogStyle);
        LayoutInflater inflater = ((AppCompatActivity) context).getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.raw_alert_dialog_custom, null);
        ab.setView(dialogView);
        TextView tvTitle, tvDescription;
        tvTitle = (TextView) dialogView.findViewById(R.id.tvTitle);
        tvDescription = (TextView) dialogView.findViewById(R.id.tvMsg);
        if (title.length() == 0) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(title);
        }
        if (negativeButtonTitle.length() != 0) {
            ab.setNegativeButton(negativeButtonTitle, dialogClickListener);
        }
        tvDescription.setText(message);
        ab.setPositiveButton(positiveButtonTitle, dialogClickListener);
        AlertDialog dialog = ab.create();
        dialog.setCancelable(cancelable);
        try {
            if (isAnimation)
                dialog.getWindow().getAttributes().windowAnimations = R.style.CustomAnimations_grow;
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog.show();
    }

}
