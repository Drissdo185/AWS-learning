package com.example.demo.service.Impl;

import com.example.demo.entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class UploadServiceImpl{

    public static List<Product> getProductFromExcel(InputStream inputStream) {
        List<Product> productList = new ArrayList<>();
        try{
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            int rowIndex = 0;
            for(Row row : sheet){
                if(rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Product product = new Product();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 0 -> product.setId(cell.getStringCellValue());
                        case 1 -> product.setProductName(cell.getStringCellValue());
                        case 2 -> product.setProductDescription(cell.getStringCellValue());
                        case 3 -> product.setPrice(BigDecimal.valueOf(cell.getNumericCellValue()));
                        case 4 -> product.setCategory(cell.getStringCellValue());
                        case 5 -> product.setInStock(cell.getBooleanCellValue());
                        default -> {
                        }
                    }
                    cellIndex++;
                }
                productList.add(product);
            }

        } catch (IOException e) {
            e.getStackTrace();
        }
        return productList;
    }

}
