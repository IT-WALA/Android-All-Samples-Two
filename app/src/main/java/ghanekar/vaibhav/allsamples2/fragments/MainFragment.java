package ghanekar.vaibhav.allsamples2.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.alarmmanager.AlarmManagerFragment;
import ghanekar.vaibhav.allsamples2.fragments.apicall.ApiCallBaseFragment;
import ghanekar.vaibhav.allsamples2.fragments.autocompletetextview.AutoCompleteTextViewFragment;
import ghanekar.vaibhav.allsamples2.fragments.bottomsheet.BottomSheetBaseFragment;
import ghanekar.vaibhav.allsamples2.fragments.broadcastreceiver.BroadcastReceiverBaseFragment;
import ghanekar.vaibhav.allsamples2.fragments.camera.CameraFragment;
import ghanekar.vaibhav.allsamples2.fragments.dialogs.DialogsBaseFragment;
import ghanekar.vaibhav.allsamples2.fragments.fileoperation.FileOpFragment;
import ghanekar.vaibhav.allsamples2.fragments.jobschedular.JobSchedularFragment;
import ghanekar.vaibhav.allsamples2.fragments.materialbutton.MaterialButtonFragment;
import ghanekar.vaibhav.allsamples2.fragments.materrialeditext.MaterialEditextFragment;
import ghanekar.vaibhav.allsamples2.fragments.menu.MenuBaseFragment;
import ghanekar.vaibhav.allsamples2.fragments.notification.NotificationFragment;
import ghanekar.vaibhav.allsamples2.fragments.orientation.OrientationFragment;
import ghanekar.vaibhav.allsamples2.fragments.pattern.PatternFragment;
import ghanekar.vaibhav.allsamples2.fragments.permissions.PermissionsFragment;
import ghanekar.vaibhav.allsamples2.fragments.pickimagefromgallery.PickImageFromGalleryFragment;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.RecyclerViewBaseFragment;
import ghanekar.vaibhav.allsamples2.fragments.service.ServiceBaseFragment;
import ghanekar.vaibhav.allsamples2.fragments.sharedpref.SharedPrefFragment;
import ghanekar.vaibhav.allsamples2.fragments.snackbar.SnackBarFragment;
import ghanekar.vaibhav.allsamples2.fragments.sociallogin.SocialLoginBaseFragment;
import ghanekar.vaibhav.allsamples2.fragments.sqlite.SqliteBaseFragment;
import ghanekar.vaibhav.allsamples2.fragments.toast.ToastFragment;
import ghanekar.vaibhav.allsamples2.fragments.toolbar.ToolbarFragment;
import ghanekar.vaibhav.allsamples2.fragments.validation.ValidationFragment;
import ghanekar.vaibhav.allsamples2.fragments.viewpager.ViewPagerBaseFragment;
import ghanekar.vaibhav.allsamples2.fragments.webview.WebViewFragment;
import ghanekar.vaibhav.allsamples2.fragments.workmanager.WorkManagerFragment;
import ghanekar.vaibhav.allsamples2.utils.AnimateTo;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.utils.Logger;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {

    @InjectView(R.id.btnRecyclerView)
    Button btnRecyclerView;
    @InjectView(R.id.btnNotification)
    Button btnNotification;
    @InjectView(R.id.btnSqlite)
    Button btnSqlite;
    @InjectView(R.id.btnService)
    Button btnService;
    @InjectView(R.id.btnBroadcastReceiver)
    Button btnBroadcastReceiver;
    @InjectView(R.id.btnApiCall)
    Button btnApiCall;
    @InjectView(R.id.btnPermissions)
    Button btnPermissions;
    @InjectView(R.id.btnContentProvider)
    Button btnContentProvider;
    @InjectView(R.id.btnSharedPref)
    Button btnSharedPref;
    @InjectView(R.id.btnFileOp)
    Button btnFileOp;
    @InjectView(R.id.btnCamera)
    Button btnCamera;
    @InjectView(R.id.btnNavigationDrawer)
    Button btnNavigationDrawer;
    @InjectView(R.id.btnTabView)
    Button btnTabView;
    @InjectView(R.id.btnViewPager)
    Button btnViewPager;
    @InjectView(R.id.btnToolbar)
    Button btnToolbar;
    @InjectView(R.id.btnMaterialButton)
    Button btnMaterialButton;
    @InjectView(R.id.btnMaterialEditText)
    Button btnMaterialEditText;
    @InjectView(R.id.btnBottomSheet)
    Button btnBottomSheet;
    @InjectView(R.id.btnBottomNavigationView)
    Button btnBottomNavigationView;
    @InjectView(R.id.btnMenus)
    Button btnMenus;
    @InjectView(R.id.btnDialogs)
    Button btnDialogs;
    @InjectView(R.id.btnAutocompleteTextView)
    Button btnAutocompleteTextView;
    @InjectView(R.id.btnWebView)
    Button btnWebView;
    @InjectView(R.id.btnMaps)
    Button btnMaps;
    @InjectView(R.id.btnSnackBar)
    Button btnSnackBar;
    @InjectView(R.id.btnSociaLogin)
    Button btnSociaLogin;
    @InjectView(R.id.btnFirebase)
    Button btnFirebase;
    @InjectView(R.id.btnToast)
    Button btnToast;
    @InjectView(R.id.btnPickImageFromGallery)
    Button btnPickImageFromGallery;
    @InjectView(R.id.btnOrientation)
    Button btnOrientation;
    @InjectView(R.id.btnPattern)
    Button btnPattern;
    @InjectView(R.id.btnWorkManager)
    Button btnWorkManager;
    @InjectView(R.id.btnAlarmManager)
    Button btnAlarmManager;
    @InjectView(R.id.btnJobSchedular)
    Button btnJobSchedular;
    @InjectView(R.id.btnValidation)
    Button btnValidation;

    public MainFragment() {
        // Required empty public constructor
    }

    public static final String TAG = MainFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);

        try {
            init();
        } catch (Exception e) {
            handleException(e);
        }
        Logger.LogVerbose(TAG + "onCreateView");
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.LogVerbose(TAG + "onCreate");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.LogVerbose(TAG + "onAttach");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.LogVerbose(TAG + "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.LogVerbose(TAG + "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.LogVerbose(TAG + "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.LogVerbose(TAG + "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.LogVerbose(TAG + "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.LogVerbose(TAG + "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.LogVerbose(TAG + "onDetach");
    }

    private void init() {
        activity = getActivity();
        setToolBar(activity, Constants.TITLE_ALL_SAMPLES, false, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        Logger.LogVerbose(TAG + "onDestroyView");
    }

    @OnClick({R.id.btnValidation,R.id.btnJobSchedular, R.id.btnAlarmManager, R.id.btnRecyclerView, R.id.btnToast, R.id.btnPickImageFromGallery,
            R.id.btnNotification, R.id.btnSqlite, R.id.btnOrientation, R.id.btnPattern, R.id.btnService, R.id.btnBroadcastReceiver,
            R.id.btnApiCall, R.id.btnPermissions, R.id.btnContentProvider,
            R.id.btnSharedPref, R.id.btnFileOp, R.id.btnCamera,
            R.id.btnNavigationDrawer, R.id.btnTabView, R.id.btnViewPager, R.id.btnToolbar,
            R.id.btnMaterialButton, R.id.btnMaterialEditText, R.id.btnBottomSheet, R.id.btnBottomNavigationView,
            R.id.btnMenus, R.id.btnDialogs, R.id.btnAutocompleteTextView, R.id.btnWebView, R.id.btnMaps,
            R.id.btnSnackBar, R.id.btnSociaLogin, R.id.btnFirebase, R.id.btnWorkManager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnMaterialButton:
                gotoFragment(new MaterialButtonFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnMaterialEditText:
                gotoFragment(new MaterialEditextFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnToolbar:
                gotoFragment(new ToolbarFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnRecyclerView:
                gotoFragment(new RecyclerViewBaseFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnToast:
                gotoFragment(new ToastFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnSnackBar:
                gotoFragment(new SnackBarFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnPickImageFromGallery:
                gotoFragment(new PickImageFromGalleryFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnSqlite:
                gotoFragment(new SqliteBaseFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnApiCall:
                gotoFragment(new ApiCallBaseFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnOrientation:
                gotoFragment(new OrientationFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnPattern:
                gotoFragment(new PatternFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnService:
                gotoFragment(new ServiceBaseFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnBroadcastReceiver:
                gotoFragment(new BroadcastReceiverBaseFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnPermissions:
                //gotoActivity(activity, PermissionsActivity.class, null, false, TAG);
                gotoFragment(new PermissionsFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnWorkManager:
                gotoFragment(new WorkManagerFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnAlarmManager:
                gotoFragment(new AlarmManagerFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnJobSchedular:
                gotoFragment(new JobSchedularFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnFileOp:
                gotoFragment(new FileOpFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnCamera:
                gotoFragment(new CameraFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnNotification:
                gotoFragment(new NotificationFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnSharedPref:
                gotoFragment(new SharedPrefFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnAutocompleteTextView:
                gotoFragment(new AutoCompleteTextViewFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnWebView:
                gotoFragment(new WebViewFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnDialogs:
                gotoFragment(new DialogsBaseFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnSociaLogin:
                gotoFragment(new SocialLoginBaseFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnBottomSheet:
                gotoFragment(new BottomSheetBaseFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnMenus:
                gotoFragment(new MenuBaseFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnViewPager:
                gotoFragment(new ViewPagerBaseFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnValidation:
                gotoFragment(new ValidationFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnContentProvider:
                break;
            case R.id.btnNavigationDrawer:
                break;
            case R.id.btnTabView:
                break;
            case R.id.btnBottomNavigationView:
                break;
            case R.id.btnMaps:
                break;
            case R.id.btnFirebase:
                break;
        }
    }
}
