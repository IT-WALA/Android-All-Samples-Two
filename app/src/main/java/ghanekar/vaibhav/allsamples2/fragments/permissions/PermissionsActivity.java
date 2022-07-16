package ghanekar.vaibhav.allsamples2.fragments.permissions;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.activities.MainActivity;
import ghanekar.vaibhav.allsamples2.fragments.permissions.utils.ActivityManagePermission;
import ghanekar.vaibhav.allsamples2.fragments.permissions.utils.PermissionResult;
import ghanekar.vaibhav.allsamples2.fragments.permissions.utils.PermissionUtils;
import ghanekar.vaibhav.allsamples2.utils.BaseActivity;
import ghanekar.vaibhav.allsamples2.utils.Logger;

public class PermissionsActivity extends ActivityManagePermission {

    public static final String TAG = PermissionsActivity.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);

        try {
            init();
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void init() {
        activity = PermissionsActivity.this;
        askPermissions();
    }

    private void askPermissions() {
        String[] permissions = {PermissionUtils.Manifest_CAMERA, PermissionUtils.Manifest_CALL_PHONE};
        askCompactPermissions(permissions, new PermissionResult() {
            @Override
            public void permissionGranted() {
                Logger.LogVerbose(TAG + "Granted.");
            }

            @Override
            public void permissionDenied() {
                openSettingsApp(activity);
            }

            @Override
            public void permissionForeverDenied() {
                openSettingsApp(activity);
            }
        });
    }

}
