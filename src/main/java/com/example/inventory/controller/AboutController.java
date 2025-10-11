package com.example.inventory.controller; // Package declaration for controller classes

import org.springframework.stereotype.Controller; // Import for Spring MVC controller annotation
import org.springframework.ui.Model; // Import for Spring MVC model interface
import org.springframework.web.bind.annotation.GetMapping; // Import for HTTP GET mapping annotation

@Controller // Marks this class as a Spring MVC controller for handling web requests
public class AboutController { // Controller class for managing About page requests

    @GetMapping("/about") // Maps HTTP GET requests to the /about URL path
    public String showAboutPage(Model model) { // Method to handle About page display requests
        model.addAttribute("shopName", "Alfonso's Auto Parts Shop"); // Adds shop name to the model for template rendering
        model.addAttribute("description", // Adds company description to the model
                "Alfonso's Auto Parts Shop has been serving the automotive community for over 25 years. " +
                "We specialize in high-quality auto parts, from engines and transmissions to brake systems " +
                "and electrical components. Our mission is to provide reliable, affordable parts that keep " +
                "your vehicles running smoothly. Whether you're a professional mechanic or a DIY enthusiast, " +
                "we have the parts and expertise you need. Visit us today and experience the Alfonso's difference!");
        model.addAttribute("mission", // Adds mission statement to the model
                "To provide the highest quality automotive parts and exceptional customer service " +
                "to keep our community's vehicles safe and reliable on the road.");
        return "about"; // Returns the view name that resolves to templates/about.html
    }
}