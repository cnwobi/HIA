package hwool.com.au.hermitageintelligenceagency.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatEditText;
import android.widget.TextView;

import hwool.com.au.hermitageintelligenceagency.R;

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
