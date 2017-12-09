import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddpresentViewComponent } from './addpresent-view.component';

describe('AddpresentViewComponent', () => {
  let component: AddpresentViewComponent;
  let fixture: ComponentFixture<AddpresentViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddpresentViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddpresentViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
