package hwool.com.au.hermitageintelligenceagency.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import hwool.com.au.hermitageintelligenceagency.R;

public class OptionActivity extends AppCompatActivity {

    private Button btnScanQR2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        //requestPermissions();


        btnScanQR2 = findViewById(R.id.btnScanQR2);

        btnScanQR2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ScanActivity.class);
                startActivity(intent);
            }
        });

    }
}
