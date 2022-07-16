package ghanekar.vaibhav.allsamples2.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import ghanekar.vaibhav.allsamples2.R;

/**
 * Created by Vaibhav Ghanekar on 08-01-2018.
 */

public class Toaster {

    private static boolean isToastEnabled = true;
    public static final String TOAST_ERROR = "TOAST_ERROR";
    public static final String TOAST_SUCCESS = "TOAST_SUCCESS";
    public static final String TOAST_WARNING = "TOAST_WARNING";
    public static final String TOAST_NORMAL = "TOAST_NORMAL";
    private static Toast toast;

    public static void showDefaultToast(final Context context, final String val, final int position, final int duration) {
        if (isToastEnabled) {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(context, val, Toast.LENGTH_SHORT);
                        toast.setDuration(duration);
                        toast.setGravity(position, 0, 0);
                        toast.show();
                    }
                });
            } else {
                Toast toast = Toast.makeText(context, val, Toast.LENGTH_SHORT);
                toast.setDuration(duration);
                toast.setGravity(position, 0, 0);
                toast.show();
            }
        }
    }

    @SuppressLint("ResourceType")
    public static void showCustomToast(Activity activity, View view, String msg, int position, int duration, String toastType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) view.findViewById(R.id.toast_layout_root));

        switch (toastType) {
            case TOAST_NORMAL:
                layout.setBackgroundColor(activity.getResources().getColor(R.color.colorPrimary));
                break;
            case TOAST_SUCCESS:
                layout.setBackgroundColor(activity.getResources().getColor(R.color.green));
                break;
            case TOAST_ERROR:
                layout.setBackgroundColor(activity.getResources().getColor(R.color.red));
                break;
            case TOAST_WARNING:
                layout.setBackgroundColor(activity.getResources().getColor(R.color.yellow));
                break;
        }
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(msg);

        toast = new Toast(activity);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public static void cancelToast() {
        toast.cancel();
    }
}
