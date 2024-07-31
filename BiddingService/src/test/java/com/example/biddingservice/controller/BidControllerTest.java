package com.example.biddingservice.controller;

import static org.mockito.Mockito.when;

import com.example.biddingservice.model.Bid;
import com.example.biddingservice.model.MaxBid;
import com.example.biddingservice.service.BidService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BidController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class BidControllerTest {
    @Autowired
    private BidController bidController;

    @MockBean
    private BidService bidService;

    @Test
    void testGetAllBids() throws Exception {
        // Arrange
        when(bidService.getAllBids()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bid/getAllBids");

        
        MockMvcBuilders.standaloneSetup(bidController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetBidById() throws Exception {
        // Arrange
        Bid bid = new Bid();
        bid.setAmount(10.0d);
        bid.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid.setId("42");
        bid.setProductId("42");
        bid.setUserId("42");
        Optional<Bid> ofResult = Optional.of(bid);
        when(bidService.getBidById(Mockito.<String>any(), Mockito.<String>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bid/getBid")
                .param("productId", "foo")
                .param("userId", "foo");

        
        MockMvcBuilders.standaloneSetup(bidController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"productId\":\"42\",\"userId\":\"42\",\"amount\":10.0,\"bidTime\":\"1970-01-01T00:00:00\"}"));
    }

    @Test
    void testGetBidById2() throws Exception {
        // Arrange
        Optional<Bid> emptyResult = Optional.empty();
        when(bidService.getBidById(Mockito.<String>any(), Mockito.<String>any())).thenReturn(emptyResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bid/getBid")
                .param("productId", "foo")
                .param("userId", "foo");

        
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(bidController).build().perform(requestBuilder);

        
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetMaxBid() throws Exception {
        // Arrange
        MaxBid maxBid = new MaxBid();
        maxBid.setAmount(10.0d);
        maxBid.setProductId("42");
        maxBid.setUserId("42");
        when(bidService.getMaxBid(Mockito.<String>any())).thenReturn(maxBid);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bid/getMaxBid")
                .param("productId", "foo");

        
        MockMvcBuilders.standaloneSetup(bidController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"productId\":\"42\",\"userId\":\"42\",\"amount\":10.0}"));
    }

    @Test
    void testGetMaxBid2() throws Exception {
        // Arrange
        when(bidService.getMaxBid(Mockito.<String>any())).thenThrow(new EntityNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bid/getMaxBid")
                .param("productId", "foo");

        
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(bidController).build().perform(requestBuilder);

        
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetBidsByProductId() throws Exception {
        // Arrange
        Optional<List<Bid>> ofResult = Optional.of(new ArrayList<>());
        when(bidService.getBidsByProductId(Mockito.<String>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bid/product")
                .param("productId", "foo");

        
        MockMvcBuilders.standaloneSetup(bidController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testPlaceBid() throws Exception {

        // Arrange
        Bid bid = new Bid();
        bid.setAmount(10.0d);
        bid.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid.setId("42");
        bid.setProductId("42");
        bid.setUserId("42");
        String content = (new ObjectMapper()).writeValueAsString(bid);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/bid/createBid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        
        MockMvcBuilders.standaloneSetup(bidController).build().perform(requestBuilder);
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateBid() throws Exception {

        // Arrange
        Bid bid = new Bid();
        bid.setAmount(10.0d);
        bid.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid.setId("42");
        bid.setProductId("42");
        bid.setUserId("42");
        String content = (new ObjectMapper()).writeValueAsString(bid);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/bid/updateBid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        
        MockMvcBuilders.standaloneSetup(bidController).build().perform(requestBuilder);
    }
}
