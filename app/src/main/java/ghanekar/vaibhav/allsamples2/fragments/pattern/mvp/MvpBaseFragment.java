package ghanekar.vaibhav.allsamples2.fragments.pattern.mvp;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.fragments.pattern.mvp.MvpContract.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class MvpBaseFragment extends BaseFragment {

    @InjectView(R.id.frag1Container)
    RelativeLayout frag1Container;
    @InjectView(R.id.frag2Container)
    RelativeLayout frag2Container;

    public MvpBaseFragment() {
        // Required empty public constructor
    }

    public static final String TAG = MvpBaseFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mvp_base, container, false);
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
        setToolBar(activity, Constants.TITLE_MVP, true, this);

        loadFrag1();
        loadFrag2();
    }

    private void loadFrag2() {
        gotoFragment(new MvpFrag2(), R.id.frag2Container, false, null, TAG, false, "");
    }

    private void loadFrag1() {
        gotoFragment(new MvpFrag1(), R.id.frag1Container, false, null, TAG, false, "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
