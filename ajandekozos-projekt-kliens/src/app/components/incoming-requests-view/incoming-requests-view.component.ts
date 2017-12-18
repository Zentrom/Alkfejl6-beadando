import { Component, OnInit } from '@angular/core';
import {MatSnackBar} from '@angular/material';

import { UserDTO } from '../../model/userdto';
import { FriendrequestService } from '../../services/friendrequest.service';
import { FriendRequest } from '../../model/friendrequest';

@Component({
  selector: 'app-incoming-requests-view',
  templateUrl: './incoming-requests-view.component.html',
  styleUrls: ['./incoming-requests-view.component.css'],
  providers: [FriendrequestService]
})

export class IncomingRequestsViewComponent implements OnInit {
  private originalRequests: FriendRequest[];
  private filteredRequests: FriendRequest[];
  private firstLook: boolean;

    constructor(
      private friendrequestService: FriendrequestService,
      public snackBar: MatSnackBar
    ) {}

    manageSearch(showall: boolean, firstname: string, lastname: string): void {
      if (showall) {
        this.resetOriginalRequests();
      } else {
        this.filterRequests(firstname, lastname);
      }
    }

    resetOriginalRequests(): void {
      this.filteredRequests = this.originalRequests;
      this.firstLook = false;
    }

    filterRequests(firstname: string, lastname: string): void {
      if ( firstname.trim() || lastname.trim() ) {
        this.resetOriginalRequests();

        if( firstname.trim() && !lastname.trim() ) {
          this.filteredRequests = this.filteredRequests.filter(request => {
            return ( request.requester.firstname.includes(firstname))
          });
        } else if ( !firstname.trim() && lastname.trim() ) {
          this.filteredRequests = this.filteredRequests.filter(request => {
            return ( request.requester.lastname.includes(lastname) )
          });
        } else if ( firstname.trim() && lastname.trim() ) {
          this.filteredRequests = this.filteredRequests.filter(request => {
            return ( request.requester.firstname.includes(firstname) && request.requester.lastname.includes(lastname) )
          });
        } 
      } 
    }

    processRequest(friendrequest: FriendRequest, status: string): void {
      this.friendrequestService.processRequests(friendrequest.id, status).subscribe(() => {
        var index = this.filteredRequests.indexOf(friendrequest);
        this.filteredRequests.splice(index, 1);
      });

      var msg = (status == "1") ? "Accepted" : "Declined";
      this.snackBar.open(msg + " request from: " + friendrequest.requester.firstname + " " + friendrequest.requester.lastname , 'Dismiss', {
        duration: 3000
      });
    }
  
    ngOnInit() {
      this.firstLook = true;
      this.friendrequestService.getFriendRequests().subscribe((friendrequests: FriendRequest[]) => {
        this.originalRequests = friendrequests;
      });
    }
  
  }