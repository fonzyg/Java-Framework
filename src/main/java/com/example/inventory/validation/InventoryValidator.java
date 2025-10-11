package com.example.inventory.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.inventory.entity.Part;

@Component
public class InventoryValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Part.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Part part = (Part) target;
        
        if (part.getMin() < 0) {
            errors.rejectValue("min", "min.negative",
                    "Minimum inventory cannot be negative.");
        }
        
        if (part.getMax() < 0) {
            errors.rejectValue("max", "max.negative",
                    "Maximum inventory cannot be negative.");
        }
        
        if (part.getMin() > part.getMax()) {
            errors.rejectValue("min", "min.greater.than.max",
                    "Minimum inventory cannot be greater than maximum inventory.");
        }
        
        if (part.getInv() < part.getMin()) {
            errors.rejectValue("inv", "inv.below.min",
                    "Current inventory (" + part.getInv() + ") is below minimum level (" + part.getMin() + ").");
        }
        
        if (part.getInv() > part.getMax()) {
            errors.rejectValue("inv", "inv.above.max",
                    "Current inventory (" + part.getInv() + ") is above maximum level (" + part.getMax() + ").");
        }
        
        if (part.getPrice() <= 0) {
            errors.rejectValue("price", "price.not.positive",
                    "Price must be greater than zero.");
        }
        
        if (part.getName() == null || part.getName().trim().isEmpty()) {
            errors.rejectValue("name", "name.empty",
                    "Part name cannot be empty.");
        }
    }
}