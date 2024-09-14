package com.example.demo.controller;

import com.example.demo.dto.ApiReponse;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/find-all")
    public ResponseEntity<ApiReponse> findAll() {
        return ResponseEntity.ok(ApiReponse
                .builder()
                .data(productService.findAll())
                .message("Success")
                .build());
    }

    @PostMapping("/upload-customers-data")
    public ResponseEntity<ApiReponse> uploadCustomersData(@RequestParam("file") MultipartFile file) {
        try {
            productService.importData(file);
            return ResponseEntity.ok(ApiReponse
                    .builder()
                    .message("File uploaded and data imported successfully.")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiReponse.builder()
                            .message("An error occurred while importing data.")
                            .build());
        }
    }
    @PostMapping("/upload-image")
    public ResponseEntity<ApiReponse> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            productService.uploadImage(file);
            return ResponseEntity.ok(ApiReponse
                    .builder()
                    .message("Image uploaded successfully.")
                    .build());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiReponse.builder()
                            .message("An error occurred while uploading image.")
                            .build());
        }
    }


}
