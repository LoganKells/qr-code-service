package com.logankells.qrcodeservice.model;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.util.Map;

@Getter
@Setter
public class QrCode {

    String data;
    ErrorCorrectionLevel correction;
    int width;
    int height;
    BufferedImage image;

    public QrCode(String data, ErrorCorrectionLevel correction, int width, int height) {
        QRCodeWriter writer = new QRCodeWriter();

        Map<EncodeHintType, ?> hints = Map.of(EncodeHintType.ERROR_CORRECTION, correction);

        try {
            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, width, height, hints);
            this.image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }
}
