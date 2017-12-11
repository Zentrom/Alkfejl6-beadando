import { Component, OnInit } from '@angular/core';

import { UserDTO } from '../../model/userdto';
import { FriendrequestService } from '../../services/friendrequest.service';
import { FriendRequest } from '../../model/friendrequest';

@Component({
  selector: 'app-add-friend-view',
  templateUrl: './add-friend-view.component.html',
  styleUrls: ['./add-friend-view.component.css'],
  providers: [FriendrequestService]
})
export class AddFriendViewComponent implements OnInit {
  private possibleFriends: UserDTO[];

  constructor(
    private friendrequestService: FriendrequestService,
  ) {}

  public sendFriendRequest(requesteeId: number): void {
    console.log(requesteeId);
    this.friendrequestService.createFriendRequest(requesteeId).subscribe((newFriendRequest: FriendRequest) => {
        //this.possibleFriends.filter(user => user.id !== requesteeId);
        var user = this.possibleFriends.find(user => user.id === requesteeId);
        var index = this.possibleFriends.indexOf(user);
        this.possibleFriends.splice(index, 1);  
    });
  }

  public searchUsers(firstname: string, lastname: string): void {
    this.friendrequestService.listPossibleFriends(firstname, lastname).subscribe((filteredUsers: UserDTO[]) => {
      this.possibleFriends = filteredUsers;
      filteredUsers.forEach(user => {
        console.log(user.firstname + " " + user.lastname);
      });
    });
  }

  ngOnInit() {
  }

}
