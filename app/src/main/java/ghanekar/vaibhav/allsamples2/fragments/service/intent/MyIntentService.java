package ghanekar.vaibhav.allsamples2.fragments.service.intent;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import ghanekar.vaibhav.allsamples2.utils.Logger;
import ghanekar.vaibhav.allsamples2.utils.Toaster;

public class MyIntentService extends IntentService {

    private static Handler handler;

    public MyIntentService() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String stop = null;
        if (null != intent) {
            stop = intent.getStringExtra("key");
        }
        if (!TextUtils.isEmpty(stop)) {
            if (null != handler) {
                handler.removeCallbacksAndMessages(null);
            }
        } else {
            handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toaster.showDefaultToast(MyIntentService.this, String.valueOf(System.currentTimeMillis()), Gravity.CENTER, Toast.LENGTH_SHORT);
                    handler.postDelayed(this, 1000);
                }
            }, 1000);
        }

    }

    @Override
    public void onDestroy() {
        Logger.LogVerbose("TAG" + "onDestroy");
        super.onDestroy();
    }
}
