package com.example.inventory.controller;
import com.example.inventory.entity.Part;
import com.example.inventory.entity.Product;
import com.example.inventory.service.PartService;
import com.example.inventory.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.Set;
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
        model.addAttribute("product", new Product());
        model.addAttribute("allParts", partService.findAll());
        return "addproduct";
    }
    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("product") Product product,
                            @RequestParam(value = "partIds", required = false) String partIds,
                            BindingResult result, Model model,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("allParts", partService.findAll());
            return "addproduct";
        }

        // Add selected parts to the product based on comma-separated IDs
        Set<Part> selectedParts = new HashSet<>();
        if (partIds != null && !partIds.trim().isEmpty()) {
            String[] partIdArray = partIds.split(",");
            for (String partIdStr : partIdArray) {
                try {
                    Long partId = Long.parseLong(partIdStr.trim());
                    Part part = partService.findById(partId);
                    if (part != null) {
                        selectedParts.add(part);
                    }
                } catch (NumberFormatException e) {
                    // Skip invalid IDs
                }
            }
        }

        // Validation: Check if product has at least one part
        if (selectedParts.isEmpty()) {
            model.addAttribute("error", "Not enough Associated Parts! A product must have at least one part.");
            model.addAttribute("allParts", partService.findAll());
            return "addproduct";
        }

        product.setParts(selectedParts);
        productService.save(product);

        redirectAttributes.addFlashAttribute("success", "Product added successfully!");
        return "redirect:/mainscreen";
    }
    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/mainscreen";
        }
        model.addAttribute("product", product);
        model.addAttribute("allParts", partService.findAll());
        return "edit-product";
    }
    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id,
                               @Valid @ModelAttribute("product") Product product,
                               @RequestParam(value = "partIds", required = false) String partIds,
                               BindingResult result, Model model,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("allParts", partService.findAll());
            return "edit-product";
        }

        product.setId(id);

        // Update the product's parts based on comma-separated IDs
        Set<Part> selectedParts = new HashSet<>();
        if (partIds != null && !partIds.trim().isEmpty()) {
            String[] partIdArray = partIds.split(",");
            for (String partIdStr : partIdArray) {
                try {
                    Long partId = Long.parseLong(partIdStr.trim());
                    Part part = partService.findById(partId);
                    if (part != null) {
                        selectedParts.add(part);
                    }
                } catch (NumberFormatException e) {
                    // Skip invalid IDs
                }
            }
        }

        // Validation: Check if product has at least one part
        if (selectedParts.isEmpty()) {
            model.addAttribute("error", "Not enough Associated Parts! A product must have at least one part.");
            model.addAttribute("allParts", partService.findAll());
            return "edit-product";
        }

        product.setParts(selectedParts);
        productService.save(product);

        redirectAttributes.addFlashAttribute("success", "Product updated successfully!");
        return "redirect:/mainscreen";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/mainscreen";
    }
}
