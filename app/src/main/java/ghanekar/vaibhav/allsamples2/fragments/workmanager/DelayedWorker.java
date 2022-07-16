package ghanekar.vaibhav.allsamples2.fragments.workmanager;

import android.support.annotation.NonNull;

import androidx.work.Worker;
import ghanekar.vaibhav.allsamples2.utils.Logger;

public class DelayedWorker extends Worker {

    public static final String TAG = DelayedWorker.class.getSimpleName() + "_";

    @NonNull
    @Override
    public WorkerResult doWork() {
        Logger.LogVerbose(TAG + "delayed work");
        return WorkerResult.SUCCESS;
    }
}
