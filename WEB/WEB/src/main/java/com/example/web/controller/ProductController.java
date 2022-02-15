package com.example.web.controller;

import com.example.web.entity.*;
import com.example.web.entity.Product;
import com.example.web.repository.CategoryRepository;
import com.example.web.repository.ProductDetailRepository;
import com.example.web.repository.ProductRepository;
import com.example.web.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ShopRepository shopRepository;

    @RequestMapping("")
    public String getProductList(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "productList";
    }

    @RequestMapping("/{id}")
    public String getProductById(Model model,
                             @PathVariable(value = "id") Long id) {
        Product product = productRepository.getById(id);
        ProductDetail productDetail = productDetailRepository.getById(id);
        List<Shop> shops = shopRepository.findAll();
        model.addAttribute("product", product);
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("shops", shops);
        return "productDetail";
    }

    @RequestMapping("/add")
    public String addProduct (Model model) {
        Product product = new Product();
        ProductDetail productDetail = new ProductDetail();
        List<Category> categories = categoryRepository.findAll();
        List<Shop> shops = shopRepository.findAll();
//        List<Product> products  = productRepository.findByNameContaining(name);
        model.addAttribute("product",   product);
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("categories", categories);
        model.addAttribute("shops", shops);
        return "productAdd";
    }

    @RequestMapping("/update/{id}")
    public String updateProduct(
            @PathVariable (value = "id") Long id, Model model)  {
        Product product = productRepository.getById(id);
        ProductDetail productDetail = productDetailRepository.getById(id);
        List<Category> categories = categoryRepository.findAll();
        List<Shop> shops = shopRepository.findAll();
        model.addAttribute("product", product);
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("categories", categories);
        model.addAttribute("shops", shops);
        return "productUpdate";
    }

    @RequestMapping("/save")
    public String saveUpdate (
            Product product,
            ProductDetail productDetail,
            @RequestParam(value = "id", required = false) Long id)
    {
        product.setId(id);
        productDetail.setId(id);
        productRepository.save(product);
        String name = product.getName();
//        List<Product> products  = productRepository.findByNameContaining(nameinput);
//        for(int i =0; )
//        if(name ==)
        productDetailRepository.save(productDetail);
        return "redirect:/product";
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(
            @PathVariable(value = "id") Long id) {
        Product product = productRepository.getById(id);
        productRepository.delete(product);
        return "redirect:/product";
    }

    @RequestMapping("/search")
    public String searchProduct(
            Model model,
            @RequestParam(value = "name") String name) {
        List<Product> products  = productRepository.findByNameContaining(name);
        model.addAttribute("products", products);
        return "productList";
    }

    @RequestMapping("/search1")
    public String searchProduct1(
            Model model,
            @RequestParam(value = "name") String name) {
        List<Product> products  = productRepository.findByNameContaining(name);
        model.addAttribute("products", products);
        return "productList";
    }
}
