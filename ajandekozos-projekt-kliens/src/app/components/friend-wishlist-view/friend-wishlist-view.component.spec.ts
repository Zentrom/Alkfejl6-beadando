import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FriendWishlistViewComponent } from './friend-wishlist-view.component';

describe('FriendWishlistViewComponent', () => {
  let component: FriendWishlistViewComponent;
  let fixture: ComponentFixture<FriendWishlistViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FriendWishlistViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FriendWishlistViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
