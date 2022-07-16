package ghanekar.vaibhav.allsamples2.fragments.viewpager.dynamic_viewpager;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicViewPagerFragment extends BaseFragment {


    @InjectView(R.id.viewPager)
    ViewPager viewPager;
    @InjectView(R.id.pager_dots)
    LinearLayout pagerDots;

    public DynamicViewPagerFragment() {
        // Required empty public constructor
    }

    private ArrayList<android.support.v4.app.Fragment> fragmentList;
    private String[] mStringArray;
    public static final String TAG = DynamicViewPagerFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dynamic_view_pager, container, false);
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

        setupViewPager();
    }

    private void setupViewPager() {
        List<android.support.v4.app.Fragment> fragmentList = getFragmentsList();
        DynamicViewPagerAdapter dynamicViewPagerAdapter = new DynamicViewPagerAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(dynamicViewPagerAdapter);

        ViewPagerUtil.getInstance().setupIndicator(activity, viewPager, pagerDots, fragmentList.size());
    }

    private List<android.support.v4.app.Fragment> getFragmentsList() {
        fragmentList = new ArrayList<>();

        mStringArray = new String[]{"A", "B", "C"};
        for (int i = 0; i < mStringArray.length; i++) {
            fragmentList.add(ViewPagerBlankFragment.getInstance(mStringArray[i]));
        }
        return fragmentList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
