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

  public clickButton(name: string, price: number, link: string): void {
    const isHidden = false;
    this.createPresent.emit(new Present(name, price, link, isHidden));
  }

  constructor() { }

  ngOnInit() {
  }

}
