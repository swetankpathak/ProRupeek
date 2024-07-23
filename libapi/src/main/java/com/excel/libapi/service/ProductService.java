package com.excel.libapi.service;

import com.excel.libapi.entity.Product;
import com.excel.libapi.helper.Helper;
import com.excel.libapi.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    // Method to handle pagination
    public Page<Product> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepo.findAll(pageable);
    }

    // Method to save products from an Excel file
    public void save(MultipartFile file) {
        try {
            List<Product> products = Helper.convertExcelToListOfProduct(file.getInputStream());
            // Insert all products without checking for existing records
            this.productRepo.saveAll(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
