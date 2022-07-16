package ghanekar.vaibhav.allsamples2.fragments.toolbar;


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
import ghanekar.vaibhav.allsamples2.fragments.materialbutton.MaterialButtonFragment;
import ghanekar.vaibhav.allsamples2.utils.AnimateTo;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToolbarFragment extends BaseFragment {


    @InjectView(R.id.buttonCollapsingToolbar)
    Button buttonCollapsingToolbar;

    public ToolbarFragment() {
        // Required empty public constructor
    }

    public static final String TAG = ToolbarFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_toolbar, container, false);
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
        setToolBar(activity, Constants.TITLE_TOOLBAR, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.buttonCollapsingToolbar)
    public void onViewClicked() {
        gotoFragment(new CollapsingToolbarFragment(), R.id.rootContainer, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
    }
}
