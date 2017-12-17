import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { MatDialog, MatDialogRef } from '@angular/material';
import { filter } from 'rxjs/operators';

import { WishList } from '../../model/wishlist';
import { WishlistService } from '../../services/wishlist.service';
//import { EditWishlistDialogComponent } from './edit-wishlist-dialog/edit-wishlist-dialog.component';
import { BreadcrumbService } from '../../services/breadcrumb.service';

@Component({
  selector: 'app-friend-wishlist-view',
  templateUrl: './friend-wishlist-view.component.html',
  styleUrls: ['./friend-wishlist-view.component.css'],
  providers: [WishlistService]
  //encapsulation: ViewEncapsulation.None
})

export class FriendWishlistViewComponent implements OnInit {
  private wishlists: WishList[];
  //private editWishlistDialogRef: MatDialogRef<EditWishlistDialogComponent>;
  private isDialogOpen: boolean;
  private userId: number;

  constructor(
    private router: Router,
    private wishlistService: WishlistService,
    private activatedRoute: ActivatedRoute,
    private breadcrumbService: BreadcrumbService,
    private dialog: MatDialog
  ) {} 

 /* public addList(wishlist: WishList): void {
    this.wishlistService.addWishList(wishlist).subscribe((newWishlist) => {
      this.wishlists.push(newWishlist);
    });
  }

  public removeList(wishlist: WishList): void {  
    this.wishlistService.deleteWishList(wishlist.id).subscribe(() => {
      var index = this.wishlists.indexOf(wishlist);
      this.wishlists.splice(index, 1);    
    });
  }*/

  /*public openEditListDialog(wishlist: WishList): void {
    if (!this.isDialogOpen) {
      this.editWishlistDialogRef = this.dialog.open(EditWishlistDialogComponent, {
        data: {
          title: wishlist ? wishlist.title : ''
        }
      });
      this.isDialogOpen = true;
    }
  

    this.editWishlistDialogRef.afterClosed().pipe(
      filter(result => result && result.title.trim()))
      .subscribe(result => {
        wishlist.title = result.title;
        this.wishlistService.updateWishList(wishlist).subscribe((updatedWishlist) => {
        });
    });
    this.isDialogOpen = false;
  }
*/
  public setBreadcrumbs(listTitle: string): void {
    this.breadcrumbService.listTitle = listTitle;
  }

  ngOnInit() {
    this.userId = parseInt(this.activatedRoute.snapshot.paramMap.get('userId'));
    
    this.wishlistService.listFriendsLists(this.userId).subscribe((friendsLists: WishList[]) =>{
      this.wishlists = friendsLists;
    });    
  }
  
}
  

