package au.com.hermitagewool.views;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.com.hwool.hermitageintelligenceagency.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import au.com.hermitagewool.models.Sensor;
import au.com.hermitagewool.repository.FirebaseHelper;
import au.com.hermitagewool.repository.SensorRepository;
import au.com.hermitagewool.repository.SensorRepositoryImpl;

public class GraphFragment extends Fragment {

    private static final String TAG = "Tab1Fragment";
    private LineChart lineTemp;
    private String currentDate;
    private String selectedDate;
    private TextView dateView;
    private ArrayList<Entry> line1 = new ArrayList<>();
    private ArrayList<Entry> line2 = new ArrayList<>();
    private ArrayList<Date> dateArrayList = new ArrayList<>();
    private ArrayList<String> timeArraylist = new ArrayList<>();
    public static final int REQUEST_CODE = 11; // Used to identify the result
    private DatabaseReference sensorReference = FirebaseHelper.getSensorDataReference();
    private SensorRepository sensorRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_graph, container, false);
        sensorRepository = new SensorRepositoryImpl();

        // get fragment manager so we can launch from fragment
        final FragmentManager fm = (getActivity()).getSupportFragmentManager();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        currentDate = dateFormat.format(new Date());
        dateView = rootView.findViewById(R.id.timeLabel);
        dateView.setText(currentDate);

        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create the datePickerFragment
                AppCompatDialogFragment newFragment = new DatePickerFragment();
                // set the targetFragment to receive the results, specifying the request code
                newFragment.setTargetFragment(GraphFragment.this, REQUEST_CODE);
                // show the datePicker
                newFragment.show(fm, "datePicker");
            }
        });

        // line chart components
        lineTemp = rootView.findViewById(R.id.line_chart);


        //read defualt local data
        readJson();
        //addData(selectedDate);
        //setData(selectedDate, rootView);

        // put data into line chart
        setGraph();
        lineTemp.setVisibleXRangeMaximum(22f);

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check for the results
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // get date from string
            selectedDate = data.getStringExtra("selectedDate");
            // set the value of the editText
            dateView.setText(data.getStringExtra("displayDate"));
        }
    }

    // make x axis labels
    private class XaisFormatter implements IAxisValueFormatter {
        private ArrayList<String> labelList;
        //private int maxIndex;

        public void getAxisLabel(ArrayList<String> labels){
            this.labelList = (ArrayList<String>) labels.clone();
            System.out.println("cloned size"+ labelList.size());
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            //this.maxIndex = labelList.size()-1;
            return labelList.get(Math.round(value));
        }
    }

    private void addData(String selctedDate) {
        ArrayList<Sensor> dataSet = sensorRepository.findData(selctedDate);
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        if (dataSet != null) {
            for (int i = 0; i < dataSet.size(); i++) {
                Sensor dataPoint = dataSet.get(i);

                String date_str = dataPoint.getLocal_date_time_full();
                try {
                    Date date = dateTimeFormat.parse(date_str);
                    dateArrayList.add(date);
                    timeArraylist.add(timeFormat.format(date));

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                float index = dataPoint.getSort_order();
                float temp = dataPoint.getAir_temp();
                line1.add(new Entry(index, temp));
                float quilt_t = dataPoint.getQuilt_t();
                line2.add(new Entry(index, quilt_t));

            }

        } else {

        }
    }

    private void setData(final String selectedDate, final View rootView){
        //final List<Sensor> dataSet = new ArrayList<>();

        final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        sensorReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    //Sensor data = ds.getValue(Sensor.class);
                    //dataSet.add(data);
                    //if (ds.child("local_date_time_full").getValue(String.class).equals(selectedDate)) {
                    String date_str = ds.child("local_date_time_full").getValue(String.class);
                    float index = ds.child("sort_order").getValue(Integer.class);
                    float temp = ds.child("air_temp").getValue(float.class);
                    float quilt_t = ds.child("quilt_t").getValue(float.class);

                    try {
                        Date date = dateTimeFormat.parse(date_str);
                        dateArrayList.add(date);
                        timeArraylist.add(timeFormat.format(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    line1.add(new Entry(index, temp));
                    line2.add(new Entry(index, quilt_t));

                        //break;

                }
                setGraph();

                //lineTemp.setVisibleXRangeMaximum(8f);
                //Toast.makeText(getActivity(), "check" + timeArraylist.get(49), Toast.LENGTH_LONG).show();
                //Toast.makeText(getActivity(), "Read data finish", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Read data failed", Toast.LENGTH_LONG).show();
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }

    private void readJson(){
        // read local json file
        String json;
        try {
            InputStream inputStream = getContext().getAssets().open("sensor_local.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer,"UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            int maxIndex = jsonArray.length();

            // setup line values
            for(int i = 0; i<maxIndex; i++){
                JSONObject obj = jsonArray.getJSONObject(maxIndex-1-i);

                String date_str = obj.getString("local_date_time_full");
                try {
                    Date date = dateTimeFormat.parse(date_str);
                    dateArrayList.add(date);
                    timeArraylist.add(timeFormat.format(date));

                }catch (ParseException e){
                    e.printStackTrace();
                }

                float index = Float.parseFloat(obj.getString("sort_order"));
                float temp = Float.parseFloat(obj.getString("air_temp"));
                line1.add(new Entry(index,temp));
                float tempQuilt = Float.parseFloat(obj.getString("quilt_t"));
                line2.add(new Entry(index,tempQuilt));


            }
            Toast.makeText(getActivity(), "check data: " + line2.get(49).toString(), Toast.LENGTH_LONG).show();

        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void setGraph(){

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();
        LineDataSet lineDataSet1 = new LineDataSet(line1,"Air Temperature");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.BLUE);
        LineDataSet lineDataSet2 = new LineDataSet(line2,"Temperature inside the Quilt");
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setColor(Color.RED);

        XAxis xAxis = lineTemp.getXAxis();
        XaisFormatter myFormatter = new XaisFormatter();
        myFormatter.getAxisLabel(timeArraylist);
        xAxis.setValueFormatter(myFormatter);
        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);
        lineTemp.setData(new LineData(lineDataSets));
        lineTemp.invalidate();
    }

}
