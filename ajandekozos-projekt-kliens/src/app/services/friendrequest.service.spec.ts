import { TestBed, inject } from '@angular/core/testing';

import { FriendrequestService } from './friendrequest.service';

describe('FriendrequestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FriendrequestService]
    });
  });

  it('should be created', inject([FriendrequestService], (service: FriendrequestService) => {
    expect(service).toBeTruthy();
  }));
});
