import { Component, OnInit } from '@angular/core';
import {MatSnackBar} from '@angular/material';

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
    public snackBar: MatSnackBar
  ) {}

  public sendFriendRequest(requestee: UserDTO): void {
    this.friendrequestService.createFriendRequest(requestee.id).subscribe((newFriendRequest: FriendRequest) => {
        var index = this.possibleFriends.indexOf(requestee);
        this.possibleFriends.splice(index, 1);  
    });

    this.snackBar.open('Request sent to: ' + requestee.firstname + ' ' + requestee.lastname, 'Dismiss', {
      duration: 3000
    });
  }

  public searchUsers(showall: boolean = false, firstname: string = "", lastname: string = ""): void {
    this.friendrequestService.listPossibleFriends(firstname, lastname).subscribe((filteredUsers: UserDTO[]) => {
      this.possibleFriends = filteredUsers;
    });
  }

  ngOnInit() {
  }

}
