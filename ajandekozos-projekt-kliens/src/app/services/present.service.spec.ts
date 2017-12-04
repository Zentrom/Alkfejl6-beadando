import { TestBed, inject } from '@angular/core/testing';

import { PresentService } from './present.service';

describe('PresentService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PresentService]
    });
  });

  it('should be created', inject([PresentService], (service: PresentService) => {
    expect(service).toBeTruthy();
  }));
});
