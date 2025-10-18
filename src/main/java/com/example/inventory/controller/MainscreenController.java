package com.example.inventory.controller;
import com.example.inventory.service.PartService;
import com.example.inventory.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainscreenController {
    private final PartService partService;
    private final ProductService productService;
    public MainscreenController(PartService partService, ProductService productService) {
        this.partService = partService;
        this.productService = productService;
    }
    @GetMapping({"/", "/mainscreen"})
    public String mainscreen(Model model) {
        model.addAttribute("parts", partService.findAll());
        model.addAttribute("products", productService.findAll());
        return "mainscreen";
    }
}
