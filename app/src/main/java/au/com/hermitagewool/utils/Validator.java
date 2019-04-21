package au.com.hermitagewool.utils;

import android.support.design.widget.TextInputLayout;

public interface Validator {
    boolean isValidTextInput(TextInputLayout textInputLayout);
    boolean isValidSpinnerSelection(TextInputLayout textInputLayout);
}
