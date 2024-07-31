package com.example.biddingservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.biddingservice.model.Bid;
import com.example.biddingservice.model.MaxBid;
import com.example.biddingservice.repository.BidRepository;
import com.example.biddingservice.repository.MaxBidRepository;
import com.example.biddingservice.validation.ValidationStrategy;
import jakarta.persistence.EntityNotFoundException;
import jakarta.xml.bind.ValidationException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BidService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class BidServiceTest {
    @MockBean
    private BidRepository bidRepository;

    @Autowired
    private BidService bidService;

    @Autowired
    private List<ValidationStrategy> list;

    @MockBean
    private MaxBidRepository maxBidRepository;

    @MockBean
    private ValidationStrategy validationStrategy;

    @Test
    void testGetAllBids() {
        
        ArrayList<Bid> bidList = new ArrayList<>();
        when(bidRepository.findAll()).thenReturn(bidList);

        
        List<Bid> actualAllBids = bidService.getAllBids();

        
        verify(bidRepository).findAll();
        assertTrue(actualAllBids.isEmpty());
        assertSame(bidList, actualAllBids);
    }

    @Test
    void testGetAllBids2() {
        
        when(bidRepository.findAll()).thenThrow(new EntityNotFoundException("An error occurred"));

        
        assertThrows(EntityNotFoundException.class, () -> bidService.getAllBids());
        verify(bidRepository).findAll();
    }

    @Test
    void testGetBidById() {
        
        Bid bid = new Bid();
        bid.setAmount(10.0d);
        bid.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid.setId("42");
        bid.setProductId("42");
        bid.setUserId("42");
        Optional<Bid> ofResult = Optional.of(bid);
        when(bidRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        
        Optional<Bid> actualBidById = bidService.getBidById("42", "42");

        
        verify(bidRepository).findById(eq("42:42"));
        assertSame(ofResult, actualBidById);
    }

    @Test
    void testGetBidById2() {
        
        when(bidRepository.findById(Mockito.<String>any())).thenThrow(new EntityNotFoundException("An error occurred"));

        
        assertThrows(EntityNotFoundException.class, () -> bidService.getBidById("42", "42"));
        verify(bidRepository).findById(eq("42:42"));
    }

    @Test
    void testPlaceBid() throws ValidationException {
        
        Bid bid = new Bid();
        bid.setAmount(10.0d);
        bid.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid.setId("42");
        bid.setProductId("42");
        bid.setUserId("42");
        when(bidRepository.save(Mockito.<Bid>any())).thenReturn(bid);

        MaxBid maxBid = new MaxBid();
        maxBid.setAmount(10.0d);
        maxBid.setProductId("42");
        maxBid.setUserId("42");

        MaxBid maxBid2 = new MaxBid();
        maxBid2.setAmount(10.0d);
        maxBid2.setProductId("42");
        maxBid2.setUserId("42");
        Optional<MaxBid> ofResult = Optional.of(maxBid2);
        when(maxBidRepository.save(Mockito.<MaxBid>any())).thenReturn(maxBid);
        when(maxBidRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        doNothing().when(validationStrategy).validate(Mockito.<Bid>any());

        Bid bid2 = new Bid();
        bid2.setAmount(10.0d);
        bid2.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid2.setId("42");
        bid2.setProductId("42");
        bid2.setUserId("42");

        
        Bid actualPlaceBidResult = bidService.placeBid(bid2);

        
        verify(validationStrategy).validate(isA(Bid.class));
        verify(maxBidRepository).findById(eq("42"));
        verify(bidRepository).save(isA(Bid.class));
        verify(maxBidRepository).save(isA(MaxBid.class));
        assertEquals("", actualPlaceBidResult.getId());
        assertSame(bid2, actualPlaceBidResult);
    }

    @Test
    void testPlaceBid2() throws ValidationException {
        
        doThrow(new EntityNotFoundException("An error occurred")).when(validationStrategy).validate(Mockito.<Bid>any());

        Bid bid = new Bid();
        bid.setAmount(10.0d);
        bid.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid.setId("42");
        bid.setProductId("42");
        bid.setUserId("42");

        
        assertThrows(EntityNotFoundException.class, () -> bidService.placeBid(bid));
        verify(validationStrategy).validate(isA(Bid.class));
    }

    @Test
    void testPlaceBid3() throws ValidationException {
        
        Bid bid = new Bid();
        bid.setAmount(10.0d);
        bid.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid.setId("42");
        bid.setProductId("42");
        bid.setUserId("42");
        when(bidRepository.save(Mockito.<Bid>any())).thenReturn(bid);

        MaxBid maxBid = new MaxBid();
        maxBid.setAmount(10.0d);
        maxBid.setProductId("42");
        maxBid.setUserId("42");

        MaxBid maxBid2 = new MaxBid();
        maxBid2.setAmount(0.0d);
        maxBid2.setProductId("42");
        maxBid2.setUserId("42");
        Optional<MaxBid> ofResult = Optional.of(maxBid2);
        when(maxBidRepository.save(Mockito.<MaxBid>any())).thenReturn(maxBid);
        when(maxBidRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        doNothing().when(validationStrategy).validate(Mockito.<Bid>any());

        Bid bid2 = new Bid();
        bid2.setAmount(10.0d);
        bid2.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid2.setId("42");
        bid2.setProductId("42");
        bid2.setUserId("42");

        
        Bid actualPlaceBidResult = bidService.placeBid(bid2);

        
        verify(validationStrategy).validate(isA(Bid.class));
        verify(maxBidRepository).findById(eq("42"));
        verify(bidRepository).save(isA(Bid.class));
        verify(maxBidRepository).save(isA(MaxBid.class));
        assertEquals("", actualPlaceBidResult.getId());
        assertSame(bid2, actualPlaceBidResult);
    }

    @Test
    void testPlaceBid4() throws ValidationException {
        
        Bid bid = new Bid();
        bid.setAmount(10.0d);
        bid.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid.setId("42");
        bid.setProductId("42");
        bid.setUserId("42");
        when(bidRepository.save(Mockito.<Bid>any())).thenReturn(bid);

        MaxBid maxBid = new MaxBid();
        maxBid.setAmount(10.0d);
        maxBid.setProductId("42");
        maxBid.setUserId("42");
        when(maxBidRepository.save(Mockito.<MaxBid>any())).thenReturn(maxBid);
        Optional<MaxBid> emptyResult = Optional.empty();
        when(maxBidRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);
        doNothing().when(validationStrategy).validate(Mockito.<Bid>any());

        Bid bid2 = new Bid();
        bid2.setAmount(10.0d);
        bid2.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid2.setId("42");
        bid2.setProductId("42");
        bid2.setUserId("42");

        
        Bid actualPlaceBidResult = bidService.placeBid(bid2);

        
        verify(validationStrategy).validate(isA(Bid.class));
        verify(maxBidRepository).findById(eq("42"));
        verify(bidRepository).save(isA(Bid.class));
        verify(maxBidRepository).save(isA(MaxBid.class));
        assertEquals("", actualPlaceBidResult.getId());
        assertSame(bid2, actualPlaceBidResult);
    }

    @Test
    void testGetBidsByProductId() {
        
        Optional<List<Bid>> ofResult = Optional.of(new ArrayList<>());
        when(bidRepository.findByProductId(Mockito.<String>any())).thenReturn(ofResult);

        
        Optional<List<Bid>> actualBidsByProductId = bidService.getBidsByProductId("42");

        
        verify(bidRepository).findByProductId(eq("42"));
        assertSame(ofResult, actualBidsByProductId);
    }

    @Test
    void testGetBidsByProductId2() {
        
        when(bidRepository.findByProductId(Mockito.<String>any()))
                .thenThrow(new EntityNotFoundException("An error occurred"));

        
        assertThrows(EntityNotFoundException.class, () -> bidService.getBidsByProductId("42"));
        verify(bidRepository).findByProductId(eq("42"));
    }

    @Test
    void testUpdateBid() throws ValidationException {
        
        Bid bid = new Bid();
        bid.setAmount(10.0d);
        bid.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid.setId("42");
        bid.setProductId("42");
        bid.setUserId("42");
        Optional<Bid> ofResult = Optional.of(bid);

        Bid bid2 = new Bid();
        bid2.setAmount(10.0d);
        bid2.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid2.setId("42");
        bid2.setProductId("42");
        bid2.setUserId("42");
        when(bidRepository.save(Mockito.<Bid>any())).thenReturn(bid2);
        when(bidRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        MaxBid maxBid = new MaxBid();
        maxBid.setAmount(10.0d);
        maxBid.setProductId("42");
        maxBid.setUserId("42");

        MaxBid maxBid2 = new MaxBid();
        maxBid2.setAmount(10.0d);
        maxBid2.setProductId("42");
        maxBid2.setUserId("42");
        Optional<MaxBid> ofResult2 = Optional.of(maxBid2);
        when(maxBidRepository.save(Mockito.<MaxBid>any())).thenReturn(maxBid);
        when(maxBidRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        doNothing().when(validationStrategy).validate(Mockito.<Bid>any());

        Bid bidDetails = new Bid();
        bidDetails.setAmount(10.0d);
        bidDetails.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bidDetails.setId("42");
        bidDetails.setProductId("42");
        bidDetails.setUserId("42");

        
        Bid actualUpdateBidResult = bidService.updateBid(bidDetails);

        
        verify(validationStrategy).validate(isA(Bid.class));
        verify(maxBidRepository).findById(eq("42"));
        verify(bidRepository).findById(eq("42:42"));
        verify(bidRepository).save(isA(Bid.class));
        verify(maxBidRepository).save(isA(MaxBid.class));
        assertEquals("", actualUpdateBidResult.getId());
        assertEquals(10.0d, actualUpdateBidResult.getAmount().doubleValue());
        assertSame(bid, actualUpdateBidResult);
    }

    @Test
    void testUpdateBid2() throws ValidationException {
        
        Bid bid = new Bid();
        bid.setAmount(10.0d);
        bid.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid.setId("42");
        bid.setProductId("42");
        bid.setUserId("42");
        Optional<Bid> ofResult = Optional.of(bid);
        when(bidRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        MaxBid maxBid = new MaxBid();
        maxBid.setAmount(10.0d);
        maxBid.setProductId("42");
        maxBid.setUserId("42");
        Optional<MaxBid> ofResult2 = Optional.of(maxBid);
        when(maxBidRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        doThrow(new EntityNotFoundException("An error occurred")).when(validationStrategy).validate(Mockito.<Bid>any());

        Bid bidDetails = new Bid();
        bidDetails.setAmount(10.0d);
        bidDetails.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bidDetails.setId("42");
        bidDetails.setProductId("42");
        bidDetails.setUserId("42");

        
        assertThrows(EntityNotFoundException.class, () -> bidService.updateBid(bidDetails));
        verify(validationStrategy).validate(isA(Bid.class));
        verify(bidRepository).findById(eq("42:42"));
    }

    @Test
    void testUpdateBid3() throws ValidationException {
        
        Optional<Bid> emptyResult = Optional.empty();
        when(bidRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        MaxBid maxBid = new MaxBid();
        maxBid.setAmount(10.0d);
        maxBid.setProductId("42");
        maxBid.setUserId("42");
        Optional<MaxBid> ofResult = Optional.of(maxBid);
        when(maxBidRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Bid bidDetails = new Bid();
        bidDetails.setAmount(10.0d);
        bidDetails.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bidDetails.setId("42");
        bidDetails.setProductId("42");
        bidDetails.setUserId("42");

        
        assertThrows(EntityNotFoundException.class, () -> bidService.updateBid(bidDetails));
        verify(bidRepository).findById(eq("42:42"));
    }

    @Test
    void testUpdateBid4() throws ValidationException {
        
        Bid bid = new Bid();
        bid.setAmount(10.0d);
        bid.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid.setId("42");
        bid.setProductId("42");
        bid.setUserId("42");
        Optional<Bid> ofResult = Optional.of(bid);

        Bid bid2 = new Bid();
        bid2.setAmount(10.0d);
        bid2.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid2.setId("42");
        bid2.setProductId("42");
        bid2.setUserId("42");
        when(bidRepository.save(Mockito.<Bid>any())).thenReturn(bid2);
        when(bidRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        MaxBid maxBid = new MaxBid();
        maxBid.setAmount(10.0d);
        maxBid.setProductId("42");
        maxBid.setUserId("42");

        MaxBid maxBid2 = new MaxBid();
        maxBid2.setAmount(0.0d);
        maxBid2.setProductId("42");
        maxBid2.setUserId("42");
        Optional<MaxBid> ofResult2 = Optional.of(maxBid2);
        when(maxBidRepository.save(Mockito.<MaxBid>any())).thenReturn(maxBid);
        when(maxBidRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        doNothing().when(validationStrategy).validate(Mockito.<Bid>any());

        Bid bidDetails = new Bid();
        bidDetails.setAmount(10.0d);
        bidDetails.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bidDetails.setId("42");
        bidDetails.setProductId("42");
        bidDetails.setUserId("42");

        
        Bid actualUpdateBidResult = bidService.updateBid(bidDetails);

        
        verify(validationStrategy).validate(isA(Bid.class));
        verify(maxBidRepository).findById(eq("42"));
        verify(bidRepository).findById(eq("42:42"));
        verify(bidRepository).save(isA(Bid.class));
        verify(maxBidRepository).save(isA(MaxBid.class));
        assertEquals("", actualUpdateBidResult.getId());
        assertEquals(10.0d, actualUpdateBidResult.getAmount().doubleValue());
        assertSame(bid, actualUpdateBidResult);
    }

    @Test
    void testUpdateBid5() throws ValidationException {
        
        Bid bid = new Bid();
        bid.setAmount(10.0d);
        bid.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid.setId("42");
        bid.setProductId("42");
        bid.setUserId("42");
        Optional<Bid> ofResult = Optional.of(bid);

        Bid bid2 = new Bid();
        bid2.setAmount(10.0d);
        bid2.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bid2.setId("42");
        bid2.setProductId("42");
        bid2.setUserId("42");
        when(bidRepository.save(Mockito.<Bid>any())).thenReturn(bid2);
        when(bidRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        MaxBid maxBid = new MaxBid();
        maxBid.setAmount(10.0d);
        maxBid.setProductId("42");
        maxBid.setUserId("42");
        when(maxBidRepository.save(Mockito.<MaxBid>any())).thenReturn(maxBid);
        Optional<MaxBid> emptyResult = Optional.empty();
        when(maxBidRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);
        doNothing().when(validationStrategy).validate(Mockito.<Bid>any());

        Bid bidDetails = new Bid();
        bidDetails.setAmount(10.0d);
        bidDetails.setBidTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        bidDetails.setId("42");
        bidDetails.setProductId("42");
        bidDetails.setUserId("42");

        
        Bid actualUpdateBidResult = bidService.updateBid(bidDetails);

        
        verify(validationStrategy).validate(isA(Bid.class));
        verify(maxBidRepository).findById(eq("42"));
        verify(bidRepository).findById(eq("42:42"));
        verify(bidRepository).save(isA(Bid.class));
        verify(maxBidRepository).save(isA(MaxBid.class));
        assertEquals("", actualUpdateBidResult.getId());
        assertEquals(10.0d, actualUpdateBidResult.getAmount().doubleValue());
        assertSame(bid, actualUpdateBidResult);
    }

    @Test
    void testGetMaxBid() {
        
        MaxBid maxBid = new MaxBid();
        maxBid.setAmount(10.0d);
        maxBid.setProductId("42");
        maxBid.setUserId("42");
        Optional<MaxBid> ofResult = Optional.of(maxBid);
        when(maxBidRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        
        MaxBid actualMaxBid = bidService.getMaxBid("42");

        
        verify(maxBidRepository).findById(eq("42"));
        assertSame(maxBid, actualMaxBid);
    }

    @Test
    void testGetMaxBid2() {
        
        Optional<MaxBid> emptyResult = Optional.empty();
        when(maxBidRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        
        assertThrows(EntityNotFoundException.class, () -> bidService.getMaxBid("42"));
        verify(maxBidRepository).findById(eq("42"));
    }

    @Test
    void testGetMaxBid3() {
        
        when(maxBidRepository.findById(Mockito.<String>any())).thenThrow(new EntityNotFoundException("An error occurred"));
        
        assertThrows(EntityNotFoundException.class, () -> bidService.getMaxBid("42"));
        verify(maxBidRepository).findById(eq("42"));
    }
}
