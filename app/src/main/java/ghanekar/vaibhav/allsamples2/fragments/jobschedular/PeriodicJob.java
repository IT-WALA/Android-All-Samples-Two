package ghanekar.vaibhav.allsamples2.fragments.jobschedular;

import android.app.job.JobParameters;
import android.app.job.JobService;

import ghanekar.vaibhav.allsamples2.utils.Logger;

public class PeriodicJob extends JobService {
    public static final String TAG = PeriodicJob.class.getSimpleName() + "_";

    @Override
    public boolean onStartJob(JobParameters params) {
        Logger.LogVerbose(TAG + "onStartJob");
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Logger.LogVerbose(TAG + "onStopJob");
        return false;
    }
}
