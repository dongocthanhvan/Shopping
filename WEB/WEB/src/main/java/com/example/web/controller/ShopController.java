package com.example.web.controller;

import com.example.web.entity.Category;
import com.example.web.entity.Product;
import com.example.web.entity.Shop;
import com.example.web.repository.ProductRepository;
import com.example.web.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ShopRepository shopRepository;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping("")
    public String getShopList(Model model) {
        List<Shop> shops = shopRepository.findAll();
        List<Product> products = productRepository.findAll();
        model.addAttribute("shops", shops);
        model.addAttribute("products", products);
        return "shopList";
    }

    @RequestMapping("/{id}")
    public String getShopById(Model model,
                              @PathVariable("id") Long id) {
        Shop shop = shopRepository.getById(id);
        model.addAttribute("shop", shop);
        return "shopDetail";
    }

    @RequestMapping("/add")
    public String addShop(Model model) {
        Shop shop = new Shop();
        model.addAttribute("shop", shop);
        return "shopAdd";
    }

    @RequestMapping("/update/{id}")
    public String updateShop(Model model,
                             @PathVariable("id") Long id) {
        Shop shop = shopRepository.getById(id);
        model.addAttribute("shop", shop);
        return "shopUpdate";
    }

    @RequestMapping("/save")
    public String save(@Valid Shop shop, BindingResult result,
                       @RequestParam(value = "id", required = false) Long id) {
        if (result.hasErrors()) {
            if (id == null) {
                return "shopAdd";
            } else {
                return "shopUpdate";
            }
        }
        shop.setId(id);
        shopRepository.save(shop);
        return "redirect:/shop";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        Shop shop = shopRepository.getById(id);
        shopRepository.delete(shop);
        return "redirect:/shop";
    }

    @RequestMapping("/asc")
    public String sortShopAsc(Model model) {
        List<Shop> shops = shopRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        model.addAttribute("shops", shops);
        return "shopList";
    }

    @RequestMapping("/desc")
    public String sortCategoryDesc(Model model) {
        List<Shop> shops = shopRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
        model.addAttribute("shops", shops);
        return "shopList";
    }
}