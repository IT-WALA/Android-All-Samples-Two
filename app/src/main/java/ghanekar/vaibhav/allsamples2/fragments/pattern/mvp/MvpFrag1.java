package ghanekar.vaibhav.allsamples2.fragments.pattern.mvp;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
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
import ghanekar.vaibhav.allsamples2.fragments.pattern.mvp.MvpContract.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class MvpFrag1 extends BaseFragment implements Frag1View {

    @InjectView(R.id.btnMinus)
    Button btnMinus;
    @InjectView(R.id.tvResult)
    TextView tvResult;
    @InjectView(R.id.btnPlus)
    Button btnPlus;

    public MvpFrag1() {
        // Required empty public constructor
    }

    public static final String TAG = MvpFrag1.class.getSimpleName() + "_";
    private Activity activity;
    private MvpPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mvp_frag1, container, false);
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

    @OnClick({R.id.btnMinus, R.id.btnPlus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnMinus:
                presenter.onMinusClicked();
                break;
            case R.id.btnPlus:
                presenter.onPlusClicked();
                break;
        }
    }

    @Override
    public void showValue(int counter) {
        tvResult.setText(counter + "");
    }
}
