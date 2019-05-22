package au.com.hermitagewool.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.com.hwool.hermitageintelligenceagency.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class QuiltList extends AppCompatActivity {

    //@BindView(R.id.toolbar_quilt_list) Toolbar toolbar;
    //@BindView(R.id.btn_connect_quilt)  Button  buttonConnectQuilt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quilt_list);

        // add the back arrow to the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_quilt_list);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button buttonConnectQuilt = findViewById(R.id.btn_connect_quilt);
        buttonConnectQuilt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent bluetoothIntent = new Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
                startActivity(bluetoothIntent);
            }
        });
    }
}
