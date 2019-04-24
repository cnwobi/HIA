package au.com.hermitagewool.models;

import android.os.Parcel;
import android.os.Parcelable;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Order implements Parcelable {
    private static final String TAG = "Order";
    private String firstName;
    private String lastName;
    private String unitNumber;
    private String streetNumber;
    private String streetName;
    private String state;
    private String suburbs;
    private String postcode;

    private Quilt quilt;
    private QrCode qrcode;

    protected Order(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        unitNumber = in.readString();
        streetNumber = in.readString();
        streetName = in.readString();
        state = in.readString();
        suburbs = in.readString();
        postcode = in.readString();
/*        qrcode = new QrCode(in);*/
    }

    public Order(String firstName, String lastName, String unitNumber, String streetNumber, String streetName, String state, String suburbs, Quilt quilt, String postcode, QrCode qrcode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.unitNumber = unitNumber;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.state = state;
        this.suburbs = suburbs;
        this.quilt = quilt;
        this.postcode = postcode;
        this.qrcode = qrcode;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(unitNumber);
        dest.writeString(streetNumber);
        dest.writeString(streetName);
        dest.writeString(state);
        dest.writeString(suburbs);
        dest.writeString(postcode);
    }
}
