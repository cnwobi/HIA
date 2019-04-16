package au.com.hermitagewool.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Order implements Serializable {
private String firstName;
private String lastName;
private String unitNumber;
private String streetNumber;
private String streetAddress;
private String state;
private String suburbs;





}
