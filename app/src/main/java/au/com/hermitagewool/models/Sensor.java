package au.com.hermitagewool.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * Sensor class
 */
public class Sensor {
    private static final String TAG = "SensorData";
    private String name;
    private String local_date_time;
    private String local_date_time_full;
    private String aifstime_utc;
    private int sort_order;
    private double lat;
    private double lon;
    private float air_temp;
    private float quilt_t;
    private int rel_hum;
    private int hrt_bt;

    public  Sensor(){

    }

    public Sensor(String location,String localTime,String localDateFull,String timeUTC,int index,double lat,double lon,float airTemp,float quiltTemp,int humidity,int heartRate){
        this.name = location;
        this.local_date_time = localTime;
        this.local_date_time_full = localDateFull;
        this.aifstime_utc = timeUTC;
        this.sort_order = index;
        this.lat = lat;
        this.lon = lon;
        this.air_temp = airTemp;
        this.quilt_t = quiltTemp;
        this.rel_hum = humidity;
        this.hrt_bt = heartRate;
    }



    public void setLocalTime(String local_date_time){
        this.local_date_time = local_date_time;
    }
    public void setLocalTimeFull(String local_date_time_full){
        this.local_date_time_full = local_date_time_full;
    }
    public void setAirTemp(float air_temp){
        this.air_temp = air_temp;
    }
    public void setQuiltTemp(float quiltTemp){
        this.quilt_t = quiltTemp;
    }

    public void setSortOrder(int index){
        this.sort_order = index;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setTimeUTC(String timeUTC){
        this.aifstime_utc = timeUTC;
    }
    public void setHumidity(int rel_hum){
        this.rel_hum = rel_hum;
    }
    public void setHeartRate(int heartRate){
        this.hrt_bt = heartRate;
    }
    public void setLatitu(double lat){
        this.lat = lat;
    }
    public void setLontitu(double lon){
        this.lon = lon;
    }


}
