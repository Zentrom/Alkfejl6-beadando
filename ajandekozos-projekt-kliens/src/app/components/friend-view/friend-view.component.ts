import { Component, OnInit } from '@angular/core';

import { UserDTO } from '../../model/userdto';
import { FriendService } from '../../services/friend.service';

@Component({
  selector: 'app-friend-view',
  templateUrl: './friend-view.component.html',
  styleUrls: ['./friend-view.component.css'],
  providers: [FriendService]
})
export class FriendViewComponent implements OnInit {
  private possibleFriends: UserDTO[];
  private error : string;

  constructor(
    private friendService: FriendService,
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

  ngOnInit() {
  }

}
