package au.com.hermitagewool.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.com.hwool.hermitageintelligenceagency.R;

import au.com.hermitagewool.models.QrCode;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressDetailsActivity extends AppCompatActivity {

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
    @BindView(R.id.text_input_postcode)
    TextInputLayout textInputLayoutPostcode;
    String selectedOption;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_details);
        ButterKnife.bind(this);
        spinnerInflate();




        btnNextCustomise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               confirmInput();}
        });


    }

    private void spinnerInflate() {
        ArrayAdapter<String> statesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.states));
        statesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statesSpinner.setAdapter(statesAdapter);
        statesSpinner.setPrompt("Select your state");
        statesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOption =  (String) statesSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private boolean isValidTextInput(TextInputLayout textInputLayout){
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

    public boolean isValidSelection(){
       selectedOption =  (String) statesSpinner.getSelectedItem();

       if (selectedOption.equalsIgnoreCase("Select State")){
       TextView errorText = (TextView)statesSpinner.getSelectedView();
       errorText.setError("Please select a state");
       errorText.setTextColor(Color.RED);
       return false;
       }
       return true;
    }

    public String returnTextInput(TextInputLayout textInputLayout){
        return textInputLayout.getEditText().getText().toString().trim();
    }

    public void confirmInput (){
        if(!isValidTextInput(textInputLayoutFirstName)|!isValidTextInput(textInputLayoutLastName)
                |!isValidTextInput(textInputLayoutStreetName)|
                !isValidTextInput(textInputLayoutStreetNumber)|
                !isValidTextInput(textInputLayoutSuburb) |isValidTextInput(textInputLayoutPostcode)| !isValidSelection()){
              return;
        }
        Intent mainIntent = new Intent(AddressDetailsActivity.this,CustomiseQuiltActivity.class);
       mainIntent.putExtra("firstName",returnTextInput(textInputLayoutFirstName));
       mainIntent.putExtra("lastName",returnTextInput(textInputLayoutLastName));
       mainIntent.putExtra("unitNumber",returnTextInput(unitNumber));
       mainIntent.putExtra("streetNumber",returnTextInput(textInputLayoutStreetNumber));
       mainIntent.putExtra("streetName",returnTextInput(textInputLayoutStreetName));
       mainIntent.putExtra("suburb",returnTextInput(textInputLayoutSuburb));
       mainIntent.putExtra("postcode",returnTextInput(textInputLayoutPostcode));
       mainIntent.putExtra("state",selectedOption);
        QrCode qrCode = getIntent().getParcelableExtra("qr code");
        mainIntent.putExtra("qr code", qrCode);

        AddressDetailsActivity.this.startActivity(mainIntent);
    }

}
