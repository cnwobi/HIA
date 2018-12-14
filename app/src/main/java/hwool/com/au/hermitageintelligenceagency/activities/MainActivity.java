package hwool.com.au.hermitageintelligenceagency.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import hwool.com.au.hermitageintelligenceagency.R;
import hwool.com.au.hermitageintelligenceagency.models.Quilt;


public class MainActivity extends AppCompatActivity {

    public static TextView tvQrResult;
    private MenuItem mScanQR;
    private DrawerLayout mDrawerLayout;


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

        //btnAddNews = findViewById(R.id.btAddNews);
        //tvQrResult = findViewById(R.id.tvQrResult);
        //btnOptions = findViewById(R.id.btnOptions);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }
        if (actionbar != null) {
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }



        mDrawerLayout = findViewById(R.id.drawer_layout);
        mScanQR = findViewById(R.id.activity_scan);

        NavigationView navigationView = findViewById(R.id.nav_view);

        if (navigationView != null) {

            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                            // set item as selected to persist highlight
                            menuItem.setChecked(true);

                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            // Add code here to update the UI based on the item selected
                            // For example, swap UI fragments here
                            int id = menuItem.getItemId();

                            if (id == android.R.id.home) {
                                mDrawerLayout.openDrawer(GravityCompat.START);
                                return true;
                            }

                            if (id == R.id.nav_scanQR) {
                                Log.i("test", "navscan");
                                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                                startActivity(intent);
                                return true;
                            }

                            return true;
                        }
                    });
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

