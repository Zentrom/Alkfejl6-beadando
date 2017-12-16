import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { WishList } from '../../../model/wishlist';

@Component({
  selector: 'app-addwishlist-view',
  templateUrl: './addwishlist-view.component.html',
  styleUrls: ['./addwishlist-view.component.css']
})
export class AddwishlistViewComponent implements OnInit {
  @Output()
  public createList: EventEmitter<WishList> = new EventEmitter();

  private error: string = '';

  public clickButton(title: string): void {
    if (title.trim().length > 0) {
      this.error = '';
      this.createList.emit(new WishList(title));
    } else {
      this.error = "Please enter something!"
    }

  }

  constructor() { }

  ngOnInit() {
  }

}