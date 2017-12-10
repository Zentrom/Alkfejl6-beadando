import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { MatDialog, MatDialogRef } from '@angular/material';
import { filter } from 'rxjs/operators';

import { WishList } from '../../model/wishlist';
import { WishlistService } from '../../services/wishlist.service';
import { EditWishlistDialogComponent } from './edit-wishlist-dialog/edit-wishlist-dialog.component';
import { BreadcrumbService } from '../../services/breadcrumb.service';

@Component({
  selector: 'app-wishlist-view',
  templateUrl: './wishlist-view.component.html',
  styleUrls: ['./wishlist-view.component.css'],
  providers: [AuthService, WishlistService]
})

export class WishlistViewComponent implements OnInit {
  private wishlists: WishList[];
  private editWishlistDialogRef: MatDialogRef<EditWishlistDialogComponent>;

  constructor(
    private router: Router,
    private wishlistService: WishlistService,
    private breadcrumbService: BreadcrumbService,
    private dialog: MatDialog
  ) {} 

  public addList(wishlist: WishList): void {
    this.wishlistService.addWishList(wishlist).subscribe((newWishlist) => {
      this.wishlists.push(newWishlist);
    });
  }

  public removeList(wishlist: WishList): void {  
    this.wishlistService.deleteWishList(wishlist.id).subscribe(() => {
      var index = this.wishlists.indexOf(wishlist);
      this.wishlists.splice(index, 1);    
    });
  }

  public openEditListDialog(wishlist: WishList): void {
    this.editWishlistDialogRef = this.dialog.open(EditWishlistDialogComponent, {
      hasBackdrop: false,
      data: {
        title: wishlist ? wishlist.title : ''
      }
    });

    this.editWishlistDialogRef.afterClosed().pipe(
      filter(title => title))
      .subscribe(title => {
        wishlist.title = title;
        this.wishlistService.updateWishList(wishlist).subscribe((updatedWishlist) => {
        });
    });
  }

  public setBreadcrumbs(listId: number, listTitle: string): void {
    this.breadcrumbService.listId = listId;
    this.breadcrumbService.listTitle = listTitle;
  }

  ngOnInit() {
    this.wishlistService.getWishLists().subscribe((wishlists: WishList[]) => {
      this.wishlists = wishlists;
    });
  }
  
}
  
