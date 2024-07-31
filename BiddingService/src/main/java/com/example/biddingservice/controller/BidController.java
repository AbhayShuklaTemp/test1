package com.example.biddingservice.controller;

import com.example.biddingservice.model.Bid;
import com.example.biddingservice.model.MaxBid;
import com.example.biddingservice.service.BidService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bid")
public class BidController {
    @Autowired
    private BidService bidService;

    @GetMapping("/getAllBids")
    public List<Bid> getAllBids() {
        return bidService.getAllBids();
    }

    @NotNull
    @GetMapping("/getBid")
    public ResponseEntity<Bid> getBidById(@Valid @RequestParam(name = "productId") String productId,
                                          @Valid @RequestParam(name = "userId") String userId) {
        return bidService.getBidById(productId, userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @NotNull
    @GetMapping("/getMaxBid")
    public ResponseEntity<MaxBid> getMaxBid(@Valid @RequestParam(name = "productId") String productId) {
        try {
            MaxBid bid = bidService.getMaxBid(productId);
            return ResponseEntity.ok(bid);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @NotNull
    @PostMapping("/createBid")
    public ResponseEntity<?>  placeBid(@Valid @RequestBody Bid bidDetails) {
        try {
            Bid bid  = bidService.placeBid(bidDetails);
            return ResponseEntity.ok(bid);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @NotNull
    @GetMapping("/product")
    public Optional<List<Bid>> getBidsByProductId(@Valid @RequestParam(name = "productId") String productId) {
        return bidService.getBidsByProductId(productId);
    }

    @NotNull
    @PutMapping("/updateBid")
    public ResponseEntity<?> updateBid(@Valid @RequestBody Bid bidDetails) {
        try {
            Bid bid = bidService.updateBid(bidDetails);
            return ResponseEntity.ok(bid);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
