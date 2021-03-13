package com.David.AuctionApp.controller;

import com.David.AuctionApp.model.Auctionitem;
import com.David.AuctionApp.model.request.SubmitAuctionItemRequest;
import com.David.AuctionApp.model.response.CreateAuctionItemResponse;
import com.David.AuctionApp.repository.AuctionitemRepository;
import com.David.AuctionApp.service.AuctionItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auctionItems")
@CrossOrigin(origins = "*")
public class AuctionController {
    @Autowired
    AuctionitemRepository auctionitemRepository;
    @Autowired
    AuctionItemService auctionItemService;

    @PostMapping(value = "")
    public ResponseEntity<CreateAuctionItemResponse> createAuctionItem(@RequestBody SubmitAuctionItemRequest submitAuctionItemRequest) {
        CreateAuctionItemResponse auctionItemResponse = auctionItemService.saveAuctionItem(submitAuctionItemRequest);
        return new ResponseEntity<>(auctionItemResponse, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Auctionitem>> getAllAuctionItems() {
        List<Auctionitem> allAuctionItems = auctionitemRepository.findAll();
        return new ResponseEntity<>(allAuctionItems, HttpStatus.OK);
    }

    @GetMapping(value = "/{auctionItemId}")
    public ResponseEntity<Auctionitem> getAuctionItem(@PathVariable("auctionItemId") long auctionItemId) {
        Optional<Auctionitem> singleAuctionItem = auctionitemRepository.findById(auctionItemId);
        return new ResponseEntity<>(singleAuctionItem.get(), HttpStatus.OK);
    }
}
