package com.example.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.example.inventory.entity.Part;
import com.example.inventory.validation.InventoryValidator;

public class PartTest {

    private InventoryValidator validator;
    private Part part;
    private Errors errors;

    @BeforeEach
    public void setUp() {
        validator = new InventoryValidator();
        part = new Part();
        errors = new BeanPropertyBindingResult(part, "part");
    }

    @Test
    public void testValidPartWithinMinMaxRange() {
        part.setName("Test Engine");
        part.setPrice(1500.00);
        part.setInv(15);
        part.setMin(10);
        part.setMax(20);

        validator.validate(part, errors);

        assertFalse(errors.hasErrors(), "Valid part should not have validation errors");
        assertEquals(0, errors.getErrorCount(), "Error count should be zero for valid part");
    }

    @Test
    public void testInvalidPartInventoryBelowMinimum() {
        part.setName("Test Brake Pad");
        part.setPrice(45.00);
        part.setInv(5);
        part.setMin(10);
        part.setMax(50);

        validator.validate(part, errors);

        assertTrue(errors.hasErrors(), "Part with inventory below minimum should have validation errors");
        assertTrue(errors.hasFieldErrors("inv"), "Should have field error for inventory");
        assertEquals("inv.below.min", errors.getFieldError("inv").getCode(),
                "Error code should match expected validation error");
    }

    @Test
    public void testInvalidPartInventoryAboveMaximum() {
        part.setName("Test Oil Filter");
        part.setPrice(12.50);
        part.setInv(250);
        part.setMin(20);
        part.setMax(200);

        validator.validate(part, errors);

        assertTrue(errors.hasErrors(), "Part with inventory above maximum should have validation errors");
        assertTrue(errors.hasFieldErrors("inv"), "Should have field error for inventory");
        assertEquals("inv.above.max", errors.getFieldError("inv").getCode(),
                "Error code should match expected validation error");
    }

    @Test
    public void testInvalidMinimumGreaterThanMaximum() {
        part.setName("Test Transmission");
        part.setPrice(2500.00);
        part.setInv(10);
        part.setMin(30);
        part.setMax(20);

        validator.validate(part, errors);

        assertTrue(errors.hasErrors(), "Part with min > max should have validation errors");
        assertTrue(errors.hasFieldErrors("min"), "Should have field error for minimum");
        assertEquals("min.greater.than.max", errors.getFieldError("min").getCode(),
                "Error code should match expected validation error");
    }

    @Test
    public void testInvalidNegativePrice() {
        part.setName("Test Alternator");
        part.setPrice(-100.00);
        part.setInv(15);
        part.setMin(10);
        part.setMax(30);

        validator.validate(part, errors);

        assertTrue(errors.hasErrors(), "Part with negative price should have validation errors");
        assertTrue(errors.hasFieldErrors("price"), "Should have field error for price");
        assertEquals("price.not.positive", errors.getFieldError("price").getCode(),
                "Error code should match expected validation error");
    }

    @Test
    public void testInvalidEmptyPartName() {
        part.setName("");
        part.setPrice(50.00);
        part.setInv(15);
        part.setMin(10);
        part.setMax(30);

        validator.validate(part, errors);

        assertTrue(errors.hasErrors(), "Part with empty name should have validation errors");
        assertTrue(errors.hasFieldErrors("name"), "Should have field error for name");
        assertEquals("name.empty", errors.getFieldError("name").getCode(),
                "Error code should match expected validation error");
    }
}