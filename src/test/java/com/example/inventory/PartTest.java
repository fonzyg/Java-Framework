package com.example.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.inventory.entity.Part;

public class PartTest {

    @Test
    public void testValidInventoryWithinRange() {
        Part part = new Part();
        part.setName("Test Part");
        part.setPrice(10.0);
        part.setInv(50);
        part.setMinInv(10);
        part.setMaxInv(100);
        
        assertTrue(part.isInventoryValid());
        assertTrue(part.isMinMaxValid());
    }

    @Test
    public void testInvalidInventoryBelowMinimum() {
        Part part = new Part();
        part.setName("Test Part");
        part.setPrice(10.0);
        part.setInv(5);
        part.setMinInv(10);
        part.setMaxInv(100);
        
        assertFalse(part.isInventoryValid());
        assertTrue(part.isMinMaxValid());
    }

    @Test
    public void testInvalidInventoryAboveMaximum() {
        Part part = new Part();
        part.setName("Test Part");
        part.setPrice(10.0);
        part.setInv(150);
        part.setMinInv(10);
        part.setMaxInv(100);
        
        assertFalse(part.isInventoryValid());
        assertTrue(part.isMinMaxValid());
    }

    @Test
    public void testInvalidMinGreaterThanMax() {
        Part part = new Part();
        part.setName("Test Part");
        part.setPrice(10.0);
        part.setInv(50);
        part.setMinInv(100);
        part.setMaxInv(50);
        
        assertFalse(part.isMinMaxValid());
    }

    @Test
    public void testInventoryValidationWithNullValues() {
        Part part = new Part();
        part.setName("Test Part");
        part.setPrice(10.0);
        part.setInv(50);
        part.setMinInv(null);
        part.setMaxInv(null);

        assertTrue(part.isInventoryValid());
    }

    @Test
    public void testMinMaxValidationWithNullValues() {
        Part part = new Part();
        part.setName("Test Part");
        part.setPrice(10.0);
        part.setInv(50);
        part.setMinInv(null);
        part.setMaxInv(100);
        
        assertTrue(part.isMinMaxValid());
    }

    @Test
    public void testValidInventoryAtBoundaries() {
        Part part = new Part();
        part.setName("Test Part");
        part.setPrice(10.0);
        part.setMinInv(10);
        part.setMaxInv(100);
        
        // Test at minimum boundary
        part.setInv(10);
        assertTrue(part.isInventoryValid());
        
        // Test at maximum boundary
        part.setInv(100);
        assertTrue(part.isInventoryValid());
        
        assertTrue(part.isMinMaxValid());
    }

    @Test
    public void testPartConstructorWithAllParameters() {
        Part part = new Part("Engine Oil", 25.99, 75, 20, 150);
        
        assertEquals("Engine Oil", part.getName());
        assertEquals(25.99, part.getPrice());
        assertEquals(75, part.getInv());
        assertEquals(20, part.getMinInv());
        assertEquals(150, part.getMaxInv());
        
        assertTrue(part.isInventoryValid());
        assertTrue(part.isMinMaxValid());
    }

    @Test 
    public void testMinimumInventoryValidation() {
        Part part = new Part();
        part.setName("Test Part");
        part.setPrice(15.0);
        part.setMinInv(5);
        part.setMaxInv(50);
        
        // Test inventory below minimum
        part.setInv(3);
        assertFalse(part.isInventoryValid(), "Inventory below minimum should be invalid");
        
        // Test inventory at minimum
        part.setInv(5);
        assertTrue(part.isInventoryValid(), "Inventory at minimum should be valid");
    }

    @Test
    public void testMaximumInventoryValidation() {
        Part part = new Part();
        part.setName("Test Part");
        part.setPrice(20.0);
        part.setMinInv(5);
        part.setMaxInv(50);
        
        // Test inventory above maximum
        part.setInv(55);
        assertFalse(part.isInventoryValid(), "Inventory above maximum should be invalid");
        
        // Test inventory at maximum
        part.setInv(50);
        assertTrue(part.isInventoryValid(), "Inventory at maximum should be valid");
    }
}