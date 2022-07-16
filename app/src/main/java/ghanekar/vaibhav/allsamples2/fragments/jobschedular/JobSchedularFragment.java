package ghanekar.vaibhav.allsamples2.fragments.jobschedular;


import android.app.Activity;
import android.app.Fragment;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobSchedularFragment extends BaseFragment {


    @InjectView(R.id.btnSimpleJobSchedular)
    Button btnSimpleJobSchedular;
    @InjectView(R.id.btnPeriodicJob)
    Button btnPeriodicJob;
    @InjectView(R.id.btnDelayedJob)
    Button btnDelayedJob;

    public JobSchedularFragment() {
        // Required empty public constructor
    }

    public static final String TAG = JobSchedularFragment.class.getSimpleName() + "_";
    private Activity activity;
    private JobScheduler jobScheduler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_job_schedular, container, false);
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
        setToolBar(activity, Constants.TITLE_JOBSCHEDULAR, true, this);
        jobScheduler = (JobScheduler) activity.getSystemService(Context.JOB_SCHEDULER_SERVICE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnSimpleJobSchedular, R.id.btnPeriodicJob, R.id.btnDelayedJob})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSimpleJobSchedular:
                scheduleSimpleJob();
                break;
            case R.id.btnPeriodicJob:
                schedulePeriodicJob();
                break;
            case R.id.btnDelayedJob:
                scheduleDelayedJob();
                break;
        }
    }

    private void scheduleDelayedJob() {
        ComponentName componentName = new ComponentName(activity, DelayedJob.class);
        JobInfo.Builder jobInfo = new JobInfo.Builder(3, componentName);
        jobInfo.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        jobInfo.setMinimumLatency(5000);
        jobInfo.setOverrideDeadline(3000);
        jobScheduler.schedule(jobInfo.build());
    }

    private void schedulePeriodicJob() {
        ComponentName componentName = new ComponentName(activity, PeriodicJob.class);
        JobInfo.Builder jobInfo = new JobInfo.Builder(2, componentName);
        jobInfo.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        jobInfo.setPeriodic(5000);//this wont work as the periodic limit for job schedular is 15 minutes.
        jobScheduler.schedule(jobInfo.build());
    }

    private void scheduleSimpleJob() {
        ComponentName componentName = new ComponentName(activity, SimpleJob.class);
        JobInfo.Builder jobInfo = new JobInfo.Builder(1, componentName);
        jobInfo.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        jobScheduler.schedule(jobInfo.build());
    }
}
