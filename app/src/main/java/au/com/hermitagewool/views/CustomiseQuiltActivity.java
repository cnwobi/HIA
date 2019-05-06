package au.com.hermitagewool.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.com.hwool.hermitageintelligenceagency.R;

import au.com.hermitagewool.models.Order;
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

        spinnerInflate(spinnerSize, R.array.size);
        spinnerInflate(spinnerFabric, R.array.fabric);
        spinnerInflate(spinnerFilling, R.array.filling);
        spinnerInflate(spinnerGSM, R.array.gsm);

        final Order order = new Order();


        order.setFirstName(returnStringFromIntent("firstName"));
        order.setLastName(returnStringFromIntent("lastName"));
        order.setUnitNumber(returnStringFromIntent("unitNumber"));
        order.setStreetNumber(returnStringFromIntent("streetNumber"));
        order.setStreetName(returnStringFromIntent("streetName"));
        order.setSuburbs(returnStringFromIntent("suburb"));
        order.setState(returnStringFromIntent("state"));
        order.setPostcode(returnStringFromIntent("postcode"));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidSelection(spinnerSize) && isValidSelection(spinnerFabric)
                        && isValidSelection(spinnerFilling) && isValidSelection(spinnerGSM)) {
                    quilt = new Quilt();
                    quilt.setSize(spinnerSize.getSelectedItem().toString());
                    quilt.setFabric(spinnerFabric.getSelectedItem().toString());
                    quilt.setFilling(spinnerFilling.getSelectedItem().toString());
                    quilt.setGSM(spinnerGSM.getSelectedItem().toString());

                    order.setQuilt(quilt);
                    //orderRepository.saveOrder(order);
                    //quiltRepository.saveQuilt(quilt);

                    //Toast.makeText(CustomiseQuiltActivity.this, "Thank you for choosing Hermitage Wool", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CustomiseQuiltActivity.this, ConfirmOrderActivity.class);
                    // push the object to the next activity ?
                    intent.putExtra("order", order);
                    //intent.putExtra("quilt", quilt);
                    CustomiseQuiltActivity.this.startActivity(intent);

                    return;
                }
                Toast.makeText(CustomiseQuiltActivity.this, "Error", Toast.LENGTH_LONG).show();
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
}
