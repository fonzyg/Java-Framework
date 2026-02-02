package com.example.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.inventory.entity.Part;
import com.example.inventory.service.PartService;
import com.example.inventory.validation.InventoryValidator;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/parts")
public class PartController {

    private final PartService partService;
    private final InventoryValidator inventoryValidator;

    @Autowired
    public PartController(PartService partService, InventoryValidator inventoryValidator) {
        this.partService = partService;
        this.inventoryValidator = inventoryValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(inventoryValidator);
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("part", new Part());
        return "addpart";
    }
    
    @PostMapping("/add")
    public String addPart(@Valid @ModelAttribute("part") Part part, 
                          BindingResult result) {
        if (result.hasErrors()) {
            return "addpart";
        }
        partService.save(part);
        return "redirect:/";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Part part = partService.findById(id);
        if (part == null) {
            return "redirect:/";
        }
        model.addAttribute("part", part);
        return "partForm";
    }
    
    @PostMapping("/edit/{id}")
    public String updatePart(@PathVariable("id") Long id, 
                            @Valid @ModelAttribute("part") Part part,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "partForm";
        }
        part.setId(id);
        partService.save(part);
        return "redirect:/";
    }
    
    @GetMapping("/delete/{id}")
    public String deletePart(@PathVariable("id") Long id) {
        partService.deleteById(id);
        return "redirect:/";
    }
}
