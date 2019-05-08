package au.com.hermitagewool.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

public class ConfirmOrderActivity extends AppCompatActivity {

    @BindView(R.id.textView_confirm)
    TextView textView;
    @BindView(R.id.button_confirm)
    Button buttonConfirm;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        ButterKnife.bind(this);

        final OrderRepository orderRepository = new OrderRepositoryImpl();

        // retrieve object from the previous activity
        Bundle bundle = getIntent().getExtras();
        final Order order = bundle.getParcelable("order");
        //Quilt quilt = bundle.getParcelable("quilt");

        textView.append(getString(R.string.first_name)    + ": " + order.getFirstName() + "\n\n");
        textView.append(getString(R.string.last_name)     + ": " + order.getLastName() + "\n\n");
        textView.append(getString(R.string.unit_number)   + ": " + order.getUnitNumber() + "\n\n");
        textView.append(getString(R.string.street_number) + ": " + order.getStreetNumber() + "\n\n");
        textView.append(getString(R.string.street_name)   + ": " + order.getStreetName() + "\n\n");
        textView.append(getString(R.string.suburb)        + ": " + order.getSuburbs() + "\n\n");
        textView.append(getString(R.string.postcode)      + ": " + order.getPostcode() + "\n\n");
        textView.append(getString(R.string.state)         + ": " + order.getState() + "\n\n");



        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                orderRepository.saveOrder(order);
                //quiltRepository.saveQuilt(quilt);


                Toast.makeText(ConfirmOrderActivity.this, "Thank you for choosing Hermitage Wool", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ConfirmOrderActivity.this, MainActivity.class);
                ConfirmOrderActivity.this.startActivity(intent);

                return;
            }
        });

    }
}
