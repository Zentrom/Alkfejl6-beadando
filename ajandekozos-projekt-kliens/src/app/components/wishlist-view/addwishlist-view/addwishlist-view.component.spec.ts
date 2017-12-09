import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddwishlistViewComponent } from './addwishlist-view.component';

describe('AddwishlistViewComponent', () => {
  let component: AddwishlistViewComponent;
  let fixture: ComponentFixture<AddwishlistViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddwishlistViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddwishlistViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
