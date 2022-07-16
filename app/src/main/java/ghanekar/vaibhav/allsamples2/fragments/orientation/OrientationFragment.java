package ghanekar.vaibhav.allsamples2.fragments.orientation;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrientationFragment extends BaseFragment {

    @InjectView(R.id.tvResponse)
    TextView tvResponse;
    @InjectView(R.id.btnChangeData)
    Button btnChangeData;
    @InjectView(R.id.btnChangeDataWithDelay)
    Button btnChangeDataWithDelay;
    private boolean isDelay = false;

    public OrientationFragment() {
        // Required empty public constructor
    }

    public static final String TAG = OrientationFragment.class.getSimpleName() + "_";
    private Activity activity;
    private static final String SAVE_DATE = "SAVE_DATE ";
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_orientation, container, false);
        ButterKnife.inject(this, view);
        try {
            restoreState(savedInstanceState);
            init();
        } catch (Exception e) {
            handleException(e);
        }
        return view;
    }

    private void restoreState(Bundle savedInstanceState) {
        setRetainInstance(true);
        if (nullCheck(savedInstanceState)) {
            String text = savedInstanceState.getString(SAVE_DATE);
            tvResponse.setText(text);
        }
    }

    private void init() {
        activity = getActivity();
        setToolBar(activity, Constants.TITLE_ORIENTATION, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        String text = tvResponse.getText().toString();
        outState.putString(SAVE_DATE, text);
        super.onSaveInstanceState(outState);
    }

    @OnClick({R.id.btnChangeData, R.id.btnChangeDataWithDelay})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.btnChangeData:
                if (isDelay) {
                    tvResponse.setText("wxyz");
                    isDelay = false;
                } else {
                    tvResponse.setText("abcd");
                }
                break;
            case R.id.btnChangeDataWithDelay:
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isDelay = true;
                        btnChangeData.callOnClick();
                    }
                }, 5000);
                break;
        }
    }
}
