package com.example.demo.service.Impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final UploadServiceImpl excelUploadService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Autowired
    private AmazonS3 amazonS3;
    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Override
    public void importData(MultipartFile file) {
        try {
            List<Product> productList = excelUploadService.getProductFromExcel(file.getInputStream());
            productRepository.saveAll(productList);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error 1001", e);
        }
    }

    @Override
    public List<ProductDto> findAll() {
        return productMapper.toDtoList(productRepository.findAll());
    }
    @Override
    public void uploadImage(MultipartFile file) throws IOException {
        amazonS3.putObject(new PutObjectRequest(bucketName, file.getOriginalFilename(), file.getInputStream(), null));
    }


}
