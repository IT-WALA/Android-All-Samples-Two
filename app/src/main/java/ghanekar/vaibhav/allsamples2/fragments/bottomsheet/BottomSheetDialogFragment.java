package ghanekar.vaibhav.allsamples2.fragments.bottomsheet;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ghanekar.vaibhav.allsamples2.R;

public class BottomSheetDialogFragment extends android.support.design.widget.BottomSheetDialogFragment {

    public static final String TAG = BottomSheetDialogFragment.class.getSimpleName() + "_";
    private Activity activity;
    private View view;

    public static BottomSheetDialogFragment newInstance() {
        return new BottomSheetDialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false);
        ButterKnife.inject(this, view);
        try {
            init();
        } catch (Exception e) {
        }
        return view;
    }

    private void init() {
        activity = getActivity();
    }

    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
