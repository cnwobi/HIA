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

    @BindView(R.id.button_confirm)   Button   buttonConfirm;
    @BindView(R.id.tvConfirmFirstName) TextView tvConfirmFirstName;
    @BindView(R.id.tvConfirmLastName)  TextView tvConfirmLastName;
    @BindView(R.id.tvConfirmUnitNumber) TextView tvConfirmUnitNumber;
    @BindView(R.id.tvConfirmStreetNumber) TextView tvConfirmStreetNumber;
    @BindView(R.id.tvConfirmStreetName) TextView tvConfirmStreetName;
    @BindView(R.id.tvConfirmSuburb) TextView tvConfirmSuburb;
    @BindView(R.id.tvConfirmPostcode) TextView tvConfirmPostcode;
    @BindView(R.id.tvConfirmState) TextView tvConfirmState;
    @BindView(R.id.tvConfirmSize) TextView tvConfirmSize;
    @BindView(R.id.tvConfirmFabric) TextView tvConfirmFabric;
    @BindView(R.id.tvConfirmFilling) TextView tvConfirmFilling;
    @BindView(R.id.tvConfirmGsm) TextView tvConfirmGsm;
    private final OrderRepository orderRepository   = new OrderRepositoryImpl();
    private final QuiltRepository quiltRepository   = new QuiltRepositoryImpl();
    private final QrCodeRepository qrCodeRepository =  new QrCodeRepositoryImpl();


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
        final QrCode qrCode = getIntent().getParcelableExtra("qrCode");

           order.setQuilt(quilt);

           tvConfirmFirstName.setText(order.getFirstName());
           tvConfirmLastName.setText(order.getLastName());
           tvConfirmUnitNumber.setText(order.getUnitNumber());
           tvConfirmStreetNumber.setText(order.getStreetNumber());
           tvConfirmStreetName.setText(order.getStreetName());
           tvConfirmSuburb.setText(order.getSuburbs());
           tvConfirmPostcode.setText(order.getPostcode());
           tvConfirmState.setText(order.getState());


           tvConfirmSize.setText(quilt.getSize());
           tvConfirmFabric.setText(quilt.getFabric());
           tvConfirmFilling.setText(quilt.getFilling());
           tvConfirmGsm.setText(quilt.getGSM());
               SpannableStringBuilder ssb = new SpannableStringBuilder();




        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                orderRepository.saveOrder(order);
                quiltRepository.saveQuilt(order.getQuilt());
                if(qrCode !=null){
                    qrCode.setUsed(true);
                }
                qrCodeRepository.updateQrCode(qrCode);
                Intent intent = new Intent(ConfirmOrderActivity.this, MainActivity.class);
                ConfirmOrderActivity.this.startActivity(intent);
                Toast.makeText(ConfirmOrderActivity.this, getApplicationContext().getResources().getString(R.string.thank_choosing_hermitage), Toast.LENGTH_LONG).show();
            }
        });

    }

}
