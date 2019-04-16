package au.com.hermitagewool.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Order implements Serializable {
    private static final String TAG = "Order";
private String firstName;
private String lastName;
private String unitNumber;
private String streetNumber;
private String streetName;
private String state;
private String suburbs;
private Quilt quilt;

    public Order(String firstName, String lastName, String unitNumber, String streetNumber, String streetName, String state, String suburbs, Quilt quilt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.unitNumber = unitNumber;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.state = state;
        this.suburbs = suburbs;
        this.quilt = quilt;
    }
}
