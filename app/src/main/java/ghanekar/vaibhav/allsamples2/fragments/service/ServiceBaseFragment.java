package ghanekar.vaibhav.allsamples2.fragments.service;


import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.constraint.ConstraintLayout;
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
import ghanekar.vaibhav.allsamples2.fragments.service.bounded.BoundedService;
import ghanekar.vaibhav.allsamples2.fragments.service.foregroundservice.MyForegroundService;
import ghanekar.vaibhav.allsamples2.fragments.service.intent.MyIntentService;
import ghanekar.vaibhav.allsamples2.fragments.service.normal.NormalService;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.utils.Logger;
import ghanekar.vaibhav.allsamples2.utils.Toaster;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceBaseFragment extends BaseFragment {

    @InjectView(R.id.btnBounderService)
    Button btnBounderService;
    @InjectView(R.id.btnIntentService)
    Button btnIntentService;
    @InjectView(R.id.btnNormalService)
    Button btnNormalService;
    @InjectView(R.id.btnStopBoundedService)
    Button btnStopBoundedService3;
    @InjectView(R.id.btnStopIntentService)
    Button btnStopIntentService;
    @InjectView(R.id.btnStopNormalService)
    Button btnStopNormalService;
    @InjectView(R.id.btnStopBoundedService)
    Button btnStopBoundedService2;
    @InjectView(R.id.btnForeGroundService)
    Button btnForeGroundService;
    @InjectView(R.id.btnStopForegroundService)
    Button btnStopForegroundService;

    public ServiceBaseFragment() {
        // Required empty public constructor
    }

    public static final String TAG = ServiceBaseFragment.class.getSimpleName() + "_";
    private Activity activity;
    private Intent foregroundServiceIntent, normalServiceIntent, intentServiceIntent, boundedServiceIntent;
    private BoundedService boundedService;
    boolean isBound = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service_base, container, false);
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
        setToolBar(activity, Constants.TITLE_SERVICE, true, this);
    }

    @Override
    public void onStart() {
        super.onStart();
        boundedServiceIntent = new Intent(activity, BoundedService.class);
        activity.bindService(boundedServiceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @OnClick({R.id.btnStopForegroundService, R.id.btnForeGroundService, R.id.btnStopBoundedService, R.id.btnStopIntentService, R.id.btnBounderService, R.id.btnIntentService, R.id.btnNormalService, R.id.btnStopNormalService})
    public void onViewClicked(View view) {
        try {
            switch (view.getId()) {
                case R.id.btnNormalService:
                    normalServiceIntent = new Intent(activity, NormalService.class);
                    activity.startService(normalServiceIntent);
                    break;
                case R.id.btnIntentService:
                    intentServiceIntent = new Intent(activity, MyIntentService.class);
                    activity.startService(intentServiceIntent);
                    break;
                case R.id.btnBounderService:
                    if (isBound) {
                        Toaster.showDefaultToast(activity, String.valueOf(boundedService.getTime()), Gravity.CENTER, Toast.LENGTH_SHORT);
                    }
                    break;
                case R.id.btnStopNormalService:
                    activity.stopService(normalServiceIntent);
                    break;
                case R.id.btnStopIntentService:
                    intentServiceIntent = new Intent(activity, MyIntentService.class);
                    intentServiceIntent.putExtra("key","stop");
                    activity.startService(intentServiceIntent);
                    break;
                case R.id.btnStopBoundedService:
                    activity.unbindService(serviceConnection);
                    isBound = false;
                    break;
                case R.id.btnForeGroundService:
                    foregroundServiceIntent = new Intent(activity, MyForegroundService.class);
                    activity.startService(foregroundServiceIntent);
                    break;
                case R.id.btnStopForegroundService:
                    Logger.LogVerbose("Hi");
                    foregroundServiceIntent = new Intent(activity, MyForegroundService.class);
                    foregroundServiceIntent.putExtra("key", "stop");
                    activity.startService(foregroundServiceIntent);
                    break;
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundedService.LocalBinder localBinder = (BoundedService.LocalBinder) service;
            boundedService = localBinder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
}
