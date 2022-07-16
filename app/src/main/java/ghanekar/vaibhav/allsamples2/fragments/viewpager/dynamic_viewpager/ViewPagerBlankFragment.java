package ghanekar.vaibhav.allsamples2.fragments.viewpager.dynamic_viewpager;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerBlankFragment extends BaseFragment {

    @InjectView(R.id.tvViewPager)
    TextView tvViewPager;

    public ViewPagerBlankFragment() {
        // Required empty public constructor
    }

    private static final String TAG = ViewPagerBlankFragment.class.getSimpleName() + "_";
    private Activity activity;
    private static final String KEY1 = "key1";

    public static ViewPagerBlankFragment getInstance(String msg) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY1, msg);

        ViewPagerBlankFragment blankFragment = new ViewPagerBlankFragment();
        blankFragment.setArguments(bundle);
        return blankFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_pager_blank, container, false);
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
        if (null != getArguments()) {
            String msg = getArguments().getString(KEY1);
            tvViewPager.setText(msg);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
