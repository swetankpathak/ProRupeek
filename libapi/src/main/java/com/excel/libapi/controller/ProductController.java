package com.excel.libapi.controller;


import com.excel.libapi.entity.Product;
import com.excel.libapi.helper.Helper;
import com.excel.libapi.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class ProductController {

    @Autowired
private ProductService productService;

    @PostMapping ("/product/upload")
public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file){
        if(Helper.checkExcelFormat(file)){
            this.productService.save(file);
            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to DB"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload in excel");
    }

    @GetMapping("/product")
    public List<Product> getAllProduct(){
        return this.productService.getAllProducts();
    }

}
