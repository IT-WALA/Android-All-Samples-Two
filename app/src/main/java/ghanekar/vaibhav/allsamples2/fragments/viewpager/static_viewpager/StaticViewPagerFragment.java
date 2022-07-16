package ghanekar.vaibhav.allsamples2.fragments.viewpager.static_viewpager;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
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
public class StaticViewPagerFragment extends BaseFragment {


    @InjectView(R.id.viewPager)
    ViewPager viewPager;
    @InjectView(R.id.pager_dots)
    LinearLayout pagerDots;

    public StaticViewPagerFragment() {
        // Required empty public constructor
    }

    public static final String TAG = StaticViewPagerFragment.class.getSimpleName() + "_";
    private ArrayList<android.support.v4.app.Fragment> fragmentList;
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_static_view_pager, container, false);
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
        StaticViewPagerAdapter staticViewPagerAdapter = new StaticViewPagerAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(staticViewPagerAdapter);

        ViewPagerUtil.getInstance().setupIndicator(activity, viewPager, pagerDots, fragmentList.size());
        ViewPagerUtil.getInstance().onBackPressed(viewPager, getFragmentManager());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setToolBar(activity, Constants.TITLE_A, false, null);
                        break;
                    case 1:
                        setToolBar(activity, Constants.TITLE_B, false, null);
                        break;
                    case 2:
                        setToolBar(activity, Constants.TITLE_C, false, null);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private List<android.support.v4.app.Fragment> getFragmentsList() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new FragA());
        fragmentList.add(new FragB());
        fragmentList.add(new FragC());

        return fragmentList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
