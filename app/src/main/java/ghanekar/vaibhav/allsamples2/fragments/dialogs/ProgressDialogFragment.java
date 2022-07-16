package ghanekar.vaibhav.allsamples2.fragments.dialogs;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgressDialogFragment extends BaseFragment {


    @InjectView(R.id.tvProgressdialog)
    TextView tvProgressdialog;

    public ProgressDialogFragment() {
        // Required empty public constructor
    }

    public static final String TAG = ProgressDialogFragment.class.getSimpleName() + "_";
    private Activity activity;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progress_dialog, container, false);
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
        setToolBar(activity, Constants.TITLE_PROGRESS_DIALOGS, true, this);

        showProgressDialog();
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("Title");
        progressDialog.setMessage("Message");
        progressDialog.setCancelable(true);
        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    dialog.dismiss();
                }
            }
        });
        progressDialog.setIcon(R.drawable.ic_account_circle);
        progressDialog.show();
    }

    @Override
    public void onDestroy() {
        if (nullCheck(progressDialog)) {
            progressDialog.dismiss();
        }
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
