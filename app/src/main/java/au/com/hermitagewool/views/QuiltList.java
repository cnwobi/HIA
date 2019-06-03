package au.com.hermitagewool.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.com.hwool.hermitageintelligenceagency.R;

import java.util.Objects;


public class QuiltList extends AppCompatActivity {

    /**
     * Create the view for the activity. The button send the user to the phone's bluetooth
     * parameter.
     * @param savedInstanceState
     */

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

        // Send the user to the smart quilt tab
        TextView quilt1 = findViewById(R.id.quilt_list_quilt1);
        quilt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuiltList.this, MainActivity.class);
                intent.putExtra("tab_index", 1);// One is your argument
                startActivity(intent);
            }
        });


        // Send the user to the phone's Bluetooth parameter
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
