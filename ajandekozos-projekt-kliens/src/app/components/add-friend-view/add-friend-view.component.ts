import { Component, OnInit } from '@angular/core';

import { UserDTO } from '../../model/userdto';
import { FriendrequestService } from '../../services/friendrequest.service';

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
