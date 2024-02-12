package com.logankells.qrcodeservice.service;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logankells.qrcodeservice.exceptions.BadRequestException;
import com.logankells.qrcodeservice.model.QrCode;

import java.awt.image.BufferedImage;
import java.util.Map;

@Service
public class QrCodeService {

    public Map<String, ErrorCorrectionLevel> correctionLevel;

    @Autowired
    public QrCodeService(Map<String, ErrorCorrectionLevel> correctionLevel) {
        this.correctionLevel = correctionLevel;
    }

    public ResponseEntity<BufferedImage> getQrCode(String data, String correction, int size, String type) {
        if (data.isEmpty() || data.isBlank()) {
            throw new BadRequestException("Contents cannot be null or blank");
        }
        if (size < 150 || size > 350) {
            throw new BadRequestException("Image size must be between 150 and 350 pixels");
        }
        if (!"L".equals(correction) && !"M".equals(correction)
                && !"Q".equals(correction) && !"H".equals(correction)) {
            throw new BadRequestException("Permitted error correction levels are L, M, Q, H");
        }
        if (!"png".equals(type) && !"jpeg".equals(type) && !"gif".equals(type)) {
            throw new BadRequestException("Only png, jpeg and gif image types are supported");
        }
        QrCode qrCode = new QrCode(data, correctionLevel.get(correction), size, size);
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType("image/" + type))
                .body(qrCode.getImage());
    }
}


