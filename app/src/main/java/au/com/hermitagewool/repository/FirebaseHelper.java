package au.com.hermitagewool.repository;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Firebase Helper that simplifies getting references to firebase paths
 */
public class FirebaseHelper {

    private final static String QR_CODE_PATH = "QrCode";
    private final static String ORDER_PATH = "orders";
    private final static String QUILT_PATH ="quilt";
    private final static String NEWS_PATH ="news";
    private final static String SENSOR_DATA ="sensor_data";

    private FirebaseHelper() {
    }


    /**
     *
     * @return Qr code reference
     */
    public static DatabaseReference getQrCodeReference(){
        return FirebaseDatabase.getInstance().getReference().child(QR_CODE_PATH);
    }

    /**
     * Returns order firebase path reference
     * @return
     */
    public static DatabaseReference getOrderReference(){
        return FirebaseDatabase.getInstance().getReference().child(ORDER_PATH);
    }

    /**
     * Returns quilt firebase path reference
     * @return
     */
    public static DatabaseReference getQuiltReference(){
        return FirebaseDatabase.getInstance().getReference().child(QUILT_PATH);
    }

    /**
     * Returns news reference
     * @return
     */
    public static DatabaseReference getNewsLetterReference(){
        return FirebaseDatabase.getInstance().getReference().child(NEWS_PATH);
    }

    /**
     * Returns sensor reference
     * @return
     */
    public static DatabaseReference getSensorDataReference(){
        return FirebaseDatabase.getInstance().getReference().child(SENSOR_DATA);
    }
}
