package com.David.AuctionApp.controller;

import com.David.AuctionApp.exception.InvalidBidAmountException;
import com.David.AuctionApp.exception.OutbidException;
import com.David.AuctionApp.exception.ReserveNotMetException;
import com.David.AuctionApp.model.request.SubmitBidRequest;
import com.David.AuctionApp.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bid")
public class BidController {
    @Autowired
    BidService bidService;

    @PostMapping(value = "")
    public String submitBid(@RequestBody SubmitBidRequest submitBidRequest) throws ReserveNotMetException,
            InvalidBidAmountException, OutbidException {
        String response = bidService.submitBid(submitBidRequest);
        return response;
    }

    @ExceptionHandler(ReserveNotMetException.class)
    public ResponseEntity<String> handleError(ReserveNotMetException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(OutbidException.class)
    public ResponseEntity<String> handleError(OutbidException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(InvalidBidAmountException.class)
    public ResponseEntity<String> handleError(InvalidBidAmountException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.OK);
    }
}
