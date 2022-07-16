package ghanekar.vaibhav.allsamples2.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.Toast;

import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.MainFragment;
import ghanekar.vaibhav.allsamples2.utils.BaseActivity;
import ghanekar.vaibhav.allsamples2.utils.Logger;
import ghanekar.vaibhav.allsamples2.utils.Toaster;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.container)
    ConstraintLayout container;

    public static final String TAG = MainActivity.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        try {
            init();
        } catch (Exception e) {
            handleException(e);
        }
        Logger.LogVerbose(TAG + "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.LogVerbose(TAG + "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.LogVerbose(TAG + "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.LogVerbose(TAG + "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.LogVerbose(TAG + "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.LogVerbose(TAG + "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.LogVerbose(TAG + "onDestroy");
    }

    private void init() {
        activity = MainActivity.this;
        InitializeSQLCipher();
        loadMainFragment();
    }

    private void InitializeSQLCipher() {
        SQLiteDatabase.loadLibs(this);
        File databaseFile = getDatabasePath("Normalsqlite.db");
        databaseFile.mkdirs();
        databaseFile.delete();
    }

    private void loadMainFragment() {
        //this check is important to retain all the fragments when the device is rotated.
        //this stops the app from starting from the first fragment over and over again when the device is rotated
        Fragment rootFragment = getSupportFragmentManager().findFragmentByTag(TAG);
        if (!nullCheck(rootFragment)) {
            Logger.LogVerbose(TAG + "fragment is null");
            gotoFragment(new MainFragment(), R.id.container, null, TAG, null);
        } else {
            Logger.LogVerbose(TAG + "fragment is not null");
        }
    }
}
