package au.com.hermitagewool.repository;

import java.util.ArrayList;

import au.com.hermitagewool.models.Sensor;

public interface SensorRepository {
    ArrayList<Sensor> findData(String date);
}
