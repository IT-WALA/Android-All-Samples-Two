package ghanekar.vaibhav.allsamples2.fragments.notification;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;

import ghanekar.vaibhav.allsamples2.R;

public class ProgressBarNotification extends AbstractNotification {

    final int PROGRESS_MAX = 100;
    final int[] PROGRESS_CURRENT = {0};

    public ProgressBarNotification(Context context) {
        super(context);
    }

    void prepareNotification(String title, String msg) {
        builder.setSmallIcon(R.drawable.android);
        builder.setContentTitle(title);
        builder.setContentText(msg);
        builder.setContentInfo("ContentInfo");
        builder.setSubText("Subtext");
        builder.setColor(context.getResources().getColor(R.color.colorPrimary));
        builder.setPriority(NotificationCompat.PRIORITY_LOW);

        builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT[0], false);

        showNotification();
    }

    @Override
    void showNotification() {
        notificationManager.notify(100, builder.build());
        vibrator.vibrate(500);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PROGRESS_CURRENT[0] == PROGRESS_MAX)
                    return;
                PROGRESS_CURRENT[0] = PROGRESS_CURRENT[0] + 10;
                builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT[0], false);
                notificationManager.notify(100, builder.build());
                handler.postDelayed(this, 1000);
            }
        }, 1);
    }
}
