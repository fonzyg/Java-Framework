package com.example.inventory.controller;
import com.example.inventory.entity.Product;
import com.example.inventory.entity.Part;
import com.example.inventory.service.ProductService;
import com.example.inventory.service.PartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final PartService partService;

    public ProductController(ProductService productService, PartService partService) {
        this.productService = productService;
        this.partService = partService;
    }
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        List<Part> allParts = partService.findAll();
        model.addAttribute("product", new Product());
        model.addAttribute("allParts", allParts);
        return "addproduct";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("product") Product product,
                            @RequestParam(value = "partIds", required = false) List<Long> partIds,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Part> allParts = partService.findAll();
            model.addAttribute("allParts", allParts);
            return "addproduct";
        }

        // Add selected parts to the product
        Set<Part> selectedParts = new HashSet<>();
        if (partIds != null && !partIds.isEmpty()) {
            for (Long partId : partIds) {
                Part part = partService.findById(partId);
                if (part != null) {
                    selectedParts.add(part);
                }
            }
        }
        product.setParts(selectedParts);

        productService.save(product);
        return "redirect:/mainscreen";
    }
    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/mainscreen";
        }
        List<Part> allParts = partService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("allParts", allParts);
        return "edit-product";
    }
    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id,
                               @Valid @ModelAttribute("product") Product product,
                               @RequestParam(value = "partIds", required = false) List<Long> partIds,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Part> allParts = partService.findAll();
            model.addAttribute("allParts", allParts);
            return "edit-product";
        }

        product.setId(id);

        // Update the product's parts based on selected checkboxes
        Set<Part> selectedParts = new HashSet<>();
        if (partIds != null && !partIds.isEmpty()) {
            for (Long partId : partIds) {
                Part part = partService.findById(partId);
                if (part != null) {
                    selectedParts.add(part);
                }
            }
        }
        product.setParts(selectedParts);

        productService.save(product);
        return "redirect:/mainscreen";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/mainscreen";
    }
}
