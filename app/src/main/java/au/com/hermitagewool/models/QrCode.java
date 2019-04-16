package au.com.hermitagewool.models;




public class QrCode {
    private static final String TAG = "QrCode";
    private String id;
    private boolean used;

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
}
