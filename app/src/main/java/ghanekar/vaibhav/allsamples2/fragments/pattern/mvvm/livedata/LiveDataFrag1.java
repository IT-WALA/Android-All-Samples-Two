package ghanekar.vaibhav.allsamples2.fragments.pattern.mvvm.livedata;


import android.app.Activity;
import android.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveDataFrag1 extends BaseFragment {

    @InjectView(R.id.btnMinus)
    Button btnMinus;
    @InjectView(R.id.tvResult)
    TextView tvResult;
    @InjectView(R.id.btnPlus)
    Button btnPlus;

    public LiveDataFrag1() {
        // Required empty public constructor
    }

    public static final String TAG = LiveDataFrag1.class.getSimpleName() + "_";
    private Activity activity;
    private LiveDataViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_data_frag1, container, false);
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
        viewModel = ViewModelProviders.of(getActivity()).get(LiveDataViewModel.class);
        Observer<CounterModel> observer = new Observer<CounterModel>() {
            @Override
            public void onChanged(@Nullable CounterModel counterModel) {
                tvResult.setText(counterModel.getCounter() + "");
            }
        };
        viewModel.getLiveData().observe(this, observer);
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
                viewModel.decrementCounter();
                break;
            case R.id.btnPlus:
                viewModel.incrementCounter();
                break;
        }
    }
}
