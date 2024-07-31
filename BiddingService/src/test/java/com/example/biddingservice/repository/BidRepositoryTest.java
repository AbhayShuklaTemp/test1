package com.example.biddingservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.biddingservice.model.Bid;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {BidRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.example.biddingservice.model"})
@DataJpaTest(properties = {"spring.main.allow-bean-definition-overriding=true"})
class BidRepositoryTest {
    @Autowired
    private BidRepository bidRepository;

    @Test
    void testFindByProductId() {
        // Arrange
        Bid bid = new Bid();
        bid.setAmount(10.0d);
        bid.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid.setId("42");
        bid.setProductId("42");
        bid.setUserId("42");

        Bid bid2 = new Bid();
        bid2.setAmount(0.5d);
        bid2.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid2.setId("Id");
        bid2.setProductId("Product Id");
        bid2.setUserId("User Id");
        bidRepository.save(bid);
        bidRepository.save(bid2);

        
        Optional<List<Bid>> actualFindByProductIdResult = bidRepository.findByProductId("42");

        
        assertEquals(1, actualFindByProductIdResult.get().size());
        assertTrue(actualFindByProductIdResult.isPresent());
    }
}
