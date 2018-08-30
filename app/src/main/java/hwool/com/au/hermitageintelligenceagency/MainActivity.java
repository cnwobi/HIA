package hwool.com.au.hermitageintelligenceagency;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.main_title);
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
    }
}
