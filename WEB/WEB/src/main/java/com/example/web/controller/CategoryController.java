package com.example.web.controller;

import com.example.web.entity.Category;
import com.example.web.entity.Product;
import com.example.web.repository.CategoryRepository;
import com.example.web.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping("")
    public String getCategoryList(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "categoryList";
    }

    @RequestMapping("/{id}")
    public String getCategoryById(Model model,
                                  @PathVariable(value = "id") Long id) {
        Category category = categoryRepository.getById(id);
        List<Product> products = productRepository.findAll();
        model.addAttribute("category", category);
        model.addAttribute("products", products);
        return "categoryDetail";
    }

    @RequestMapping("/add")
    public String addCategory (Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "categoryAdd";
    }

    @RequestMapping("/update/{id}")
    public String updateCategory(
            @PathVariable (value = "id") Long id, Model model)  {
        Category category = categoryRepository.findById(id).get();
        model.addAttribute(category);
        return "categoryUpdate";
    }

    @RequestMapping("/save")
    public String saveUpdate (
            Category category,
            @RequestParam(value = "id", required = false) Long id) {
        category.setId(id);
        categoryRepository.save(category);
        return "redirect:/category";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCategory(
            @PathVariable(value = "id") Long id) {
        Category category = categoryRepository.findById(id).get();
        categoryRepository.delete(category);
        return "redirect:/category";
    }

    @RequestMapping("/asc")
    public String sortCategoryAsc(Model model) {
        List<Category> categories = categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        model.addAttribute("categories", categories);
        return "categoryList";
    }

    @RequestMapping("/desc")
    public String sortCategoryDesc(Model model) {
        List<Category> categories = categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
        model.addAttribute("categories", categories);
        return "categoryList";
    }
}