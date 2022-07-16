package ghanekar.vaibhav.allsamples2.fragments.autocompletetextview;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.MainFragment;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class AutoCompleteTextViewFragment extends BaseFragment {


    @InjectView(R.id.tvAutoComplete)
    AutoCompleteTextView tvAutoComplete;

    public AutoCompleteTextViewFragment() {
        // Required empty public constructor
    }

    public static final String TAG = AutoCompleteTextViewFragment.class.getSimpleName() + "_";
    private Activity activity;
    private String[] mSuggestions = {"Belgium", "France", "Italy", "Germany", "Spain"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_auto_complete_text_view, container, false);
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
        setToolBar(activity, Constants.TITLE_AUTOCOMPLETE_TEXTVIEW, true, this);
        setupAutoCompTv();
    }

    private void setupAutoCompTv() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(activity, android.R.layout.simple_dropdown_item_1line, mSuggestions);
        tvAutoComplete.setThreshold(3);
        tvAutoComplete.setAdapter(arrayAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
