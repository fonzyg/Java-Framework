package com.example.inventory.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.inventory.entity.Part;
import com.example.inventory.entity.Product;
import com.example.inventory.service.PartService;
import com.example.inventory.service.ProductService;
@Controller
public class ProductBuyController {
    private final ProductService productService;
    private final PartService partService;

    public ProductBuyController(ProductService productService, PartService partService) {
        this.productService = productService;
        this.partService = partService;
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

        // Check if all associated parts have sufficient inventory
        for (Part part : product.getParts()) {
            if (part.getInv() <= 0) {
                redirectAttributes.addFlashAttribute("error",
                    "Cannot purchase product: " + part.getName() + " is out of stock!");
                return "redirect:/mainscreen";
            }
        }

        // Reduce product inventory by 1
        product.setInv(product.getInv() - 1);

        // Reduce inventory of all associated parts by 1
        for (Part part : product.getParts()) {
            part.setInv(part.getInv() - 1);
            partService.save(part);
        }

        // Save the updated product
        productService.save(product);

        redirectAttributes.addFlashAttribute("success",
                "Successfully purchased " + product.getName() + "! Remaining inventory: " + product.getInv());
        return "redirect:/mainscreen";
    }
}
