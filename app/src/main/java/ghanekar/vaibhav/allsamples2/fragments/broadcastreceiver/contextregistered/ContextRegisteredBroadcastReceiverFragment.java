package ghanekar.vaibhav.allsamples2.fragments.broadcastreceiver.contextregistered;


import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.utils.Logger;
import ghanekar.vaibhav.allsamples2.utils.Toaster;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContextRegisteredBroadcastReceiverFragment extends BaseFragment {

    @InjectView(R.id.btnLocalBroadcastReceiver)
    Button btnLocalBroadcastReceiver;

    public ContextRegisteredBroadcastReceiverFragment() {
        // Required empty public constructor
    }

    public static final String TAG = ContextRegisteredBroadcastReceiverFragment.class.getSimpleName() + "_";
    private Activity activity;
    private IntentFilter intentFilter, localIntentFilter;
    private Intent localIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_context_registered_broadcast_receiver, container, false);
        ButterKnife.inject(this, view);
        try {
            init();
        } catch (Exception e) {
            handleException(e);
        }
        return view;
    }

    private void init() {
        activity = getActivity();
        setToolBar(activity, Constants.TITLE_CONTEXT_REGISTERED_BROADCAST_RECEIVER, true, this);
        setIntentFilter();
    }

    private void setIntentFilter() {
        intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);

        localIntent = new Intent("localIntent");
        localIntentFilter = new IntentFilter(localIntent.getAction());
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            StringBuilder sb = new StringBuilder();
            sb.append("Action: " + intent.getAction() + "\n");
            sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
            String log = sb.toString();
            Logger.LogVerbose(TAG + log);
            Toaster.showDefaultToast(context, log, Gravity.CENTER, Toast.LENGTH_LONG);

        }
    };

    BroadcastReceiver localBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toaster.showDefaultToast(context, "Local Broadcast Receiver.", Gravity.CENTER, Toast.LENGTH_LONG);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        activity.registerReceiver(broadcastReceiver, intentFilter);
        LocalBroadcastManager.getInstance(activity).registerReceiver(localBroadcastReceiver, localIntentFilter);
    }

    @Override
    public void onPause() {
        activity.unregisterReceiver(broadcastReceiver);
        LocalBroadcastManager.getInstance(activity).unregisterReceiver(localBroadcastReceiver);
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.btnLocalBroadcastReceiver)
    public void onViewClicked() {
        Logger.LogVerbose(TAG + "clicked");
        LocalBroadcastManager.getInstance(activity).sendBroadcast(localIntent);
        //activity.sendBroadcast(localIntent);
    }
}
