package au.com.hermitagewool.repository;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import au.com.hermitagewool.models.Sensor;

/**
 * @author Ye Hung
 */
public class SensorRepositoryImpl implements SensorRepository {
    private DatabaseReference sensorReference = FirebaseHelper.getSensorDataReference();

    public ArrayList<Sensor> findData(String date) {
        final ArrayList<Sensor> dataSet = new ArrayList<>();
        sensorReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    Sensor data = ds.getValue(Sensor.class);
                    dataSet.add(data);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return (dataSet.isEmpty()) ? null : dataSet;
    }


}
