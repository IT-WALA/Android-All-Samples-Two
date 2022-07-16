package ghanekar.vaibhav.allsamples2.fragments.notification;

import android.content.Context;
import android.support.v4.app.NotificationCompat;

import ghanekar.vaibhav.allsamples2.R;

public class SimpleNotification extends AbstractNotification {

    public SimpleNotification(Context context) {
        super(context);
    }

    void prepareNotification(String title, String msg) {
        builder.setSmallIcon(R.drawable.android);
        builder.setContentTitle(title);
        builder.setContentText(msg);
        builder.setContentInfo("ContentInfoContentInfoContentInfoContentInfo");
        builder.setSubText("Subtext");
        builder.setTicker("TickerTickerTickerTicker");
        builder.setColor(context.getResources().getColor(R.color.white));
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setCategory(NotificationCompat.CATEGORY_ALARM);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setOnlyAlertOnce(true);

        showNotification();
    }

    @Override
    void showNotification() {
        int NOTIFICATION_ID = (int) (System.currentTimeMillis() / 1000);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
