package au.com.hermitagewool.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
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
import au.com.hermitagewool.repository.QrCodeRepository;
import au.com.hermitagewool.repository.QrCodeRepositoryImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmOrderActivity extends AppCompatActivity {

    @BindView(R.id.textView_confirm) TextView textView;
    @BindView(R.id.button_confirm)   Button   buttonConfirm;
    final OrderRepository orderRepository = new OrderRepositoryImpl();
    final QuiltRepository quiltRepository = new QuiltRepositoryImpl();
    final QrCodeRepository qrCodeRepository =  new QrCodeRepositoryImpl();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        ButterKnife.bind(this);

        // add the back arrow to the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_confirm);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        // retrieve object from the previous activity
        Quilt quilt = getIntent().getParcelableExtra("quilt");
        final Order order = getIntent().getParcelableExtra("order");
        QrCode qrCode = getIntent().getParcelableExtra("qr code");
        order.setQuilt(quilt);

        SpannableStringBuilder ssb = new SpannableStringBuilder();

        textView.append("Customer Detail:\n\n");
        textView.append(getString(R.string.first_name)    + ": " + order.getFirstName() + "\n");
        textView.append(getString(R.string.last_name)     + ": " + order.getLastName() + "\n");
        textView.append(getString(R.string.unit_number)   + ": " + order.getUnitNumber() + "\n");
        textView.append(getString(R.string.street_number) + ": " + order.getStreetNumber() + "\n");
        textView.append(getString(R.string.street_name)   + ": " + order.getStreetName() + "\n");
        textView.append(getString(R.string.suburb)        + ": " + order.getSuburbs() + "\n");
        textView.append(getString(R.string.postcode)      + ": " + order.getPostcode() + "\n");
        textView.append(getString(R.string.state)         + ": " + order.getState() + "\n\n");

        textView.append("Selected Quilt:\n\n");
        textView.append(getString(R.string.size_c)      + ": " + order.getQuilt().getSize() + "\n");
        textView.append(getString(R.string.fabric_c)    + ": " + order.getQuilt().getFabric() + "\n");
        textView.append(getString(R.string.filling_c)   + ": " + order.getQuilt().getFilling() + "\n");
        textView.append(getString(R.string.gsm_c)       + ": " + order.getQuilt().getGSM() + "\n");


        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                orderRepository.saveOrder(order);
                quiltRepository.saveQuilt(order.getQuilt());


                Intent intent = new Intent(ConfirmOrderActivity.this, MainActivity.class);
                ConfirmOrderActivity.this.startActivity(intent);
                Toast.makeText(ConfirmOrderActivity.this, "Thank you for choosing Hermitage Wool", Toast.LENGTH_LONG).show();
            }
        });

    }

}
