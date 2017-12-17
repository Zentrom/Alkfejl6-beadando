import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchByNameViewComponent } from './search-by-name-view.component';

describe('SearchByNameViewComponent', () => {
  let component: SearchByNameViewComponent;
  let fixture: ComponentFixture<SearchByNameViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchByNameViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchByNameViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
