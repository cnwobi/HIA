package au.com.hermitagewool.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.com.hwool.hermitageintelligenceagency.R;

import java.util.Objects;

import au.com.hermitagewool.models.News;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This activity displays the details of a news article
 */
public class NewsDetailActivity extends AppCompatActivity {
    @BindView(R.id.tvDetailsTitle) TextView tvDetailsTitle;
    @BindView(R.id.tvDetailsBody)  TextView tvDetailsBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        // Define the toolbar, the back arrow
        Toolbar mToolbar = findViewById(R.id.toolbar_news);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Define the title and the body for the news, take the data from the news fragment.
        Intent intent = getIntent();
        News news = (News) intent.getSerializableExtra("news");

        tvDetailsTitle.setText(news.getmTitle());
        tvDetailsBody.setText(news.getmBody());
    }
}
