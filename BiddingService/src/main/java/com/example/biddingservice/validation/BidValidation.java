package com.example.biddingservice.validation;

import com.example.biddingservice.model.Bid;
import com.example.biddingservice.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class BidValidation implements ValidationStrategy {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void validate(Bid bid) throws ValidationException {
        String query = "SELECT new com.example.biddingservice.model.Product(u.productId, u.vendorId, u.basePrice, u.productCategory, u.slotStart, u.slotEnd) " +
                "FROM Product u WHERE u.productId = :userId";


        TypedQuery<Product> typedQuery = entityManager.createQuery(query, Product.class);
        typedQuery.setParameter("userId", bid.getProductId());

        List<Product> productInfo = typedQuery.getResultList();

        if (productInfo.stream().noneMatch(user -> user.getProductId().equals(bid.getProductId()))) {
            throw new ValidationException("Product does not exist");
        }

        Optional<Product> product = productInfo.stream().filter(info -> info.getProductId().equals(bid.getProductId())).findFirst();
        if (product.isPresent() && product.get().getBasePrice() > bid.getAmount()) {
            throw new ValidationException("Bid Amount is lower than Base Price");
        }

        LocalDateTime bidTime = bid.getBidTime();
        LocalDateTime slotStartTime = product.get().getSlotStart();
        LocalDateTime slotEndTime = product.get().getSlotEnd();

        if (bidTime.isBefore(slotStartTime)) {
            throw new ValidationException("Bidding have not started");
        } else if (bidTime.isAfter(slotEndTime)) {
            throw new ValidationException("Bidding time has ended");
        }
    }
}
