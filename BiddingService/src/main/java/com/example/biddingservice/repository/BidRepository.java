package com.example.biddingservice.repository;

import com.example.biddingservice.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BidRepository extends JpaRepository<Bid, String> {
    Optional<List<Bid>> findByProductId(String productId);
}
