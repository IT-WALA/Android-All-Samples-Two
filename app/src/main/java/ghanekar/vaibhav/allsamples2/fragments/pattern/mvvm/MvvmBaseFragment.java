package ghanekar.vaibhav.allsamples2.fragments.pattern.mvvm;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.pattern.mvvm.livedata.MvvmLiveDataFragment;
import ghanekar.vaibhav.allsamples2.utils.AnimateTo;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class MvvmBaseFragment extends BaseFragment {

    @InjectView(R.id.btnLiveData)
    Button btnLiveData;

    public MvvmBaseFragment() {
        // Required empty public constructor
    }

    public static final String TAG = MvvmBaseFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mvvm_base, container, false);
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
        setToolBar(activity, Constants.TITLE_MVVM, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnLiveData})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLiveData:
                gotoFragment(new MvvmLiveDataFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
        }
    }
}
