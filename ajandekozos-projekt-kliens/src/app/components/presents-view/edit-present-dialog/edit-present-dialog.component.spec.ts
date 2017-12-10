import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditPresentDialogComponent } from './edit-present-dialog.component';

describe('EditPresentDialogComponent', () => {
  let component: EditPresentDialogComponent;
  let fixture: ComponentFixture<EditPresentDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditPresentDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditPresentDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
