package ghanekar.vaibhav.allsamples2.fragments.notification;

import android.content.Context;
import android.support.v4.app.NotificationCompat;

import ghanekar.vaibhav.allsamples2.R;

public class BigTextNotification extends AbstractNotification {

    public BigTextNotification(Context context) {
        super(context);
    }

    void prepareNotification(String title, String msg, String bigText) {
        builder.setSmallIcon(R.drawable.android);
        builder.setContentTitle(title);
        builder.setContentText(msg);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(bigText));

        showNotification();
    }

    @Override
    void showNotification() {
        notificationManager.notify(100, builder.build());
        vibrator.vibrate(500);
    }
}
