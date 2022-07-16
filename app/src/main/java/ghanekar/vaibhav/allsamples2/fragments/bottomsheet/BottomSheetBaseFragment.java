package ghanekar.vaibhav.allsamples2.fragments.bottomsheet;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomSheetBaseFragment extends BaseFragment {

    @InjectView(R.id.btn_bottom_sheet)
    Button btnBottomSheet;
    @InjectView(R.id.btn_bottom_sheet_dialog)
    Button btnBottomSheetDialog;
    @InjectView(R.id.btn_bottom_sheet_dialog_fragment)
    Button btnBottomSheetDialogFragment;
    @InjectView(R.id.bottom_sheet)
    LinearLayout layoutBottomSheet;

    public BottomSheetBaseFragment() {
        // Required empty public constructor
    }

    public static final String TAG = BottomSheetBaseFragment.class.getSimpleName() + "_";
    private Activity activity;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_base, container, false);
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
        setToolBar(activity, Constants.TITLE_BOTTOMSHEET, true, this);
        setupBottomSheet();
    }

    private void setupBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        bottomSheetBehavior.setPeekHeight(56);
        bottomSheetBehavior.setHideable(false);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        btnBottomSheet.setText("Close Sheet");
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        btnBottomSheet.setText("Expand Sheet");
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btn_bottom_sheet, R.id.btn_bottom_sheet_dialog, R.id.btn_bottom_sheet_dialog_fragment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_bottom_sheet:
                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    btnBottomSheet.setText("Close sheet");
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    btnBottomSheet.setText("Expand sheet");
                }
                break;
            case R.id.btn_bottom_sheet_dialog:
                View view1 = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_dialog, null);
                BottomSheetDialog dialog = new BottomSheetDialog(activity);
                dialog.setContentView(view1);
                dialog.show();
                break;
            case R.id.btn_bottom_sheet_dialog_fragment:
                BottomSheetDialogFragment bottomSheetFragment = new BottomSheetDialogFragment();
                bottomSheetFragment.show(getFragmentManager(), bottomSheetFragment.getTag());
                break;
        }
    }
}
