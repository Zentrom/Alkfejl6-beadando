import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FriendPresentsViewComponent } from './friend-presents-view.component';

describe('FriendPresentsViewComponent', () => {
  let component: FriendPresentsViewComponent;
  let fixture: ComponentFixture<FriendPresentsViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FriendPresentsViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FriendPresentsViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
