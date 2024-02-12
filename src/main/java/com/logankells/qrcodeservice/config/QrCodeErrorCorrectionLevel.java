package com.logankells.qrcodeservice.config;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QrCodeErrorCorrectionLevel {

    @Bean
    public Map<String, ErrorCorrectionLevel> correctionLevelMap() {
        Map<String, ErrorCorrectionLevel> errorCorrectionLevelMap = new HashMap<>(4);
        errorCorrectionLevelMap.put("L", ErrorCorrectionLevel.L);
        errorCorrectionLevelMap.put("M", ErrorCorrectionLevel.M);
        errorCorrectionLevelMap.put("Q", ErrorCorrectionLevel.Q);
        errorCorrectionLevelMap.put("H", ErrorCorrectionLevel.H);
        return errorCorrectionLevelMap;
    }
}
