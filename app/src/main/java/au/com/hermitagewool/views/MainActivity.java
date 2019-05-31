package au.com.hermitagewool.views;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.com.hwool.hermitageintelligenceagency.R;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * This is the main activity of the app
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        // Tabs
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        FirebaseMessaging.getInstance().subscribeToTopic("NewsLetter");
        Log.d(TAG, "onCreate: Inside Main activity");



    }


    /*
        Create and shows the overflow menu on top right.
     */
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
                Intent quiltList = new Intent(MainActivity.this, QuiltList.class);
                startActivity(quiltList);
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }

}

