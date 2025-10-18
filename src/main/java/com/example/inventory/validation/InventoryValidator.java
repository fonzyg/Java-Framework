package com.example.inventory.validation;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.example.inventory.entity.Part;
@Component
public class InventoryValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Part.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Part part = (Part) target;
        if (part.getMinInv() == null) {
            errors.rejectValue("minInv", "required", "Minimum inventory is required");
            return;
        }
        if (part.getMaxInv() == null) {
            errors.rejectValue("maxInv", "required", "Maximum inventory is required");
            return;
        }
        if (part.getMinInv() > part.getMaxInv()) {
            errors.rejectValue("minInv", "invalid", "Minimum inventory cannot be greater than maximum inventory");
        }
        if (part.getInv() != null) {
            if (part.getInv() < part.getMinInv()) {
                errors.rejectValue("inv", "invalid", "Current inventory cannot be less than minimum inventory (" + part.getMinInv() + ")");
            }
            if (part.getInv() > part.getMaxInv()) {
                errors.rejectValue("inv", "invalid", "Current inventory cannot be greater than maximum inventory (" + part.getMaxInv() + ")");
            }
        }
    }
}
