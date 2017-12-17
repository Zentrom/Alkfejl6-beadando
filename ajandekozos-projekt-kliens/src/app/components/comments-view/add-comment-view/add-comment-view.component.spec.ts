import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCommentViewComponent } from './add-comment-view.component';

describe('AddCommentViewComponent', () => {
  let component: AddCommentViewComponent;
  let fixture: ComponentFixture<AddCommentViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCommentViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCommentViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
