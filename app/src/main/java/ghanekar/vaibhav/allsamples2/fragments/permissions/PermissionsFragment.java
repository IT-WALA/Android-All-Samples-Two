package ghanekar.vaibhav.allsamples2.fragments.permissions;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.permissions.utils.FragmentManagePermission;
import ghanekar.vaibhav.allsamples2.fragments.permissions.utils.PermissionResult;
import ghanekar.vaibhav.allsamples2.fragments.permissions.utils.PermissionUtils;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.utils.Logger;

/**
 * A simple {@link Fragment} subclass.
 */
public class PermissionsFragment extends FragmentManagePermission {

    @InjectView(R.id.btnAskPermission)
    Button btnAskPermission;

    public PermissionsFragment() {
        // Required empty public constructor
    }

    public static final String TAG = PermissionsFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_permissions, container, false);
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
        setToolBar(activity, Constants.TITLE_PERMISSIONS, true, this);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.btnAskPermission)
    public void onViewClicked() {
    }

}
