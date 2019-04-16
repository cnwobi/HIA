package au.com.hermitagewool.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBaseUtil {
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;

    private static FireBaseUtil instance= null;


    public static FirebaseDatabase getInstance(){
        if (mFirebaseDatabase==null){

            mFirebaseDatabase = FirebaseDatabase.getInstance();
        }

        return mFirebaseDatabase;
    }


public static DatabaseReference openFirebaseReference(String ref){
    mDatabaseReference = getInstance().getReference().child(ref);
    return mDatabaseReference;
}

    private FireBaseUtil() {
    }

}
