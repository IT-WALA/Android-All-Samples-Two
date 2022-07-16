package ghanekar.vaibhav.allsamples2.fragments.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.activities.MainActivity;


public class NotificationUtil {

    private static NotificationUtil instance;
    private Context context;

    public static final String NOTIFICATION_NORMAL = "NOTIFICATION_NORMAL";
    public static final String NOTIFICATION_WITH_IMAGE = "NOTIFICATION_WITH_IMAGE";
    public static final String NOTIFICATION_WITH_BIXT_TEXT = "NOTIFICATION_WITH_BIXT_TEXT";
    public static final String NOTIFICATION_WITH_PROGRESSBAR = "NOTIFICATION_WITH_PROGRESSBAR";
    public static final String NOTIFICATION_CUSTOM_STYLE = "NOTIFICATION_CUSTOM_STYLE";

    private AbstractNotification notification = null;

    private NotificationUtil() {
    }

    public static NotificationUtil getInstance(Context context, Bundle bundle) {
        try {
            if (null == instance) {
                instance = new NotificationUtil();
            }
            instance.init(context, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    private void init(Context context, Bundle bundle) {
        this.context = context;
    }

    public void sendNotification(String notifType) {
        try {
            switch (notifType) {
                case NOTIFICATION_NORMAL:
                    notification = new SimpleNotification(context);
                    ((SimpleNotification) notification).prepareNotification("Title", "Message");
                    break;

                case NOTIFICATION_WITH_BIXT_TEXT:
                    notification = new BigTextNotification(context);
                    ((BigTextNotification) notification).prepareNotification("Title", "Message", "Much longer text that can not fit in the notification width.\\nSo we are using this big text notification to display a big notification.");
                    break;

                case NOTIFICATION_WITH_IMAGE:
                    notification = new ImageNotification(context);
                    ((ImageNotification) notification).prepareNotification("Title", "Message", R.drawable.android);
                    break;

                case NOTIFICATION_WITH_PROGRESSBAR:
                    notification = new ProgressBarNotification(context);
                    ((ProgressBarNotification) notification).prepareNotification("Title", "Message");
                    break;

                case NOTIFICATION_CUSTOM_STYLE:
                    notification = new CustomNotification(context);
                    ((CustomNotification) notification).prepareNotification();
                    break;

                default:
                    notification = new SimpleNotification(context);
                    ((SimpleNotification) notification).prepareNotification("Title", "Message");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
