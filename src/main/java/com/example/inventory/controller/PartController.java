package com.example.inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.inventory.entity.Part;
import com.example.inventory.service.PartService;
import com.example.inventory.validation.InventoryValidator;

@Controller
@RequestMapping("/parts")
public class PartController {

    private final PartService partService;
    private final InventoryValidator inventoryValidator;

    public PartController(PartService partService, InventoryValidator inventoryValidator) {
        this.partService = partService;
        this.inventoryValidator = inventoryValidator;
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("part", new Part());
        return "partForm";
    }

    @PostMapping("/save")
    public String savePart(@ModelAttribute("part") Part part, BindingResult bindingResult,
                          Model model, RedirectAttributes redirectAttributes) {
        
        inventoryValidator.validate(part, bindingResult);
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("part", part);
            return "partForm";
        }
        
        try {
            partService.save(part);
            redirectAttributes.addFlashAttribute("success",
                    "Part '" + part.getName() + "' saved successfully!");
            return "redirect:/mainscreen";
        } catch (Exception e) {
            model.addAttribute("error", "Error saving part: " + e.getMessage());
            model.addAttribute("part", part);
            return "partForm";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Part part = partService.findById(id);
        
        if (part == null) {
            return "redirect:/mainscreen";
        }
        
        model.addAttribute("part", part);
        return "partForm";
    }

    @GetMapping("/delete/{id}")
    public String deletePart(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Part part = partService.findById(id);
        
        if (part != null) {
            partService.deleteById(id);
            redirectAttributes.addFlashAttribute("success",
                    "Part '" + part.getName() + "' deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Part not found.");
        }
        
        return "redirect:/mainscreen";
    }
}