package au.com.hermitagewool.repository;

import au.com.hermitagewool.models.Order;

/**
 * Order repository interface
 */
public interface OrderRepository {
    void saveOrder(Order order);
}
