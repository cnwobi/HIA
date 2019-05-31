package au.com.hermitagewool.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import com.com.hwool.hermitageintelligenceagency.R;

import au.com.hermitagewool.models.QrCode;
import au.com.hermitagewool.repository.FirebaseHelper;
import au.com.hermitagewool.repository.QrCodeRepository;
import au.com.hermitagewool.repository.QrCodeRepositoryImpl;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import pub.devrel.easypermissions.EasyPermissions;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private int MY_PERMISSIONS_REQUEST_CAMERA = 1;

    private DatabaseReference qrCodeReference = FirebaseHelper.getQrCodeReference();
    private QrCodeRepository qrCodeRepository;
    private String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        requestPermission();
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        qrCodeRepository = new QrCodeRepositoryImpl();


    }


    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }


    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }



    @Override
    public void handleResult(final Result rawResult) {

        // Do something with the result here
        // Log.v("tag", rawResult.getText()); // Prints scan results
        // Log.v("tag", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        //MainActivity.tvQrResult.setText(rawResult.getText());

        /*
        Currently just send the result and print it to AddressDetailsActivity
        In the future, check with the server if the QRCode is valid.
         */
        key =  rawResult.getText();

        qrCodeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                QrCode qrCode = null;
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    if (ds.getKey().equalsIgnoreCase(key)){
                        qrCode = ds.getValue(QrCode.class);
                        qrCode.setId(key);
                        break;
                    }
                }
                if(qrCode != null && !qrCode.isUsed() ) {
                    Intent intent = new Intent(getApplicationContext(), AddressDetailsActivity.class);
                    intent.putExtra("qrCode", qrCode);
                    startActivity(intent);
                    key= null;
                }
                else if(qrCode!=null && qrCode.isUsed()&& key==null){
                    Toast.makeText(getApplicationContext(), "QR-Code has been used! Please contact issuing store", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);

                }


                else if(qrCode==null && key!=null) {

                    Toast.makeText(getApplicationContext(), "Invalid QR-Code", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    //onBackPressed();

    // If you would like to resume scanning, call this method below:
    //mScannerView.resumeCameraPreview(this);


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    public void requestPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }
}
