package ghanekar.vaibhav.allsamples2.fragments.validation;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static Validator instance = null;

    private Validator() {
    }

    public static Validator getInstance() {
        if (instance == null) {
            instance = new Validator();
        }
        return instance;
    }

    void isNotEmptyValid(String value, EditText editText, String errMsg) {
        if (TextUtils.isEmpty(value)) {
            if (TextUtils.isEmpty(errMsg)) {
                editText.setError("Field can not be empty.");
            } else {
                editText.setError(errMsg);
            }
        }
    }

    void isNumberOfDigitsValid(String value, int numberOfDigits, EditText editText, String errMsg) {
        if (value.length() != numberOfDigits) {
            if (TextUtils.isEmpty(errMsg)) {
                editText.setError("Field should be of " + numberOfDigits + " only.");
            } else {
                editText.setError(errMsg);
            }
        }
    }

    void isEmailValid(String value, EditText editText, String errMsg) {
        if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            if (TextUtils.isEmpty(errMsg)) {
                editText.setError("Invalid email.");
            } else {
                editText.setError(errMsg);
            }
        }
    }

    void isPANValid(String value, EditText editText, String errMsg) {
        String regexPAN = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
        Pattern pattern = Pattern.compile(regexPAN);
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            if (TextUtils.isEmpty(errMsg)) {
                editText.setError("Invalid PAN.");
            } else {
                editText.setError(errMsg);
            }
        }
    }

}
