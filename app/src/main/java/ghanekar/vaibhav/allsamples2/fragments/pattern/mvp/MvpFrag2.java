package ghanekar.vaibhav.allsamples2.fragments.pattern.mvp;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.fragments.pattern.mvp.MvpContract.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class MvpFrag2 extends BaseFragment implements Frag2View {

    @InjectView(R.id.tvFinalValue)
    TextView tvFinalValue;

    public MvpFrag2() {
        // Required empty public constructor
    }

    public static final String TAG = MvpFrag2.class.getSimpleName() + "_";
    private Activity activity;
    MvpPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mvp_frag2, container, false);
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
        presenter = new MvpPresenterImpl(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void setFinalResult(int result) {
        tvFinalValue.setText(result + "");
    }
}
