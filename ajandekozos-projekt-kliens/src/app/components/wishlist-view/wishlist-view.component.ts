import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

import { WishList } from '../../model/wishlist';
import { WishlistService } from '../../services/wishlist.service';

@Component({
  selector: 'app-wishlist-view',
  templateUrl: './wishlist-view.component.html',
  styleUrls: ['./wishlist-view.component.css'],
  providers: [AuthService, WishlistService]
})

export class WishlistViewComponent implements OnInit {
  private wishlists: WishList[];

  constructor(
    private router: Router,
    private wishlistService: WishlistService
  ) {} 

  public addList(wishlist: WishList): void {
    this.wishlistService.addWishList(wishlist).subscribe(() => {
      this.wishlistService.getWishLists().subscribe((wishlists: WishList[]) => {
        this.wishlists = wishlists;
      });
    });
  }

  public removeList(wishlist: WishList): void {  
    this.wishlistService.deleteWishList(wishlist.id).subscribe(() => {
      this.wishlistService.getWishLists().subscribe((wishlists: WishList[]) => {
        this.wishlists = wishlists;
      });
    })
  }

  ngOnInit() {
    this.wishlistService.getWishLists().subscribe((wishlists: WishList[]) => {
      this.wishlists = wishlists;
    });
  }
  
}
  
