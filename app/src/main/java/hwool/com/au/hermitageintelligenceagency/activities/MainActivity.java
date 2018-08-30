package hwool.com.au.hermitageintelligenceagency.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import hwool.com.au.hermitageintelligenceagency.R;

public class MainActivity extends AppCompatActivity {
private Button btnScanQr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseReference = mFirebaseDatabase.getReference();
        mDatabaseReference.setValue("Hello world");
        setTitle(R.string.main_title);
        btnScanQr = findViewById(R.id.btScanQr);
        btnScanQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
    }

}

