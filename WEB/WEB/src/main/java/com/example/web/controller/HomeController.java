package com.example.web.controller;

import com.example.web.entity.Category;
import com.example.web.entity.Product;
import com.example.web.repository.CategoryRepository;
import com.example.web.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

//@Controller
//public class HomeController {
//    @RequestMapping("/")
//    public String showIndex(){
//        return "index";
//    }
//}

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping("")
    public String getProductList(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "index";
    }
}
