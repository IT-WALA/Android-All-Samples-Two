package ghanekar.vaibhav.allsamples2.fragments.validation;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ValidationFragment extends BaseFragment {


    @InjectView(R.id.edEmptyValidation)
    EditText edEmptyValidation;
    @InjectView(R.id.edTenDigitValidation)
    EditText edTenDigitValidation;
    @InjectView(R.id.edEmailValidation)
    EditText edEmailValidation;
    @InjectView(R.id.edRegexValidation)
    EditText edRegexValidation;
    @InjectView(R.id.btnValidate)
    Button btnValidate;

    public ValidationFragment() {
        // Required empty public constructor
    }

    public static final String TAG = ValidationFragment.class.getSimpleName() + "_";
    private Activity activity;
    private Validator validator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_validation, container, false);
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
        setToolBar(activity, Constants.TITLE_VALIDATION, true, this);
        validator = Validator.getInstance();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.btnValidate)
    public void onViewClicked() {
        validate();
    }

    private void validate() {
        String strEmptyValidation = edEmptyValidation.getText().toString().trim();
        String str10DigitValidation = edTenDigitValidation.getText().toString().trim();
        String strEmailValidation = edEmailValidation.getText().toString().trim();
        String strRegexValidation = edRegexValidation.getText().toString().trim();

        //empty
        validator.isNotEmptyValid(strEmptyValidation, edEmptyValidation, "Wrong input.");

        //number of digits
        validator.isNumberOfDigitsValid(str10DigitValidation, 10, edTenDigitValidation, "");
        validator.isNotEmptyValid(str10DigitValidation, edTenDigitValidation, "");

        //email
        validator.isEmailValid(strEmailValidation, edEmailValidation, "");
        validator.isNotEmptyValid(strEmailValidation, edEmailValidation, "");

        //PAN-Regex
        validator.isPANValid(strRegexValidation, edRegexValidation, "");
        validator.isNotEmptyValid(strRegexValidation, edRegexValidation, "");
    }
}
