package ghanekar.vaibhav.allsamples2.fragments.notification;

import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import ghanekar.vaibhav.allsamples2.R;

public class CustomNotification extends AbstractNotification {

    public CustomNotification(Context context) {
        super(context);
    }

    void prepareNotification() {
        // Get the layouts to use in the custom notification
        RemoteViews notificationLayout = new RemoteViews(context.getPackageName(), R.layout.custom_toast);

        // Apply the layouts to the notification
        builder.setStyle(new NotificationCompat.DecoratedCustomViewStyle());
        builder.setCustomContentView(notificationLayout);
        builder.setSmallIcon(R.drawable.crop_image_menu_flip);

        showNotification();
    }

    @Override
    void showNotification() {
        notificationManager.notify(300, builder.build());
        vibrator.vibrate(500);
    }
}
