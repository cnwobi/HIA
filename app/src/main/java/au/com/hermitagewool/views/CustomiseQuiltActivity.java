package au.com.hermitagewool.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.com.hwool.hermitageintelligenceagency.R;

import java.util.Objects;

import au.com.hermitagewool.models.Order;
import au.com.hermitagewool.models.QrCode;
import au.com.hermitagewool.models.Quilt;
import au.com.hermitagewool.repository.OrderRepository;
import au.com.hermitagewool.repository.OrderRepositoryImpl;
import au.com.hermitagewool.repository.QuiltRepository;
import au.com.hermitagewool.repository.QuiltRepositoryImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomiseQuiltActivity extends AppCompatActivity {
    @BindView(R.id.spinner_size)
    Spinner spinnerSize;
    @BindView(R.id.spinner_fabric)
    Spinner spinnerFabric;
    @BindView(R.id.spinner_filling)
    Spinner spinnerFilling;
    @BindView(R.id.spinner_gsm)
    Spinner spinnerGSM;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    Quilt quilt;
    OrderRepository orderRepository;
    QuiltRepository quiltRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customise_quilt);
        ButterKnife.bind(this);

        // add the back arrow to the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_customise);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        spinnerInflate(spinnerSize, R.array.array_size);
        spinnerInflate(spinnerFabric, R.array.array_fabric);
        spinnerInflate(spinnerFilling, R.array.array_filling);
        spinnerInflate(spinnerGSM, R.array.array_gsm);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmQuilt();

            }
        });

    }

    public boolean isValidSelection(Spinner spinner) {
        String selectedOption = (String) spinner.getSelectedItem();

        if (selectedOption.equalsIgnoreCase("Select Size")
                | selectedOption.equalsIgnoreCase("Select Fabric")
                | selectedOption.equalsIgnoreCase("Select Filling")
                | selectedOption.equalsIgnoreCase("Select GSM")) {
            TextView errorText = (TextView) spinner.getSelectedView();
            errorText.setError("Please select an option");
            errorText.setTextColor(Color.RED);
            return false;
        }
        return true;
    }


    private void spinnerInflate(Spinner spinner, int stringArray) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(stringArray));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    private String returnStringFromIntent(String key) {
        return getIntent().getStringExtra(key);
    }

    private void confirmQuilt(){
        if (!isValidSelection(spinnerSize) || !isValidSelection(spinnerFabric)
                || !isValidSelection(spinnerFilling) || !isValidSelection(spinnerGSM)) {

            Toast.makeText(CustomiseQuiltActivity.this, "Error", Toast.LENGTH_LONG).show();
            return;
        }
        quilt = new Quilt();
        quilt.setSize(spinnerSize.getSelectedItem().toString());
        quilt.setFabric(spinnerFabric.getSelectedItem().toString());
        quilt.setFilling(spinnerFilling.getSelectedItem().toString());
        quilt.setGSM(spinnerGSM.getSelectedItem().toString());

        Order order = new Order();

        QrCode qrCode = getIntent().getParcelableExtra("qr code");
        order.setQrCode(qrCode.getId());
        order.setFirstName(returnStringFromIntent("firstName"));
        order.setLastName(returnStringFromIntent("lastName"));
        order.setUnitNumber(returnStringFromIntent("unitNumber"));
        order.setStreetNumber(returnStringFromIntent("streetNumber"));
        order.setStreetName(returnStringFromIntent("streetName"));
        order.setSuburbs(returnStringFromIntent("suburb"));
        order.setState(returnStringFromIntent("state"));
        order.setPostcode(returnStringFromIntent("postcode"));
        //order.setQuilt(quilt);

        Intent mainIntent = new Intent(CustomiseQuiltActivity.this, ConfirmOrderActivity.class);
        // push the object to the next activity ?
        mainIntent.putExtra("quilt", quilt);
        mainIntent.putExtra("order", order);

        CustomiseQuiltActivity.this.startActivity(mainIntent);

    }
}
