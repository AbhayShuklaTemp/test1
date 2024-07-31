package com.example.biddingservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MaxBidTest {

    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        MaxBid actualMaxBid = new MaxBid();
        actualMaxBid.setAmount(10.0d);
        actualMaxBid.setProductId("42");
        actualMaxBid.setUserId("42");
        Double actualAmount = actualMaxBid.getAmount();
        String actualProductId = actualMaxBid.getProductId();

        assertEquals("42", actualProductId);
        assertEquals("42", actualMaxBid.getUserId());
        assertEquals(10.0d, actualAmount.doubleValue());
    }

    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        MaxBid actualMaxBid = new MaxBid("42", "42", 10.0d);
        actualMaxBid.setAmount(10.0d);
        actualMaxBid.setProductId("42");
        actualMaxBid.setUserId("42");
        Double actualAmount = actualMaxBid.getAmount();
        String actualProductId = actualMaxBid.getProductId();

        assertEquals("42", actualProductId);
        assertEquals("42", actualMaxBid.getUserId());
        assertEquals(10.0d, actualAmount.doubleValue());
    }
}
