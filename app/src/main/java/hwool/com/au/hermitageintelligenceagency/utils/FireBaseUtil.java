package hwool.com.au.hermitageintelligenceagency.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBaseUtil {
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;



    private static FireBaseUtil instance= null;
    public static FireBaseUtil getInstance(){
        if (instance==null){
            instance = new FireBaseUtil();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
        }

        return instance;
    }
public static void openFirebaseReference(String ref){
    mDatabaseReference = mFirebaseDatabase.getReference().child(ref);
}
    private FireBaseUtil() {
    }

}
