import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SubmitBidModel} from "../submit-bid.model";
import {RestCallService} from "../rest-call.service";

RestCallService

@Component({
  selector: 'app-auction-item',
  templateUrl: './auction-item.component.html',
  styleUrls: ['./auction-item.component.css']
})
export class AuctionItemComponent implements OnInit {

  auctionItems : any;
  submitBid = new SubmitBidModel();
  constructor(private http:HttpClient, private _submitBidService: RestCallService) { }

  ngOnInit(): void {
    this._submitBidService.getAllAuctionItems()
        .subscribe(
            (data)=>this.auctionItems=data);
  }

  onSubmit(auctionItem) {
    this.submitBid.auctionItemId = auctionItem.id;
    this._submitBidService.submitBid(this.submitBid)
        .subscribe(
            data => console.log(data.message, data),
            error => console.error(error.message, error)
        )
    this._submitBidService.getAllAuctionItems()
        .subscribe(
            (data)=>this.auctionItems=data);
  }
}

