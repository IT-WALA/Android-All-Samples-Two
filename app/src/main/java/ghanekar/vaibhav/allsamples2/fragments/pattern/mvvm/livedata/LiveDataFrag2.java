package ghanekar.vaibhav.allsamples2.fragments.pattern.mvvm.livedata;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;

public class LiveDataFrag2 extends BaseFragment {

    @InjectView(R.id.tvFinalValue)
    TextView tvFinalValue;

    public LiveDataFrag2() {
        // Required empty public constructor
    }

    public static final String TAG = LiveDataFrag2.class.getSimpleName() + "_";
    private Activity activity;
    LiveDataViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_data_frag2, container, false);
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
                tvFinalValue.setText(counterModel.getCounter() + "");
            }
        };
        viewModel.getLiveData().observe(this, observer);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
