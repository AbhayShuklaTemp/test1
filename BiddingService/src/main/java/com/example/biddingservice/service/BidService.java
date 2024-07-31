package com.example.biddingservice.service;

import com.example.biddingservice.model.Bid;
import com.example.biddingservice.model.MaxBid;
import com.example.biddingservice.repository.BidRepository;
import com.example.biddingservice.repository.MaxBidRepository;
import com.example.biddingservice.validation.BidValidation;
import com.example.biddingservice.validation.UserValidation;
import com.example.biddingservice.validation.ValidationStrategy;
import jakarta.persistence.EntityNotFoundException;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidService {
    private final String empty = "";

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private MaxBidRepository maxBidRepository;

    @Autowired
    List<ValidationStrategy> validationStrategies;

    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    public Optional<Bid> getBidById(String productId, String userId) {
        return bidRepository.findById(productId+Bid.colon+userId);
    }

    public Bid placeBid(Bid bid) throws ValidationException {
        for (ValidationStrategy strategy : validationStrategies) {
            strategy.validate(bid);
        }

        bid.setId(bid.getProductId()+Bid.colon+bid.getUserId());
        bidRepository.save(bid);
        saveMaxBid(bid);

        bid.setId(empty);
        return bid;
    }

    // see if we want to throw any error when productId not present
    public Optional<List<Bid>> getBidsByProductId(String productId) {
        return bidRepository.findByProductId(productId);
    }

    public Bid updateBid(Bid bidDetails) throws ValidationException {
        Bid bid = bidRepository.findById(bidDetails.getProductId()+Bid.colon+bidDetails.getUserId()).orElseThrow(() -> new EntityNotFoundException("Bid not found"));
        for (ValidationStrategy strategy : validationStrategies) {
            strategy.validate(bid);
        }

        bid.setAmount(bidDetails.getAmount());
        bidRepository.save(bid);
        saveMaxBid(bid);

        bid.setId(empty);
        return bid;
    }

    private void saveMaxBid(Bid bidDetails) {
        Optional<MaxBid> maxBid = maxBidRepository.findById(bidDetails.getProductId());

        String userId;
        Double amount = 0.0;
        if (maxBid.isEmpty()) {
            userId = bidDetails.getUserId();
            amount = bidDetails.getAmount();
        } else {
            if (bidDetails.getAmount() > maxBid.get().getAmount()) {
                userId = bidDetails.getUserId();
                amount = bidDetails.getAmount();
            } else {
                userId = maxBid.get().getUserId();
                amount = maxBid.get().getAmount();
            }
        }
        MaxBid bid = new MaxBid(bidDetails.getProductId(), userId, amount);
        maxBidRepository.save(bid);
    }

    public MaxBid getMaxBid(String productId) {
        return maxBidRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("No bid has been made till now on this product"));
    }
}
