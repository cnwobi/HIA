package au.com.hermitagewool.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.com.hwool.hermitageintelligenceagency.R;

public class CustomiseQuiltActivity extends AppCompatActivity {

    private String tvQRcodeResult;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customise);
    }

    public void setTvQRcodeResult(String tvQRcodeResult) {
        this.tvQRcodeResult = tvQRcodeResult;
    }
}
