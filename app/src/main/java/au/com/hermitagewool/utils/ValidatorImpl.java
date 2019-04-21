package au.com.hermitagewool.utils;

import android.support.design.widget.TextInputLayout;

public class ValidatorImpl implements Validator {
    @Override
    public boolean isValidTextInput(TextInputLayout textInputLayout) {
        String input =textInputLayout.getEditText().getText().toString().trim();
        if(input.isEmpty()){
            textInputLayout.setError("Field can't be empty");
            return false;
        }
        if(input.length()>15){
            textInputLayout.setError("Input too long");
            return false;
        }
        textInputLayout.setError(null);
        textInputLayout.setErrorEnabled(false);
        return true;
    }

    @Override
    public boolean isValidSpinnerSelection(TextInputLayout textInputLayout) {
        return false;
    }





}
