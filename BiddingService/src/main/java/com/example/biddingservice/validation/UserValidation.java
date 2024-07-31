package com.example.biddingservice.validation;

import com.example.biddingservice.model.Bid;
import com.example.biddingservice.model.ProductUser;
import com.example.biddingservice.model.UserCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserValidation implements ValidationStrategy {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void validate(Bid bid) throws ValidationException {
        String query = "SELECT new com.example.biddingservice.model.ProductUser(u.userId, u.userName, u.email, u.userCategory) FROM ProductUser u WHERE u.userId = :userId";

        TypedQuery<ProductUser> typedQuery = entityManager.createQuery(query, ProductUser.class);
        typedQuery.setParameter("userId", bid.getUserId());

        List<ProductUser> users = typedQuery.getResultList();
        if (users.isEmpty()) {
            throw new ValidationException("No user exist with this user id");
        }

        if (users.stream().noneMatch(user -> user.getUserCategory().equals(UserCategory.Buyer))) {
            throw new ValidationException("User is not a Buyer. Only Buyer can bid on products");
        }
    }
}
