package ghanekar.vaibhav.allsamples2.fragments.pattern;


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
import ghanekar.vaibhav.allsamples2.fragments.MainFragment;
import ghanekar.vaibhav.allsamples2.fragments.pattern.mvp.MvpBaseFragment;
import ghanekar.vaibhav.allsamples2.fragments.pattern.mvvm.MvvmBaseFragment;
import ghanekar.vaibhav.allsamples2.utils.AnimateTo;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatternFragment extends BaseFragment {

    @InjectView(R.id.btnMvp)
    Button btnMvp;
    @InjectView(R.id.btnMvvm)
    Button btnMvvm;

    public PatternFragment() {
        // Required empty public constructor
    }

    public static final String TAG = PatternFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pattern, container, false);
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
        setToolBar(activity, Constants.TITLE_PATTERN, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnMvp, R.id.btnMvvm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnMvp:
                gotoFragment(new MvpBaseFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnMvvm:
                gotoFragment(new MvvmBaseFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
        }
    }
}
