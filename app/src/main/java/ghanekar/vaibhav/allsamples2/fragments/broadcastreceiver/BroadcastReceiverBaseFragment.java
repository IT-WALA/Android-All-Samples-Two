package ghanekar.vaibhav.allsamples2.fragments.broadcastreceiver;


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
import ghanekar.vaibhav.allsamples2.fragments.broadcastreceiver.contextregistered.ContextRegisteredBroadcastReceiverFragment;
import ghanekar.vaibhav.allsamples2.utils.AnimateTo;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class BroadcastReceiverBaseFragment extends BaseFragment {

    @InjectView(R.id.btnManifestBroadcast)
    Button btnManifestBroadcast;
    @InjectView(R.id.btnContextRegisteredBroadcast)
    Button btnContextRegisteredBroadcast;

    public BroadcastReceiverBaseFragment() {
        // Required empty public constructor
    }

    public static final String TAG = BroadcastReceiverBaseFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_broadcast_receiver_base, container, false);
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
        setToolBar(activity, Constants.TITLE_BROADCAST_RECEIVER, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnManifestBroadcast, R.id.btnContextRegisteredBroadcast})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnManifestBroadcast:
                break;
            case R.id.btnContextRegisteredBroadcast:
                gotoFragment(new ContextRegisteredBroadcastReceiverFragment(), R.id.container, true, null, TAG,
                        true, AnimateTo.ANIMATE_INOUT);
                break;
        }
    }
}
