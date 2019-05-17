package au.com.hermitagewool.repository;
import com.google.firebase.database.DatabaseReference;
import au.com.hermitagewool.models.News;

public class NewsRepositoryImpl implements NewsRepositiory {
    DatabaseReference databaseReference = FirebaseHelper.getNewsLetterReference();
    @Override
    public void saveNews(News news) {
        databaseReference.push().setValue(news);
    }
}
