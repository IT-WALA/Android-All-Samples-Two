package ghanekar.vaibhav.allsamples2.fragments.sociallogin;


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
public class SocialLoginBaseFragment extends BaseFragment {


    @InjectView(R.id.btnFacebook)
    Button btnFacebook;
    @InjectView(R.id.btnGoogle)
    Button btnGoogle;

    public SocialLoginBaseFragment() {
        // Required empty public constructor
    }

    public static final String TAG = SocialLoginBaseFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_social_login_base, container, false);
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
        setToolBar(activity, Constants.TITLE_SOCIAL_LOGIN, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnFacebook, R.id.btnGoogle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnGoogle:
                break;
            case R.id.btnFacebook:
                break;
        }
    }
}
