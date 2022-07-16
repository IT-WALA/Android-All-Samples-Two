package ghanekar.vaibhav.allsamples2.fragments.apicall;


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
import ghanekar.vaibhav.allsamples2.fragments.apicall.retrofit.RetrofitFragment;
import ghanekar.vaibhav.allsamples2.fragments.apicall.volley.VolleyFragment;
import ghanekar.vaibhav.allsamples2.utils.AnimateTo;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApiCallBaseFragment extends BaseFragment {

    @InjectView(R.id.btnVolley)
    Button btnVolley;
    @InjectView(R.id.btnRetrofit)
    Button btnRetrofit;

    public ApiCallBaseFragment() {
        // Required empty public constructor
    }

    public static final String TAG = ApiCallBaseFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_api_call_base, container, false);
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
        setToolBar(activity, Constants.TITLE_API_CALL, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnVolley, R.id.btnRetrofit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnVolley:
                gotoFragment(new VolleyFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnRetrofit:
                gotoFragment(new RetrofitFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
        }
    }
}
