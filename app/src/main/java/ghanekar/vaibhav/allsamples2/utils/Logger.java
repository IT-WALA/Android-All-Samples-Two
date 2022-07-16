package ghanekar.vaibhav.allsamples2.utils;

import android.util.Log;


/**
 * Created by Vaibhav Ghanekar on 08-01-2018.
 */

public class Logger {

    private static String LOGKEY = "APP_NAME";
    private static String TYPE_VERBOSE = "VERBOSE";
    private static String TYPE_ERROR = "ERROR";
    private static boolean isLogEnabled = true;

    public static void LogVerbose(String val) {
        if (isLogEnabled) {
            showLongLog(val, TYPE_VERBOSE);
        }
    }

    private static void showLongLog(String val, String type) {
        int maxLogSize = 1000;
        for (int i = 0; i <= val.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i + 1) * maxLogSize;
            end = end > val.length() ? val.length() : end;
            if (type.equals(TYPE_VERBOSE))
                Log.v(LOGKEY, val.substring(start, end));
            else
                Log.e(LOGKEY, val.substring(start, end));
        }
    }

    public static void LogError(String val) {
        if (isLogEnabled)
            showLongLog(val, TYPE_ERROR);
    }
}
