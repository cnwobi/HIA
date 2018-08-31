package hwool.com.au.hermitageintelligenceagency.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import hwool.com.au.hermitageintelligenceagency.R;
import hwool.com.au.hermitageintelligenceagency.models.News;

public class InsertNews extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    EditText etTitle;
    EditText etBody;
    Button btSaveNews;
    EditText etDateCreated;
    EditText etAuthor;
    DatePickerDialog dpCreationDate;

    {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_news);
        setTitle(R.string.add_news);
        etTitle = findViewById(R.id.etTitle);
        etBody = findViewById(R.id.etBody);
        etAuthor = findViewById(R.id.etAuthor);
        etDateCreated = findViewById(R.id.etDateCreated);


        btSaveNews= findViewById(R.id.btSave);

        btSaveNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNews();
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });


    }


    public void saveNews() {

        String title = etTitle.getText().toString();
        String body = etBody.getText().toString();
        String author = etAuthor.getText().toString();
        String dateCreated = etDateCreated.getText().toString();
        News news = new News(author, title, body, dateCreated);
        mDatabaseReference.child("news").push().setValue(news);

    }

    public void clean() {
        etTitle.setText(" ");
        etBody.setText(" ");
        etAuthor.setText(" ");
        etDateCreated.setText(" ");
    }

}
