package au.com.hermitagewool.repository;


import au.com.hermitagewool.models.QrCode;

public interface QrCodeRepository {
    QrCode findQrCode(String key);
    QrCode updateQrCode(QrCode qrCode);
    void saveQrCode(QrCode qrCode);

}
