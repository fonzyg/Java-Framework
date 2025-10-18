package com.example.inventory.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.inventory.entity.Product;
import com.example.inventory.service.ProductService;
@Controller
public class ProductBuyController {
    private final ProductService productService;
    public ProductBuyController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/products/buy/{id}")
    public String buyProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Product product = productService.findById(id);
        if (product == null) {
            redirectAttributes.addFlashAttribute("error", "Product not found.");
            return "redirect:/mainscreen";
        }
        if (product.getInv() <= 0) {
            redirectAttributes.addFlashAttribute("error", "Product is out of stock.");
            return "redirect:/mainscreen";
        }
        product.setInv(product.getInv() - 1);
        productService.save(product);
        redirectAttributes.addFlashAttribute("success",
                "Successfully purchased " + product.getName() + "! Remaining inventory: " + product.getInv());
        return "redirect:/mainscreen";
    }
}
