package com.example.biddingservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class ProductTest {
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Product actualProduct = new Product();
        actualProduct.setBasePrice(10.0d);
        actualProduct.setProductCategory("Product Category");
        actualProduct.setProductDescription("Product Description");
        actualProduct.setProductId("42");
        actualProduct.setProductName("Product Name");
        LocalDateTime slotEnd = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualProduct.setSlotEnd(slotEnd);
        LocalDateTime slotStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualProduct.setSlotStart(slotStart);
        actualProduct.setVendorId("42");
        Double actualBasePrice = actualProduct.getBasePrice();
        String actualProductCategory = actualProduct.getProductCategory();
        String actualProductDescription = actualProduct.getProductDescription();
        String actualProductId = actualProduct.getProductId();
        String actualProductName = actualProduct.getProductName();
        LocalDateTime actualSlotEnd = actualProduct.getSlotEnd();
        LocalDateTime actualSlotStart = actualProduct.getSlotStart();


        assertEquals("42", actualProductId);
        assertEquals("42", actualProduct.getVendorId());
        assertEquals("Product Category", actualProductCategory);
        assertEquals("Product Description", actualProductDescription);
        assertEquals("Product Name", actualProductName);
        assertEquals(10.0d, actualBasePrice.doubleValue());
        assertSame(slotEnd, actualSlotEnd);
        assertSame(slotStart, actualSlotStart);
    }

    @Test
    void testGettersAndSetters2() {
        // Arrange
        LocalDateTime slotStart = LocalDate.of(1970, 1, 1).atStartOfDay();

        
        Product actualProduct = new Product("42", "42", 10.0d, "Product Category", slotStart,
                LocalDate.of(1970, 1, 1).atStartOfDay());
        actualProduct.setBasePrice(10.0d);
        actualProduct.setProductCategory("Product Category");
        actualProduct.setProductDescription("Product Description");
        actualProduct.setProductId("42");
        actualProduct.setProductName("Product Name");
        LocalDateTime slotEnd = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualProduct.setSlotEnd(slotEnd);
        LocalDateTime slotStart2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualProduct.setSlotStart(slotStart2);
        actualProduct.setVendorId("42");
        Double actualBasePrice = actualProduct.getBasePrice();
        String actualProductCategory = actualProduct.getProductCategory();
        String actualProductDescription = actualProduct.getProductDescription();
        String actualProductId = actualProduct.getProductId();
        String actualProductName = actualProduct.getProductName();
        LocalDateTime actualSlotEnd = actualProduct.getSlotEnd();
        LocalDateTime actualSlotStart = actualProduct.getSlotStart();

         
        assertEquals("42", actualProductId);
        assertEquals("42", actualProduct.getVendorId());
        assertEquals("Product Category", actualProductCategory);
        assertEquals("Product Description", actualProductDescription);
        assertEquals("Product Name", actualProductName);
        assertEquals(10.0d, actualBasePrice.doubleValue());
        assertSame(slotEnd, actualSlotEnd);
        assertSame(slotStart2, actualSlotStart);
    }

    @Test
    void testGettersAndSetters3() {
        // Arrange
        LocalDateTime slotStart = LocalDate.of(1970, 1, 1).atStartOfDay();

        
        Product actualProduct = new Product("42", "42", "Product Name", "Product Description", 10.0d, "Product Category",
                slotStart, LocalDate.of(1970, 1, 1).atStartOfDay());
        actualProduct.setBasePrice(10.0d);
        actualProduct.setProductCategory("Product Category");
        actualProduct.setProductDescription("Product Description");
        actualProduct.setProductId("42");
        actualProduct.setProductName("Product Name");
        LocalDateTime slotEnd = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualProduct.setSlotEnd(slotEnd);
        LocalDateTime slotStart2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualProduct.setSlotStart(slotStart2);
        actualProduct.setVendorId("42");
        Double actualBasePrice = actualProduct.getBasePrice();
        String actualProductCategory = actualProduct.getProductCategory();
        String actualProductDescription = actualProduct.getProductDescription();
        String actualProductId = actualProduct.getProductId();
        String actualProductName = actualProduct.getProductName();
        LocalDateTime actualSlotEnd = actualProduct.getSlotEnd();
        LocalDateTime actualSlotStart = actualProduct.getSlotStart();

         
        assertEquals("42", actualProductId);
        assertEquals("42", actualProduct.getVendorId());
        assertEquals("Product Category", actualProductCategory);
        assertEquals("Product Description", actualProductDescription);
        assertEquals("Product Name", actualProductName);
        assertEquals(10.0d, actualBasePrice.doubleValue());
        assertSame(slotEnd, actualSlotEnd);
        assertSame(slotStart2, actualSlotStart);
    }
}
