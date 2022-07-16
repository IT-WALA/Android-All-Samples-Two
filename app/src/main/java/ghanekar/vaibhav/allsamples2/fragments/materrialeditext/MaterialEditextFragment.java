package ghanekar.vaibhav.allsamples2.fragments.materrialeditext;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.materialbutton.MaterialButtonFragment;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaterialEditextFragment extends BaseFragment {

    @InjectView(R.id.tiedError)
    TextInputLayout tiedError;
    @InjectView(R.id.textInputLayout)
    TextInputLayout textInputLayout;

    public MaterialEditextFragment() {
        // Required empty public constructor
    }

    public static final String TAG = MaterialEditextFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_material_editext, container, false);
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
        setToolBar(activity, Constants.TITLE_MATERIAL_EDITEXT, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
