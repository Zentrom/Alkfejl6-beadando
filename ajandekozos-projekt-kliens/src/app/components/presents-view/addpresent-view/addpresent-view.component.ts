import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { Present } from '../../../model/present';

@Component({
  selector: 'app-addpresent-view',
  templateUrl: './addpresent-view.component.html',
  styleUrls: ['./addpresent-view.component.css']
})
export class AddpresentViewComponent implements OnInit {
  @Output()
  public createPresent: EventEmitter<Present> = new EventEmitter();

  @Input()
  public isHidden: boolean;

  private nameError: string = "";
  private priceError: string = "";  

  public clickButton(name: string, price: number, link: string): void {
    if (name && price && name.trim().length > 0 && price >= 1) {
      this.nameError = "";
      this.priceError = "";
      if (link && link.trim().length === 0) {
        link = null;
      }
      this.createPresent.emit(new Present(name, price, link, this.isHidden));
    } else {
      if (price <= 0) {
        this.priceError = "Price must be a positive number!";
      } else {
        this.priceError = "";
      }
      if (!name || name.trim().length === 0) {
        this.nameError = "Please enter something!";     
      } else {
        this.nameError = "";
      }
    }
  } 

  constructor() { }

  ngOnInit() {
  }

}
