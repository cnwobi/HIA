package au.com.hermitagewool.views;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.com.hwool.hermitageintelligenceagency.R;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Date;

import au.com.hermitagewool.models.News;
import au.com.hermitagewool.repository.NewsRepositiory;
import au.com.hermitagewool.repository.NewsRepositoryImpl;


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

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

       /* News news1 = new News();
        news1.setmTitle("Bill Shorten Wins Election");
        news1.setmBody("Barr specifically was referring to the early January 2017 briefing intelligence officials gave then-president-elect Trump at Trump Tower, and “the leaking of information subsequent to that meeting.”\n" +
                "\n" +
                "At that meeting, Trump was briefed by intelligence and law enforcement officials on Russian election meddling -- and was also informed by former FBI Director James Comey about the now-infamous anti-Trump dossier which included salacious allegations against him. Details later leaked to the press.");
        news1.setmAuthor("Juliet Muonanu ");
        news1.setmCreationDate(formattedDate);
        NewsRepositiory newsRepositiory = new NewsRepositoryImpl();

        newsRepositiory.saveNews(news1);*/
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
                Intent bluetoothIntent = new Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
                startActivity(bluetoothIntent);
                //Log.d(TAG, "bluetooth");
                return true;

            case R.id.nav_customise:
                Intent intentCustomise = new Intent(MainActivity.this, AddressDetailsActivity.class);
                startActivity(intentCustomise);
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }



}

