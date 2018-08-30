package hwool.com.au.hermitageintelligenceagency.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import hwool.com.au.hermitageintelligenceagency.quilts.Quilt;

public class FireBaseUtil {
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;

 /*   public static List<Quilt> mDeals;*/

    private static FireBaseUtil instance= null;
    public static FireBaseUtil getInstance(){
        if (instance==null){
            instance = new FireBaseUtil();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
        }
      /*  mDeals = new ArrayList<>();*/
        return instance;
    }
public static void openFirebaseReference(String ref){
    mDatabaseReference = mFirebaseDatabase.getReference().child(ref);
}
    private FireBaseUtil() {
    }

}
