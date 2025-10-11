package com.example.inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.inventory.service.PartService;
import com.example.inventory.service.ProductService;

@Controller
public class MainscreenController {

    private final PartService partService;
    private final ProductService productService;

    public MainscreenController(PartService partService, ProductService productService) {
        this.partService = partService;
        this.productService = productService;
    }

    @GetMapping("/mainscreen")
    public String showMainScreen(Model model) {
        model.addAttribute("parts", partService.findAll());
        model.addAttribute("products", productService.findAll());
        return "mainscreen";
    }
    
    @GetMapping("/")
    public String home() {
        return "redirect:/mainscreen";
    }
}