package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    void importData(MultipartFile file);
    void uploadImage(MultipartFile file) throws IOException;
    List<ProductDto> findAll();
}
