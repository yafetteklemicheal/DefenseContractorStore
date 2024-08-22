package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;

@Controller
public class ConfirmationBuyProductController {
    @Autowired
    private ApplicationContext context;
    private ProductRepository productRepository;
    public ConfirmationBuyProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping("/buyproduct")
    public String buyProduct (@Valid @RequestParam ("productID") int productID) {
        ProductService repo = context.getBean(ProductServiceImpl.class);
        Product product=repo.findById(productID);
        if((product != null) && (product.getInv() > 0)){
            product.setInv(product.getInv() - 1);
            productRepository.save(product);
                return "confirmationbuyproduct";
        }
        else{
            return "outofstockerror";
        }
    }
}