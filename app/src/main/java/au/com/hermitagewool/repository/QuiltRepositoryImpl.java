package au.com.hermitagewool.repository;

import com.google.firebase.database.DatabaseReference;

import au.com.hermitagewool.models.Quilt;

public class QuiltRepositoryImpl implements QuiltRepository {

    @Override
    public void saveQuilt(Quilt quilt) {
        DatabaseReference databaseReference = FirebaseHelper.getQuiltReference();
        databaseReference.push().setValue(quilt);
    }
}
