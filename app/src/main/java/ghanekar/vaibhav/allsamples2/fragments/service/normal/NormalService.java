package ghanekar.vaibhav.allsamples2.fragments.service.normal;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

import ghanekar.vaibhav.allsamples2.utils.Logger;
import ghanekar.vaibhav.allsamples2.utils.Toaster;

public class NormalService extends Service {

    private Handler handler;

    public NormalService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toaster.showDefaultToast(NormalService.this,String.valueOf(System.currentTimeMillis()),
                                Gravity.CENTER,Toast.LENGTH_SHORT);
                        //Toast.makeText(NormalService.this, String.valueOf(System.currentTimeMillis()), Toast.LENGTH_SHORT).show();
                        handler.postDelayed(this, 1000);
                    }
                }, 1000);
            }
        });
        thread.start();
        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
