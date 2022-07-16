package ghanekar.vaibhav.allsamples2.fragments.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;

import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.activities.MainActivity;

abstract public class AbstractNotification {

    private static final String CHANNEL_ID = "100";
    private static final CharSequence CHANNEL_NAME = "CHANNEL_NAME";
    final NotificationManager notificationManager;
    final Vibrator vibrator;
    Context context;
    Intent intent;
    PendingIntent pendingIntent;
    NotificationCompat.Builder builder;

    public AbstractNotification(Context context) {
        this.context = context;
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        buildBasicNotification();
    }

    void buildBasicNotification() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String Description = context.getString(R.string.app_name);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(context, 1, intent, 0);
        builder = new NotificationCompat.Builder(context, CHANNEL_ID);
    }

    abstract void showNotification();
}
