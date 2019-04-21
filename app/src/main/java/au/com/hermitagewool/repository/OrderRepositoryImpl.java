package au.com.hermitagewool.repository;

import com.google.firebase.database.DatabaseReference;

import au.com.hermitagewool.models.Order;


public class OrderRepositoryImpl implements OrderRepository {
    DatabaseReference databaseReference = FirebaseHelper.getOrderReference();


    @Override
    public void saveOrder(Order order) {
        databaseReference.push().setValue(order);

    }
}
