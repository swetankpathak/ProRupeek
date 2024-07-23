package com.excel.libapi.controller;

import com.excel.libapi.entity.Product;
import com.excel.libapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Endpoint to upload products from an Excel file
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            productService.save(file);
            return "File uploaded and data saved successfully.";
        } catch (Exception e) {
            return "Failed to upload and save data: " + e.getMessage();
        }
    }

    // Endpoint to get paginated products
    @GetMapping
    public Page<Product> getProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return productService.getProducts(page, size);
    }
}
