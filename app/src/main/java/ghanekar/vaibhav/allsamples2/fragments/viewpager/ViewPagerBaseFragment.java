package ghanekar.vaibhav.allsamples2.fragments.viewpager;


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
import ghanekar.vaibhav.allsamples2.fragments.viewpager.dynamic_viewpager.DynamicViewPagerFragment;
import ghanekar.vaibhav.allsamples2.fragments.viewpager.static_viewpager.StaticViewPagerFragment;
import ghanekar.vaibhav.allsamples2.utils.AnimateTo;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerBaseFragment extends BaseFragment {

    @InjectView(R.id.btnDynamicViewPager)
    Button btnDynamicViewPager;
    @InjectView(R.id.btnStaticViewPager)
    Button btnStaticViewPager;

    public ViewPagerBaseFragment() {
        // Required empty public constructor
    }

    public static final String TAG = ViewPagerBaseFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_pager_base, container, false);
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
        setToolBar(activity, Constants.TITLE_VIEWPAGER, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnDynamicViewPager, R.id.btnStaticViewPager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnDynamicViewPager:
                showDynamicViewPager();
                break;
            case R.id.btnStaticViewPager:
                showStaticViewPager();
                break;
        }
    }

    private void showStaticViewPager() {
        gotoFragment(new StaticViewPagerFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
    }

    private void showDynamicViewPager() {
        gotoFragment(new DynamicViewPagerFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
    }
}
