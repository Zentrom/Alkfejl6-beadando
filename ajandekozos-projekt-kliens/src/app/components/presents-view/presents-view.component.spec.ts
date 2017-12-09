import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PresentsViewComponent } from './presents-view.component';

describe('PresentsViewComponent', () => {
  let component: PresentsViewComponent;
  let fixture: ComponentFixture<PresentsViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PresentsViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PresentsViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
