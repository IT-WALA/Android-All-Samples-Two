package ghanekar.vaibhav.allsamples2.fragments.toast;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.utils.Toaster;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToastFragment extends BaseFragment {

    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.btnCancelToast)
    Button btnCancelToast;

    public ToastFragment() {
        // Required empty public constructor
    }

    public static final String TAG = ToastFragment.class.getSimpleName() + "_";
    private Activity activity;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_toast, container, false);
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
        setToolBar(activity, Constants.TITLE_TOAST, true, this);

        showToast();
    }

    private void showToast() {
        Toaster.showDefaultToast(activity, "normal toast", Gravity.CENTER, Toast.LENGTH_SHORT);
        Toaster.showCustomToast(activity, view, "Success", Gravity.CENTER, Toast.LENGTH_SHORT, Toaster.TOAST_SUCCESS);
        Toaster.showCustomToast(activity, view, "Error", Gravity.CENTER, Toast.LENGTH_SHORT, Toaster.TOAST_ERROR);
        Toaster.showCustomToast(activity, view, "Warning", Gravity.CENTER, Toast.LENGTH_SHORT, Toaster.TOAST_WARNING);
        Toaster.showCustomToast(activity, view, "Normal", Gravity.CENTER, Toast.LENGTH_SHORT, Toaster.TOAST_NORMAL);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.btnCancelToast)
    public void onViewClicked() {
        Toaster.cancelToast();
    }
}
