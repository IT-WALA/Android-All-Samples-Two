package ghanekar.vaibhav.allsamples2.fragments.sharedpref;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.MainFragment;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.utils.SPUtils;
import ghanekar.vaibhav.allsamples2.utils.Toaster;

/**
 * A simple {@link Fragment} subclass.
 */
public class SharedPrefFragment extends BaseFragment {


    @InjectView(R.id.btnSave)
    Button btnSave;
    @InjectView(R.id.btnFetch)
    Button btnFetch;

    public SharedPrefFragment() {
        // Required empty public constructor
    }

    public static final String TAG = SharedPrefFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shared_pref, container, false);
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
        setToolBar(activity, Constants.TITLE_SHARED_PREF, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnSave, R.id.btnFetch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                save();
                break;
            case R.id.btnFetch:
                fetch();
                break;
        }
    }

    private void fetch() {
        Toaster.showDefaultToast(activity, SPUtils.getString(activity, "key"), Gravity.CENTER, Toast.LENGTH_SHORT);
    }

    private void save() {
        boolean isSaved = SPUtils.put(activity, "key", "value");
        if (isSaved) {
            Toaster.showDefaultToast(activity, "Saved", Gravity.CENTER, Toast.LENGTH_SHORT);
        } else {
            Toaster.showDefaultToast(activity, "Not Saved", Gravity.CENTER, Toast.LENGTH_SHORT);
        }
    }
}
