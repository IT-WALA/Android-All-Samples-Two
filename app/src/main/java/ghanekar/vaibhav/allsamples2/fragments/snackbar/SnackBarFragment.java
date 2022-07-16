package ghanekar.vaibhav.allsamples2.fragments.snackbar;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.utils.SnackBarMaker;

/**
 * A simple {@link Fragment} subclass.
 */
public class SnackBarFragment extends BaseFragment {

    public SnackBarFragment() {
        // Required empty public constructor
    }

    public static final String TAG = SnackBarFragment.class.getSimpleName() + "_";
    private Activity activity;
    private View snackBarView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_snack_bar, container, false);
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
        setToolBar(activity, Constants.TITLE_SNACKBAR, true, this);
        snackBarView = activity.findViewById(R.id.container);

        showSnackBar();
    }

    private void showSnackBar() {
        showNormalSnackBar();
    }

    private void showNormalSnackBar() {
        Snackbar snackbar = SnackBarMaker.showDefaultSnackBar(snackBarView, "This is Snackbar", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomSnackBar();
            }
        });
    }

    private void showCustomSnackBar() {
        SnackBarMaker.showCustomSnackBar(activity, snackBarView, "This is custom snackbar.", Snackbar.LENGTH_INDEFINITE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
