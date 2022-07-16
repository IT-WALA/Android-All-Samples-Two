package ghanekar.vaibhav.allsamples2.fragments.service.bounded;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import ghanekar.vaibhav.allsamples2.utils.Logger;

public class BoundedService extends Service {

    private static final String TAG = BoundedService.class.getSimpleName() + "_";
    private final IBinder mBinder = new LocalBinder();

    public BoundedService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Logger.LogVerbose(TAG + "onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logger.LogVerbose(TAG + "onUnbind");
        return true;
    }

    public class LocalBinder extends Binder {
        public BoundedService getService() {
            return BoundedService.this;
        }
    }

    public long getTime() {
        return System.currentTimeMillis();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.LogVerbose(TAG + "onDestroyService");
    }
}
