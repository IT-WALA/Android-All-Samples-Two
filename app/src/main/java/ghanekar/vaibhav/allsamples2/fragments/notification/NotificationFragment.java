package ghanekar.vaibhav.allsamples2.fragments.notification;


import android.app.Activity;
import android.app.Fragment;
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
public class NotificationFragment extends BaseFragment {


    @InjectView(R.id.btnNormalNotif)
    Button btnNormalNotif;
    @InjectView(R.id.btnImageNotifi)
    Button btnImageNotifi;
    @InjectView(R.id.btnBigtextNotifi)
    Button btnBigtextNotifi;
    @InjectView(R.id.btnProgressbarNotif)
    Button btnProgressbarNotif;
    @InjectView(R.id.btnCustomNotif)
    Button btnCustomNotif;

    public NotificationFragment() {
        // Required empty public constructor
    }

    public static final String TAG = NotificationFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
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
        setToolBar(activity, Constants.TITLE_NOTIFICATION, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnNormalNotif, R.id.btnImageNotifi, R.id.btnBigtextNotifi, R.id.btnProgressbarNotif,
            R.id.btnCustomNotif})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnNormalNotif:
                NotificationUtil.getInstance(activity, null).sendNotification(NotificationUtil.NOTIFICATION_NORMAL);
                break;
            case R.id.btnImageNotifi:
                NotificationUtil.getInstance(activity, null).sendNotification(NotificationUtil.NOTIFICATION_WITH_IMAGE);
                break;
            case R.id.btnBigtextNotifi:
                NotificationUtil.getInstance(activity, null).sendNotification(NotificationUtil.NOTIFICATION_WITH_BIXT_TEXT);
                break;
            case R.id.btnProgressbarNotif:
                NotificationUtil.getInstance(activity, null).sendNotification(NotificationUtil.NOTIFICATION_WITH_PROGRESSBAR);
                break;
            case R.id.btnCustomNotif:
                NotificationUtil.getInstance(activity, null).sendNotification(NotificationUtil.NOTIFICATION_CUSTOM_STYLE);
                break;
        }
    }
}
