package au.com.hermitagewool.views;


import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import com.com.hwool.hermitageintelligenceagency.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import au.com.hermitagewool.models.QrCode;
import au.com.hermitagewool.models.Quilt;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseReference = mFirebaseDatabase.getReference().child("metr");
        mDatabaseReference.push().setValue("news");
        mDatabaseReference = mFirebaseDatabase.getReference().child("Quilts");
        Quilt quilt = new Quilt("Fantastis", "Purple", "12", "32", "This is a fantastic quilt");
        mDatabaseReference.push().setValue(quilt);
        mDatabaseReference = mFirebaseDatabase.getReference().child("QrCode");

        for(int i = 0;i < 200;i++){
            mDatabaseReference.push().setValue(new QrCode());
        }


        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.overflow_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_scanQR:

                Intent intentScanQR = new Intent(MainActivity.this, ScanActivity.class);
                startActivity(intentScanQR);
                return true;
            case R.id.nav_connect_quilt:
                Toast.makeText(getApplicationContext(), "Smart Quilt not available", Toast.LENGTH_LONG).show();
                //Log.i("test_toast", "test_toast");
                return true;
            case R.id.nav_customise:
                Intent intentCustomise = new Intent(MainActivity.this, CustomiseQuiltActivity.class);
                startActivity(intentCustomise);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}

