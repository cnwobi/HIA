package au.com.hermitagewool.repository;

import com.google.firebase.database.DatabaseReference;

import au.com.hermitagewool.models.Order;

/**
 * Concrete OrderRepo implementation
 */
public class OrderRepositoryImpl implements OrderRepository {
    private DatabaseReference databaseReference = FirebaseHelper.getOrderReference();


    @Override
    public void saveOrder(Order order) {
        databaseReference.push().setValue(order);

    }
}
