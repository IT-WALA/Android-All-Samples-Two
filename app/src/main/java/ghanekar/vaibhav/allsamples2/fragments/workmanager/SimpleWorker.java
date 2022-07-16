package ghanekar.vaibhav.allsamples2.fragments.workmanager;

import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.Toast;

import androidx.work.Worker;
import ghanekar.vaibhav.allsamples2.utils.Logger;
import ghanekar.vaibhav.allsamples2.utils.Toaster;

public class SimpleWorker extends Worker {

    public static final String TAG = SimpleWorker.class.getSimpleName() + "_";

    @NonNull
    @Override
    public WorkerResult doWork() {
        myWork();
        return WorkerResult.SUCCESS;
    }

    private void myWork() {
        Logger.LogVerbose(TAG + "Simple work");
    }
}
