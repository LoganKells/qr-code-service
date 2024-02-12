package com.logankells.qrcodeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logankells.qrcodeservice.exceptions.BadRequestException;
import com.logankells.qrcodeservice.service.QrCodeService;

import java.awt.image.BufferedImage;

@RestController
public class ImageController {

    QrCodeService qrCodeService;

    @Autowired
    public ImageController(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping(value = "/api/health")
    public ResponseEntity<String> getHealth() {
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping(value = "/api/qrcode")
    public ResponseEntity<BufferedImage> getQrCode(
            @RequestParam(value = "contents") String contents,
            @RequestParam(value = "correction", required = false, defaultValue = "L") String correction,
            @RequestParam(value = "size", required = false, defaultValue = "250") int size,
            @RequestParam(value = "type", required = false, defaultValue = "png") String type
    ) {
        return qrCodeService.getQrCode(contents, correction, size, type);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException e) {
        return ResponseEntity.badRequest().body(String.format("{\"error\": \"%s\"}", e.getMessage()));
    }
}

