package au.com.hermitagewool.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Quilt implements Parcelable {
    private String id;
    private String fabric;
    private String filling;
    private String GSM;
    private String size;

    protected Quilt(Parcel in) {
        size    = in.readString();
        fabric  = in.readString();
        filling = in.readString();
        GSM     = in.readString();
    }

    /*
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
    */

    public static final Creator<Quilt> CREATOR = new Creator<Quilt>() {
        @Override
        public  Quilt createFromParcel(Parcel in) {
            return new Quilt(in);
        }

        @Override
        public Quilt[] newArray(int size) {
            return new Quilt[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(size);
        dest.writeString(fabric);
        dest.writeString(filling);
        dest.writeString(GSM);
    }

}
