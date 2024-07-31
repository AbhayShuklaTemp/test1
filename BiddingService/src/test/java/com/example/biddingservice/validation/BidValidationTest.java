package com.example.biddingservice.validation;

import com.example.biddingservice.model.Bid;
import com.example.biddingservice.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.ValidationException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.IsAnything;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BidValidationTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Product> typedQuery;

    @InjectMocks
    private BidValidation bidValidationMock;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testValidate(){

        // Arrange
        BidValidation bidValidation = new BidValidation();

        Bid bid = new Bid();
        bid.setAmount(10.0);
        bid.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid.setId("42:42");
        bid.setProductId("42");
        bid.setUserId("42");

        Product mockProduct = new Product();

        mockProduct.setBasePrice(100.0);
        mockProduct.setProductId("42");
        mockProduct.setVendorId("42");

        List<Product> mockProducts = Arrays.asList(mockProduct);

        when(entityManager.createQuery("SELECT new com.example.biddingservice.model.Product(u.productId, u.vendorId, u.basePrice, u.productCategory, u.slotStart, u.slotEnd) " +
                "FROM Product u WHERE u.productId = :userId", Product.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(mockProducts);

        assertThrows(ValidationException.class, () -> bidValidationMock.validate(bid));
    }
}
