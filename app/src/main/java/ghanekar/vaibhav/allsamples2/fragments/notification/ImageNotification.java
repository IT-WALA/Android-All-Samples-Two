package ghanekar.vaibhav.allsamples2.fragments.notification;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import ghanekar.vaibhav.allsamples2.R;

public class ImageNotification extends AbstractNotification{

    public ImageNotification(Context context) {
        super(context);
    }

    void prepareNotification(String title, String msg, int image) {
        builder.setSmallIcon(R.drawable.android);
        builder.setContentTitle("Title");
        builder.setContentText("Content");
        builder.setContentInfo("ContentInfo");
        builder.setSubText("Subtext");
        builder.setColor(context.getResources().getColor(R.color.colorPrimary));
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.android);
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null));
        builder.setLargeIcon(bitmap);

        showNotification();
    }

    @Override
    void showNotification() {
        notificationManager.notify(100, builder.build());
        vibrator.vibrate(500);
    }
}
