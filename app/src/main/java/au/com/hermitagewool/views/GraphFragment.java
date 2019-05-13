package au.com.hermitagewool.views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.com.hwool.hermitageintelligenceagency.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GraphFragment extends Fragment {

    private static final String TAG = "Tab1Fragment";
    private LineChart lineTemp;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_graph, container, false);

        lineTemp = rootView.findViewById(R.id.line_chart);

        ArrayList<Entry> line1 = new ArrayList<>();
        ArrayList<Entry> line2 = new ArrayList<>();

        String json;

        try {
            InputStream inputStream = getContext().getAssets().open("test.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer,"UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                float temp = Float.parseFloat(obj.getString("air_temp"));
                line1.add(new Entry(i,temp));
                float temp_apparent = Float.parseFloat(obj.getString("apparent_t"));
                line2.add(new Entry(i,temp_apparent));
            }

        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();
        LineDataSet lineDataSet1 = new LineDataSet(line1,"Air Temperature");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.BLUE);
        LineDataSet lineDataSet2 = new LineDataSet(line2,"Apparent Temperature");
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setColor(Color.RED);

        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);
        lineTemp.setData(new LineData(lineDataSets));

        lineTemp.setVisibleXRangeMaximum(7f);

        //Toast.makeText(rootView.getContext(), "Graph", Toast.LENGTH_LONG).show();

        return rootView;
    }



}
