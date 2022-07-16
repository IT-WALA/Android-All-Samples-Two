package ghanekar.vaibhav.allsamples2.fragments.alarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

import ghanekar.vaibhav.allsamples2.utils.Logger;

public class MyBroadCastReceiver extends BroadcastReceiver {

    public static final String TAG = MyBroadCastReceiver.class.getSimpleName() + "_";

    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.LogVerbose(TAG + "Alarm");
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);
    }
}
