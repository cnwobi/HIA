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



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                Intent intentCustomise = new Intent(MainActivity.this, AddressDetailsActivity.class);
                startActivity(intentCustomise);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}

