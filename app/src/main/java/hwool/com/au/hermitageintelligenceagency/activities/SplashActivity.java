package hwool.com.au.hermitageintelligenceagency.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import hwool.com.au.hermitageintelligenceagency.R;
import hwool.com.au.hermitageintelligenceagency.activities.MainActivity;


public class SplashActivity extends AppCompatActivity {
private final static int SPLASH_OUT = 2300;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
         startActivity(new Intent(this,MainActivity.class));
    }
}
