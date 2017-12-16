import { Component, OnInit } from '@angular/core';

import { UserDTO } from '../../model/userdto';
import { FriendService } from '../../services/friend.service';
import { WishlistService } from '../../services/wishlist.service';
import { WishList } from '../../model/wishlist';

@Component({
  selector: 'app-friend-view',
  templateUrl: './friend-view.component.html',
  styleUrls: ['./friend-view.component.css'],
  providers: [FriendService,WishlistService]
})
export class FriendViewComponent implements OnInit {
  private wishlists: WishList[];
  private possibleFriends: UserDTO[];
  private error : string;

  constructor(
    private friendService: FriendService,
    private wishlistService: WishlistService
  ) {}

  /*public searchUsers(firstname: string, lastname: string): void {
    this.friendService.listFriends().subscribe((filteredUsers: UserDTO[]) => {
      this.possibleFriends = filteredUsers;
      filteredUsers.forEach(user => {
        console.log(user.firstname + " " + user.lastname);
      });
    });
  }*/

  public searchUsers(showall: boolean = false, firstname: string = "", lastname: string = ""): void {
    if ( !firstname.trim() && !lastname.trim() && !showall ) {
      this.error = "Please enter something!";
    } else {
      this.error = "";
      this.friendService.listFriends(firstname, lastname).subscribe((filteredUsers: UserDTO[]) => {
        this.possibleFriends = filteredUsers;
        //filteredUsers.forEach(user => {
        //  console.log(user.firstname + " " + user.lastname);
        //});
      });
    }
  }

  public removeFriend(user: UserDTO): void {  
    this.friendService.deleteFriend(user.id).subscribe(() => {
      var index = this.possibleFriends.indexOf(user);
      this.possibleFriends.splice(index, 1);  
    });
  }

  public friendWishlist(userId: number): void {
      this.wishlistService.listFriendsLists(userId).subscribe((friendsLists: WishList[]) =>{
        //this.wishlists= friendsLists; NEMJOMÁSCOMPONENTKELL
      });
  }

  ngOnInit() {
  }

}
