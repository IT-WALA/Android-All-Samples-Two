package ghanekar.vaibhav.allsamples2.fragments.workmanager;

import android.os.Handler;
import android.support.annotation.NonNull;

import androidx.work.Worker;
import ghanekar.vaibhav.allsamples2.utils.Logger;

public class PeriodicWorker extends Worker {

    public static final String TAG = PeriodicWorker.class.getSimpleName() + "_";

    @NonNull
    @Override
    public WorkerResult doWork() {
        Logger.LogVerbose(TAG + "periodic work");
        return WorkerResult.SUCCESS;
    }
}
