package au.com.hermitagewool.repository;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import au.com.hermitagewool.models.QrCode;


public class QrCodeRepositoryImpl implements QrCodeRepository {
    private DatabaseReference qrCodeReference = FirebaseHelper.getQrCodeReference();
    @Override
    public QrCode findQrCode(final String key) {
        final List<QrCode>  qrCodes = new ArrayList<>();
        qrCodeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    if (Objects.requireNonNull(ds.getKey()).equalsIgnoreCase(key)) {
                        QrCode qrCode = ds.getValue(QrCode.class);
                        assert qrCode != null;
                        qrCode.setId(key);
                        qrCodes.add(qrCode);
                        return;
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return (qrCodes.isEmpty()) ? null : qrCodes.get(0);
    }


    @Override
    public QrCode updateQrCode(QrCode qrCode) {
        if(qrCode !=null){
            qrCodeReference.child(qrCode.getId()).setValue(qrCode);
        }
      return qrCode;
    }


}
