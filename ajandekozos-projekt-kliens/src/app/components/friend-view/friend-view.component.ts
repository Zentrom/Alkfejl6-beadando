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

  constructor(
    private friendService: FriendService,
  ) {}

  public searchUsers(firstname: string, lastname: string): void {
    this.friendService.listFriends().subscribe((filteredUsers: UserDTO[]) => {
      this.possibleFriends = filteredUsers;
      filteredUsers.forEach(user => {
        console.log(user.firstname + " " + user.lastname);
      });
    });
  }

  public removeFriend(user: UserDTO): void {  
    this.friendService.deleteFriendOrUser(user.id).subscribe(() => {
      //var index = this.user.indexOf(user);
      //this.user.splice(index, 1);    
    });
  }

  ngOnInit() {
  }

}
