package au.com.hermitagewool.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Quilt implements Serializable {
 private String id;
    private String fabric;
    private String filling;
    private String GSM;
    private String size;



    public Quilt(String fabric, String filling, String width, String height, String GSM) {
        this.fabric = fabric;
        this.filling = filling;
        this.GSM = GSM;
    }
}
