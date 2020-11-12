import { TestBed } from '@angular/core/testing';

import { RestCallService } from './rest-call.service';

describe('SubmitBidService', () => {
  let service: RestCallService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RestCallService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
