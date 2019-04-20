package au.com.hermitagewool.repository;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {
    private DatabaseReference databaseReference;
    private final static String QR_CODE_PATH = "QrCode";
    private final static String ORDER_PATH= "orders";
    private static FirebaseHelper instance;

    private FirebaseHelper() {
    }

    public static FirebaseHelper getInstance(){
        if (instance==null){
            instance =  new FirebaseHelper();
        }
        return instance;
    }

    public static DatabaseReference getQrCodeReference(){
        return FirebaseDatabase.getInstance().getReference().child(QR_CODE_PATH);
    }
    public static DatabaseReference getOrderReference(){
        return FirebaseDatabase.getInstance().getReference().child(ORDER_PATH);
    }
}
