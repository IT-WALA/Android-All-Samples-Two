package ghanekar.vaibhav.allsamples2.fragments.dialogs;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestDialogFragment extends DialogFragment {

    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.inEmail)
    EditText inEmail;
    @InjectView(R.id.inPassword)
    EditText inPassword;
    @InjectView(R.id.btnDone)
    Button btnDone;

    public TestDialogFragment() {
        // Required empty public constructor
    }

    public static final String TAG = TestDialogFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test_dialog, container, false);
        ButterKnife.inject(this, view);
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    private void init() {
        activity = getActivity();
        removeDefaultTitle();
    }

    private void removeDefaultTitle() {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
