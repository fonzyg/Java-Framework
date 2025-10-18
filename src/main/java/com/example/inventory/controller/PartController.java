package com.example.inventory.controller;
import com.example.inventory.entity.Part;
import com.example.inventory.service.PartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
@Controller
@RequestMapping("/parts")
public class PartController {
    private final PartService partService;
    public PartController(PartService partService) {
        this.partService = partService;
    }
    @GetMapping("/add")
    public String showAddPartForm(Model model) {
        model.addAttribute("part", new Part());
        return "addpart";
    }
    @PostMapping("/add")
    public String addPart(@Valid @ModelAttribute("part") Part part, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addpart";
        }
        if (part.getInv() < part.getMinInv() || part.getInv() > part.getMaxInv()) {
            model.addAttribute("error", "Inventory must be between minimum and maximum values");
            return "addpart";
        }
        partService.save(part);
        return "redirect:/mainscreen";
    }
    @GetMapping("/edit/{id}")
    public String showEditPartForm(@PathVariable Long id, Model model) {
        Part part = partService.findById(id);
        if (part == null) {
            return "redirect:/mainscreen";
        }
        model.addAttribute("part", part);
        return "editpart";
    }
    @PostMapping("/edit/{id}")
    public String updatePart(@PathVariable Long id, @Valid @ModelAttribute("part") Part part, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "editpart";
        }
        part.setId(id);
        partService.save(part);
        return "redirect:/mainscreen";
    }
    @GetMapping("/delete/{id}")
    public String deletePart(@PathVariable Long id) {
        partService.deleteById(id);
        return "redirect:/mainscreen";
    }
}
