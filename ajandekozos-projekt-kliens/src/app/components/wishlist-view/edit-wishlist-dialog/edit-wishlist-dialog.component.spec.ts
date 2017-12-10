import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditWishlistDialogComponent } from './edit-wishlist-dialog.component';

describe('EditWishlistDialogComponent', () => {
  let component: EditWishlistDialogComponent;
  let fixture: ComponentFixture<EditWishlistDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditWishlistDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditWishlistDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
