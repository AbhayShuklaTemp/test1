package com.example.biddingservice.validation;

import com.example.biddingservice.model.Bid;
import jakarta.xml.bind.ValidationException;

public interface ValidationStrategy {
    void validate(Bid bid) throws ValidationException;
}
