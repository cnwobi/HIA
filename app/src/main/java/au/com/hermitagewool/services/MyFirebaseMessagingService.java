package au.com.hermitagewool.services;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMessagingServ";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if(remoteMessage.getData().size() > 0) {
            Log.d(TAG, "onMessageReceived: "+remoteMessage.getData());
            JSONObject data =  new JSONObject(remoteMessage.getData());

            try {
                String jsonMessage= data.getString("extra_information");
                Log.d(TAG, "onMessageReceived: \n" + "Extra Information "+ jsonMessage);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(remoteMessage.getNotification() != null) {
            String title       = remoteMessage.getNotification().getTitle();
            String message     = remoteMessage.getNotification().getBody();
            String clickAction = remoteMessage.getNotification().getClickAction();


            Log.d(TAG, "Message Title: " + title);
            Log.d(TAG, "Message Notification Body:"+ message);
            Log.d(TAG, "Message Notification click_action: "+ clickAction);
        }
    }
}
