package ghanekar.vaibhav.allsamples2.fragments.materialbutton;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

public class MaterialButtonFragment extends BaseFragment {

    @InjectView(R.id.btnMaterialOne)
    MaterialButton btnMaterialOne;
    @InjectView(R.id.button)
    MaterialButton button;
    @InjectView(R.id.button2)
    MaterialButton button2;
    @InjectView(R.id.button3)
    MaterialButton button3;

    public MaterialButtonFragment() {
        // Required empty public constructor
    }

    public static final String TAG = MaterialButtonFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_material_button, container, false);
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
        setToolBar(activity, Constants.TITLE_MATERIAL_BUTTON, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
