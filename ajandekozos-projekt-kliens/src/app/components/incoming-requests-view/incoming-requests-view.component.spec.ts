import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncomingRequestsViewComponent } from './incoming-requests-view.component';

describe('IncomingRequestsViewComponent', () => {
  let component: IncomingRequestsViewComponent;
  let fixture: ComponentFixture<IncomingRequestsViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncomingRequestsViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncomingRequestsViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
