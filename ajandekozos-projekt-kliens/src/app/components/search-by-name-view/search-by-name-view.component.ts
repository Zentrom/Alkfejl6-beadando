import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-search-by-name-view',
  templateUrl: './search-by-name-view.component.html',
  styleUrls: ['./search-by-name-view.component.css'],
})
export class SearchByNameViewComponent implements OnInit {
  @Output()
  public searchParams: EventEmitter<any> = new EventEmitter();

  private error: string = '';
  constructor() { }

  public searchUsers(showall: boolean = false, firstname: string = "", lastname: string = ""): void {
    if ( !firstname.trim() && !lastname.trim() && !showall ) {
      this.error = "Please enter something!";
    } else {
      this.error = "";
      firstname = firstname.trim();
      lastname = lastname.trim();
      this.searchParams.emit({showall, firstname, lastname});
    }
  }

  ngOnInit() {
  }

}
