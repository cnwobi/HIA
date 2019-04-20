package au.com.hermitagewool.models;


import android.os.Parcel;
import android.os.Parcelable;

public class QrCode implements Parcelable {
    private static final String TAG = "QrCode";
    private String id;
    private boolean used;

    public QrCode(String id, boolean used) {
        this.id = id;
        this.used = used;
    }

    protected QrCode(Parcel in) {
        id = in.readString();
        used = in.readByte() != 0;
    }

    public static final Creator<QrCode> CREATOR = new Creator<QrCode>() {
        @Override
        public QrCode createFromParcel(Parcel in) {
            return new QrCode(in);
        }

        @Override
        public QrCode[] newArray(int size) {
            return new QrCode[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "QrCode{" +
                "id='" + id + '\'' +
                ", used=" + used +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeByte((byte) (used ? 1 : 0));
    }
}
