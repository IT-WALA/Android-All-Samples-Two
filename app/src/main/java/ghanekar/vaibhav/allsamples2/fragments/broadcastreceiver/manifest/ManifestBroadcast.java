package ghanekar.vaibhav.allsamples2.fragments.broadcastreceiver.manifest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.widget.Toast;

import ghanekar.vaibhav.allsamples2.utils.Logger;
import ghanekar.vaibhav.allsamples2.utils.Toaster;

public class ManifestBroadcast extends BroadcastReceiver {

    public static final String TAG = ManifestBroadcast.class.getSimpleName() + "_";

    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuilder sb = new StringBuilder();
        sb.append("Action: " + intent.getAction() + "\n");
        sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
        String log = sb.toString();
        Logger.LogVerbose(TAG + log);
        Toaster.showDefaultToast(context, log, Gravity.CENTER, Toast.LENGTH_LONG);
    }
}
