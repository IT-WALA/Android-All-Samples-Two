package ghanekar.vaibhav.allsamples2.fragments.dialogs;


import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.MainFragment;
import ghanekar.vaibhav.allsamples2.utils.AnimateTo;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogsBaseFragment extends BaseFragment {


    @InjectView(R.id.btnProgressDialog)
    Button btnProgressDialog;
    @InjectView(R.id.btnDatePickerDialog)
    Button btnDatePickerDialog;
    @InjectView(R.id.btnTimePickerDialog)
    Button btnTimePickerDialog;
    @InjectView(R.id.btnDialogFragment)
    Button btnDialogFragment;

    public DialogsBaseFragment() {
        // Required empty public constructor
    }

    public static final String TAG = DialogsBaseFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialogs_base, container, false);
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
        setToolBar(activity, Constants.TITLE_DIALOGS, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnProgressDialog, R.id.btnDatePickerDialog, R.id.btnTimePickerDialog, R.id.btnDialogFragment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnProgressDialog:
                showProgressDialog();
                break;
            case R.id.btnDatePickerDialog:
                showDatePickerDialog();
                break;
            case R.id.btnTimePickerDialog:
                showTimePickerDialog();
                break;
            case R.id.btnDialogFragment:
                showDialogFragment();
                break;
        }
    }

    private void showDialogFragment() {
        TestDialogFragment dialogFragment = new TestDialogFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        android.support.v4.app.Fragment prev = fragmentManager.findFragmentByTag("dialog");
        if (prev != null) {
            fragmentTransaction.remove(prev);
        }
        fragmentTransaction.addToBackStack(null);
        dialogFragment.show(fragmentTransaction, "dialog");

        //dialog fragment listeners
        fragmentManager.executePendingTransactions();
        dialogFragment.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (nullCheck(dialog)) {
                    dialog.dismiss();
                }
            }
        });

        dialogFragment.getDialog().setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (nullCheck(dialog)) {
                    dialog.cancel();
                }
            }
        });
    }

    private void showTimePickerDialog() {
        gotoFragment(new TimePickerDialogFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
    }

    private void showDatePickerDialog() {
        gotoFragment(new DatePickerFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
    }

    private void showProgressDialog() {
        gotoFragment(new ProgressDialogFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
    }
}
