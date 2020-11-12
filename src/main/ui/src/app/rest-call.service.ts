import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {SubmitBidModel} from "./submit-bid.model";

@Injectable({
  providedIn: 'root'
})
export class RestCallService {

  constructor(private _http: HttpClient) { }

  submitBid(submitBidRequest: SubmitBidModel) {
    return this._http.post<any>("http://localhost:8080/bid", submitBidRequest);
  }
  getAllAuctionItems() {
    return this._http.get<any>("http://localhost:8080/auctionItems");
  }
}
