import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFriendViewComponent } from './add-friend-view.component';

describe('AddFriendViewComponent', () => {
  let component: AddFriendViewComponent;
  let fixture: ComponentFixture<AddFriendViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddFriendViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddFriendViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
