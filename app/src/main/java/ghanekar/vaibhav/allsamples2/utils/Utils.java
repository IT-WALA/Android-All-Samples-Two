package ghanekar.vaibhav.allsamples2.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static android.content.Context.CLIPBOARD_SERVICE;


public class Utils {

    private static Utils instance;

    public static final String DATE_FORMAT_Y_M_D = "yyyy-MM-dd";
    public static final String DATE_FORMAT_D_M_Y = "dd-MM-yyyy";
    public static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static final String DATE_FORMAT_REVERSED = "hh:mm:ss yyyy-MM-dd";

    private Utils() {

    }

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    protected boolean isGpsEnabled(Activity activity) {
        try {
            final LocationManager manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

            boolean isGpsEnabled = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            return isGpsEnabled;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    protected boolean isNetworkAvailable(Activity activity) {
        try {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //encryption
    protected byte[] encrypt(String plainText, String key) throws Exception {
        try {
            byte[] clean = plainText.getBytes();

            // Generating IV.
            int ivSize = 16;
            byte[] iv = new byte[ivSize];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            // Hashing key.
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(key.getBytes("UTF-8"));
            byte[] keyBytes = new byte[16];
            System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

            // Encrypt.
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(clean);

            // Combine IV and encrypted part.
            byte[] encryptedIVAndText = new byte[ivSize + encrypted.length];
            System.arraycopy(iv, 0, encryptedIVAndText, 0, ivSize);
            System.arraycopy(encrypted, 0, encryptedIVAndText, ivSize, encrypted.length);

            return encryptedIVAndText;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "".getBytes();
    }

    //decryption
    protected String decrypt(byte[] encryptedIvTextBytes, String key) throws Exception {
        try {
            int ivSize = 16;
            int keySize = 16;

            // Extract IV.
            byte[] iv = new byte[ivSize];
            System.arraycopy(encryptedIvTextBytes, 0, iv, 0, iv.length);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            // Extract encrypted part.
            int encryptedSize = encryptedIvTextBytes.length - ivSize;
            byte[] encryptedBytes = new byte[encryptedSize];
            System.arraycopy(encryptedIvTextBytes, ivSize, encryptedBytes, 0, encryptedSize);

            // Hash key.
            byte[] keyBytes = new byte[keySize];
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(key.getBytes());
            System.arraycopy(md.digest(), 0, keyBytes, 0, keyBytes.length);
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

            // Decrypt.
            Cipher cipherDecrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] decrypted = cipherDecrypt.doFinal(encryptedBytes);

            return new String(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    protected void restartApplication(Activity activity) {
        try {
            Intent i = activity.getPackageManager().getLaunchIntentForPackage(activity.getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void refreshFragment(Fragment fragment) {
        try {
            FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
            ft.detach(fragment).attach(fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //For load json from assest folder
    public String nbLoadJSONFromAsset(Activity activity, String file_name) {
        String json = null;
        try {
            InputStream is = activity.getAssets().open(file_name);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    //For play sound
    //raw_data = R.raw.click_sound
    public void nbPlaySound(Context context, int raw_data) {

        MediaPlayer mp = MediaPlayer.create(context, raw_data);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // TODO Auto-generated method stub
                mp.reset();
                mp.release();
                mp = null;
            }

        });
        try {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(context, raw_data);
            }
            mp.setVolume(0.5f, 0.5f);
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //For hide status bar
    public void nbHideStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT < 16) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);

        }
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        activity.getWindow().setAttributes(params);
    }

    //For check app install or not
    public boolean checkAppInstall(Activity activity, String uri) {
        PackageManager pm = activity.getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }

    //For send mail
    public void sendMail(Activity activity, String string, String sub) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{string});
        i.putExtra(Intent.EXTRA_SUBJECT, sub);
        // getResources().getString(R.string.mail_subject));
        i.putExtra(Intent.EXTRA_TEXT, "");
        try {
            activity.startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toaster.showDefaultToast(activity, "There are no email clients installed.", Gravity.BOTTOM, Toast.LENGTH_SHORT);
        }
    }

    //For dial number
    public void dialNumber(Activity activity, String phoneNumber) {

        TelephonyManager tm = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE) {
            Toaster.showDefaultToast(activity, "Can not support calling functionality", Gravity.BOTTOM, Toast.LENGTH_SHORT);
        } else {
            try {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber));
                activity.startActivity(callIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //For message
    public static void sendMessage(Context activity, String phoneNumber,
                                   String smsContent) {
        if (phoneNumber == null || phoneNumber.length() < 4) {
            return;
        }
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", smsContent);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(it);

    }

    //For has SdCard or not
    public static boolean hasSDCard() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    //For Disable screen capture
    public static void disableScreenshotFunctionality(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
    }

    //For get file size
    public static String getFileSize(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            int length = fis.available();
            if (length >= 1073741824) {
                return String.format("%.2f GB", length * 1.0 / 1073741824);
            } else if (length >= 1048576) {
                return String.format("%.2f MB", length * 1.0 / 1048576);
            } else {
                return String.format("%.2f KB", length * 1.0 / 1024);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "none";
    }

    //For Checking Service running or not
    public static boolean isServiceRunning(Context context, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> servicesList = activityManager
                .getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo si : servicesList) {
            if (className.equals(si.service.getClassName())) {
                isRunning = true;
            }
        }
        return isRunning;
    }

    //For Stop Service
    public static boolean stopRunningService(Context context, String className) {
        Intent intent_service = null;
        boolean ret = false;
        try {
            intent_service = new Intent(context, Class.forName(className));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (intent_service != null) {
            ret = context.stopService(intent_service);
        }
        return ret;
    }

    //For check App is on Background or not
    public static boolean isApplicationInBackground(Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName topActivity = taskList.get(0).topActivity;
            if (topActivity != null
                    && !topActivity.getPackageName().equals(
                    context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    //For Share Image
    public static void shareImage(Context context, Uri uri, String title) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/jpeg");
        context.startActivity(Intent.createChooser(shareIntent, title));
    }

    //For Share Text
    public static void shareText(Context context, String extraText) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Text");
        intent.putExtra(Intent.EXTRA_TEXT, extraText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(
                Intent.createChooser(intent, "Choose"));
    }

    //#Image
    public static Bitmap getBitmapFromFile(String path) {
        File imageFile = new File(path);
        Bitmap bitmap = null;
        if (imageFile.exists()) {
            bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        }
        return bitmap;
    }

    public static Bitmap getBitmapFromURI(Uri uri, Context context) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static byte[] getByteFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] outBuffer = stream.toByteArray();
        return outBuffer;
    }

    public static Bitmap getBitmapFromByte(byte[] bytes) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bitmap;
    }

    //For get BaseEncode64 String from image
    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String temp = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return temp;
    }

    //For get image uri
    public Uri getImageUri(Activity activity, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(activity.getContentResolver(), inImage, UUID.randomUUID().toString() + ".png", "drawing");
        return Uri.parse(path);
    }

    //For save image- handle permission yourself
    public String saveImageBitmap(Bitmap bitmap, String name, String path) {
        File file = null;
        try {
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + path);
            myDir.mkdirs();
            file = new File(myDir, name);
            if (file.exists()) {
                return file.toString();
            }
            FileOutputStream out = new FileOutputStream(file);
            //UPDATE IMAGE QUALITY NIKHIL
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.toString();
    }

    //For getting Bitmap from any view
    public static Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        view.layout(view.getLeft(), view.getTop(), view.getRight(),
                view.getBottom());
        view.draw(canvas);

        return bitmap;
    }

    //For take screenshot with status bar return Bitmap
    public static Bitmap getScreenShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenSize(activity)[0];
        int height = getScreenSize(activity)[1];
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }

    //For take screenshot without status bar return Bitmap
    public static Bitmap getScreenShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = getScreenSize(activity)[0];
        int height = getScreenSize(activity)[1];
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }

    //#Date-Time
    public static String getCurrentDateTime(String format) {
        return getTimeByFormat(format);
    }

    public static String getCurrentTime(String format) {
        return getTimeByFormat(format);
    }

    public static String getCurrentDateTime() {
        return getTimeByFormat("dd-MMM-yyyy hh:mm:ss");
    }

    public static String getCurrentTime() {
        return getTimeByFormat("hh:mm");
    }

    private static String getTimeByFormat(String format) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(format);
        df.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().getID()));
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    //For date conversion
    //  ptn1="given date format"
    //  ptn2="desired date format"
    public String convertDate(String ptn1, String ptn2, String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(ptn1, Locale.US);
        Date testDate = null;
        try {
            testDate = sdf.parse(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat(ptn2, Locale.US);
        return formatter.format(testDate);
    }

    //For get date difference
    public String getDateDifference(Date startDate, Date endDate) {
        try {
            long different = endDate.getTime() - startDate.getTime();
            if (different <= 0) {
                return "0";
            }
            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;

            long elapsedSeconds = different / secondsInMilli;

            String mSec = "";
            if (elapsedSeconds <= 9)
                mSec = "0";
            return " 0" + elapsedMinutes + " : " + mSec + elapsedSeconds + "";
            // return elapsedMinutes + " min " + elapsedSeconds + " Sec";

        } catch (Exception e) {
            return "0";
        }
    }

    //#Device
    @SuppressLint("MissingPermission")
    public static String getDeviceIMEI(Context context) {
        String imei = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            if (android.os.Build.VERSION.SDK_INT >= 26) {
                imei = telephonyManager.getImei();
            } else {
                imei = telephonyManager.getDeviceId();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return imei;
    }

    //For check Wifi connected or not
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    //For check 3G service running or not
    public static boolean is3G(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    //For check 4G service running or not
    public static boolean is4G(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.isConnectedOrConnecting()) {
            if (activeNetInfo.getType() == TelephonyManager.NETWORK_TYPE_LTE) {
                return true;
            }
        }
        return false;
    }

    //For check device is mobile ot tablet
    public boolean isPhoneDevice(Activity activity) {
        return activity.getPackageManager().hasSystemFeature(PackageManager.FEATURE_TELEPHONY);
    }

    //For check device is rooted
    public static boolean isDeviceRooted() {
        String su = "su";
        String[] locations = {"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/",
                "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/"};
        for (String location : locations) {
            if (new File(location + su).exists()) {
                return true;
            }
        }
        return false;
    }

    //For get sdk version
    public static int getSDKVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }

    //For get Android ID
    public String getAndroidID(Activity activity) {
        return Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    //For get device manufacture
    public static String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    //For get device model
    public static String getDeviceModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }

    //For Install apk(File & Uri)
    public static void installApk(Context context, Uri file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(file, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    //For Uninstall apk
    public static void uninstallApk(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        Uri packageURI = Uri.parse("package:" + packageName);
        intent.setData(packageURI);
        context.startActivity(intent);
    }

    //For check Screen Oriented
    public static boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    //For check Screen Oriented
    public static boolean isPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    //For Get the screen dimensions
    public static int[] getScreenSize(Activity activity) {
        Point size = new Point();
        WindowManager w = activity.getWindowManager();

        w.getDefaultDisplay().getSize(size);
        return new int[]{size.x, size.y};
    }

    //#playstore
    public static void RateApp(Context c, String PackageName) {
        String attach = "details?id=";
        xmethod(c, attach + PackageName);
    }

    public static void FollowOnFb(Context c, String PageId, String PageLink) {
        Intent fbintent;
        try {
            fbintent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + PageId));
            c.startActivity(fbintent);
        } catch (Exception e) {
            fbintent = new Intent(Intent.ACTION_VIEW, Uri.parse(PageLink));
            c.startActivity(fbintent);
        }

    }

    public static void MoreApps(Context c, String Dev_Name) {
        String attach = "developer?id=";
        xmethod(c, attach + Dev_Name);
    }

    public static void Feedback(Context c, String Mail, String Subject) {
        Intent intentmail = new Intent(Intent.ACTION_VIEW);
        Uri datas = Uri.parse("mailto:" + Mail + "?subject=" + Subject);
        intentmail.setData(datas);
        c.startActivity(intentmail);
    }

    public static void ShareApp(Context c, String PackageName, String Subject, String Description) {
        String applink = "https://play.google.com/store/apps/details?id=" + PackageName;
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_SUBJECT, Subject);
        share.putExtra(Intent.EXTRA_TEXT, Description + ": " + applink);
        c.startActivity(Intent.createChooser(share, "Share via"));
    }

    public static void CopyText(Context c, String text) {
        ClipboardManager clipboardManager = (ClipboardManager) c.getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Text Copied", text);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(clipData);
            Toaster.showDefaultToast(c, "Text Copied", Gravity.BOTTOM, Toast.LENGTH_SHORT);
        }
    }

    public static void LaunchUrl(Context c, String url) {
        try {
            c.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(url)));
        } catch (Exception e) {
            if (url.startsWith("http://") || url.startsWith("https;//"))
                Toaster.showDefaultToast(c, "No Browser Installed", Gravity.BOTTOM, Toast.LENGTH_SHORT);
            Toaster.showDefaultToast(c, "Invalid url,it must start with http://", Gravity.BOTTOM, Toast.LENGTH_SHORT);
        }
    }

    @SuppressLint("InlinedApi")
    private static void xmethod(Context c, String name) {
        String applink = "https://play.google.com/store/apps/";
        Uri love = Uri.parse("market://" + name);
        Intent lc = new Intent(Intent.ACTION_VIEW, love);
        //noinspection deprecation
        lc.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            c.startActivity(lc);
        } catch (ActivityNotFoundException e) {
            c.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(applink + name)));
        }
    }
}
