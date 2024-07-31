package com.example.biddingservice.validation;

import com.example.biddingservice.model.Bid;
import com.example.biddingservice.model.Product;
import com.example.biddingservice.model.ProductUser;
import com.example.biddingservice.model.UserCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.xml.bind.ValidationException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UserValidationTest {
    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<ProductUser> typedQuery;

    @InjectMocks
    private UserValidation userValidationMock;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testValidate() {

        Bid bid = new Bid();
        bid.setAmount(10.0d);
        bid.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid.setId("42");
        bid.setProductId("42");
        bid.setUserId("42");

        ProductUser mockProductUser = new ProductUser();

        mockProductUser.setUserCategory(UserCategory.Vendor);
        mockProductUser.setUserId("42");


        List<ProductUser> mockProducts = Arrays.asList(mockProductUser);

        when(entityManager.createQuery("SELECT new com.example.biddingservice.model.ProductUser(u.userId, u.userName, u.email, u.userCategory) FROM ProductUser u WHERE u.userId = :userId", ProductUser.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(mockProducts);

        assertThrows(ValidationException.class, () -> userValidationMock.validate(bid));
    }
}
