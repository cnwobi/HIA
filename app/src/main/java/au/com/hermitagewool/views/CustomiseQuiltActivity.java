package au.com.hermitagewool.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.com.hwool.hermitageintelligenceagency.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomiseQuiltActivity extends AppCompatActivity {

    @BindView(R.id.spinner_states)
    Spinner statesSpinner;
    @BindView(R.id.btnNextCustomise)
    Button btnNextCustomise;
    @BindView(R.id.text_input_firstName)
    TextInputLayout textInputLayoutFirstName;
    @BindView(R.id.text_input_lastName)
    TextInputLayout textInputLayoutLastName;
    @BindView(R.id.unit_number)
    TextInputLayout unitNumber;
    @BindView(R.id.text_input_streetNumber)
    TextInputLayout textInputLayoutStreetNumber;
    @BindView(R.id.text_input_streetName)
    TextInputLayout textInputLayoutStreetName;
    @BindView(R.id.text_input_suburb)
    TextInputLayout textInputLayoutSuburb;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customise);
        ButterKnife.bind(this);
        spinnerInflate();

        btnNextCustomise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               confirmInput();

            }
        });


    }

    private void spinnerInflate() {
        ArrayAdapter<String> statesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.states));
        statesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statesSpinner.setAdapter(statesAdapter);
    }


    private boolean validateTextInput(TextInputLayout textInputLayout){
        String input =textInputLayout.getEditText().getText().toString().trim();
        if(input.isEmpty()){
            textInputLayout.setError("Field can't be empty");
            return false;
        }
        if(input.length()>15){
            textInputLayout.setError("Input too long");
        }
       textInputLayout.setError(null);
       textInputLayout.setErrorEnabled(false);
        return true;
    }

    public void confirmInput (){
        if(!validateTextInput(textInputLayoutFirstName)|!validateTextInput(textInputLayoutLastName)
                |!validateTextInput(textInputLayoutStreetName)|
                !validateTextInput(textInputLayoutStreetNumber)|
                !validateTextInput(textInputLayoutSuburb)){
              return;
        }
        Intent MainIntent = new Intent(CustomiseQuiltActivity.this,MainActivity.class);
        CustomiseQuiltActivity.this.startActivity(MainIntent);
    }

}
