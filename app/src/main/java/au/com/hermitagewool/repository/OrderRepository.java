package au.com.hermitagewool.repository;

import au.com.hermitagewool.models.Order;

public interface OrderRepository {
    void saveOrder(Order order);
}
