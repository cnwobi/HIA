package au.com.hermitagewool.views;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.com.hwool.hermitageintelligenceagency.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_graph, container, false);

        // get fragment manager so we can launch from fragment
        final FragmentManager fm = (getActivity()).getSupportFragmentManager();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
        XAxis xAxis = lineTemp.getXAxis();

        readJson(selectedDate);

        // put data into line chart
        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();
        LineDataSet lineDataSet1 = new LineDataSet(line1,"Air Temperature");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.BLUE);
        LineDataSet lineDataSet2 = new LineDataSet(line2,"Apparent Temperature");
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setColor(Color.RED);

        XaisFormatter myFormatter = new XaisFormatter();
        myFormatter.getAxisLabel(timeArraylist);
        xAxis.setValueFormatter(myFormatter);
        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);
        lineTemp.setData(new LineData(lineDataSets));

        lineTemp.setVisibleXRangeMaximum(7f);

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

        public void getAxisLabel(ArrayList<String> labels){
            this.labelList = (ArrayList<String>) labels.clone();
            System.out.println("cloned size"+ labelList.size());
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return labelList.get(Math.round(value));
        }
    }


    private void readJson(String selectedDate){
        // read local json file
        String json;
        try {
            InputStream inputStream = getContext().getAssets().open("sensor.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer,"UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

            // setup line values
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);

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
                float temp_apparent = Float.parseFloat(obj.getString("quilt_t"));
                line2.add(new Entry(index,temp_apparent));

            }

        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

}
