package com.example.biddingservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class BidTest {

    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Bid actualBid = new Bid();
        actualBid.setAmount(10.0d);
        LocalDateTime bidTime = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualBid.setBidTime(bidTime);
        actualBid.setId("42");
        actualBid.setProductId("42");
        actualBid.setUserId("42");
        Double actualAmount = actualBid.getAmount();
        LocalDateTime actualBidTime = actualBid.getBidTime();
        String actualId = actualBid.getId();
        String actualProductId = actualBid.getProductId();

        assertEquals("42", actualId);
        assertEquals("42", actualProductId);
        assertEquals("42", actualBid.getUserId());
        assertEquals(10.0d, actualAmount.doubleValue());
        assertSame(bidTime, actualBidTime);
    }

    @Test
    void testNewBid() {
        // Arrange and Act
        Bid actualBid = new Bid("42", "42", 10.0d, LocalDate.of(1970, 1, 1).atStartOfDay());

        
        assertEquals("1970-01-01", actualBid.getBidTime().toLocalDate().toString());
        assertEquals("42", actualBid.getProductId());
        assertEquals("42", actualBid.getUserId());
        assertEquals("42:42", actualBid.getId());
        assertEquals(10.0d, actualBid.getAmount().doubleValue());
    }
}
