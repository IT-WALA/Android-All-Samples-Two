package ghanekar.vaibhav.allsamples2.fragments.workmanager;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.utils.Logger;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkManagerFragment extends BaseFragment {

    @InjectView(R.id.btnSimpleWork)
    Button btnSimpleWork;
    @InjectView(R.id.btnPeriodicWork)
    Button btnPeriodicWork;
    @InjectView(R.id.btnDelayedWork)
    Button btnDelayedWork;
    private int minute;
    private int hour;

    public WorkManagerFragment() {
        // Required empty public constructor
    }

    public static final String TAG = WorkManagerFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_work_manager, container, false);
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
        setToolBar(activity, Constants.TITLE_WORKMANAGER, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnSimpleWork, R.id.btnPeriodicWork, R.id.btnDelayedWork})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSimpleWork:
                doSimpleWork();
                break;
            case R.id.btnPeriodicWork:
                doPeriodicWork();
                break;
            case R.id.btnDelayedWork:
                doDelayedWork();
                break;
        }
    }

    private void doPeriodicWork() {
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(PeriodicWorker.class, 15, TimeUnit.MINUTES)
                .addTag(TAG)
                .build();
        WorkManager.getInstance().enqueue(periodicWorkRequest);
    }

    private void doDelayedWork() {
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(DelayedWorker.class)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .addTag(TAG)
                .build();
        WorkManager.getInstance().enqueue(workRequest);
    }

    private void doSimpleWork() {
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(SimpleWorker.class)
                .addTag(TAG)
                .build();
        WorkManager.getInstance().enqueue(workRequest);
    }

    @Override
    public void onPause() {
        //WorkManager.getInstance().cancelAllWorkByTag(TAG);
        super.onPause();
    }
}
