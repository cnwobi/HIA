package hwool.com.au.hermitageintelligenceagency.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import hwool.com.au.hermitageintelligenceagency.R;
import hwool.com.au.hermitageintelligenceagency.models.Quilt;

public class MainActivity extends AppCompatActivity {
    private Button btnScanQr;
    private Button btnAddNews;
    private Button btnOptions;
    public  static TextView tvQrResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseReference = mFirebaseDatabase.getReference().child("metr");
        mDatabaseReference.setValue("news");
        mDatabaseReference = mFirebaseDatabase.getReference().child("Quilts");
        Quilt quilt = new Quilt("Fantastis","Purple","12","32","This is a fantastic quilt");
        mDatabaseReference.push().setValue(quilt);

        //setTitle(R.string.main_title);
        btnScanQr  = findViewById(R.id.btScanQr);
        btnAddNews = findViewById(R.id.btAddNews);
        tvQrResult = findViewById(R.id.tvQrResult);
        btnOptions = findViewById(R.id.btnOptions);

        btnScanQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ScanActivity.class);
                startActivity(intent);
            }
        });

        btnAddNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(view.getContext(),InsertNews.class);
                startActivity(intent);
            }
        });


       btnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OptionActivity.class);
                startActivity(intent);
            }
        });




    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
    }

}

