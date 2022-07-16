package ghanekar.vaibhav.allsamples2.fragments.service.foregroundservice;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.service.normal.NormalService;
import ghanekar.vaibhav.allsamples2.utils.Logger;
import ghanekar.vaibhav.allsamples2.utils.Toaster;

public class MyForegroundService extends Service {

    private static final String CHANNEL_ID = "500";
    private static final CharSequence CHANNEL_NAME = "FOREGROUND_SERVICE";
    private Handler handler;
    private NotificationManager notificationManager = null;
    private NotificationCompat.Builder builder = null;

    public MyForegroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.LogVerbose("onStartCommand");
        String stop = "";

        if (null != intent) {
            stop = intent.getStringExtra("key");
        }

        if (!TextUtils.isEmpty(stop)) {
            stopForeground(true);
            if (null != handler) {
                handler.removeCallbacksAndMessages(null);
            }
        } else {
            init();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toaster.showDefaultToast(MyForegroundService.this, String.valueOf(System.currentTimeMillis()),
                                    Gravity.CENTER, Toast.LENGTH_SHORT);
                            handler.postDelayed(this, 1000);
                        }
                    }, 1000);
                }
            });
            thread.start();

        }

        return START_NOT_STICKY;
    }

    private void init() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            foregroundNotification();
    }

    @Override
    public void onDestroy() {
        if (null != handler) {
            handler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }

    private void foregroundNotification() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String Description = getString(R.string.app_name);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.android);
        builder.setContentTitle("Title");
        builder.setContentText("Message");
        builder.setColor(getResources().getColor(R.color.white));
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setCategory(NotificationCompat.CATEGORY_ALARM);
        builder.setAutoCancel(true);
        builder.setOnlyAlertOnce(true);
        builder.setOngoing(true);

        int NOTIFICATION_ID = (int) (System.currentTimeMillis() / 1000);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
        startForeground(NOTIFICATION_ID, builder.build());
    }
}
