package au.com.hermitagewool.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.com.hwool.hermitageintelligenceagency.R;

import au.com.hermitagewool.models.News;

public class NewsDetailActivity extends AppCompatActivity {
    Toolbar mToolbar;
    TextView tvDetailsTitle;
    TextView tvDetailsBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        mToolbar = findViewById(R.id.toolbar_news);
        tvDetailsBody = findViewById(R.id.tvDetailsBody);
        tvDetailsTitle = findViewById(R.id.tvDetailsTitle);
        Intent intent = getIntent();
        News news = (News) intent.getSerializableExtra("news");
        tvDetailsTitle.setText(news.getmTitle());
        tvDetailsBody.setText(news.getmBody());


    }
}
