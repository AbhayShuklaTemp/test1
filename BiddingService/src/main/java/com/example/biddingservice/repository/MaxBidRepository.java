package com.example.biddingservice.repository;

import com.example.biddingservice.model.MaxBid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaxBidRepository extends JpaRepository<MaxBid, String> {
}
