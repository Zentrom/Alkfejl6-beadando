import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Location } from '@angular/common';
import { MatDialog, MatDialogRef, MatSnackBar } from '@angular/material';
import { filter } from 'rxjs/operators';

import { User } from '../../model/user';
import { Present } from '../../model/present';
import { PresentService } from '../../services/present.service';
import { EditPresentDialogComponent } from '../presents-view/edit-present-dialog/edit-present-dialog.component';
import { BreadcrumbService } from '../../services/breadcrumb.service';
import { UserDTO } from '../../model/userdto';

@Component({
  selector: 'app-friend-presents-view',
  templateUrl: './friend-presents-view.component.html',
  styleUrls: ['./friend-presents-view.component.css'],
  providers: [PresentService]
})

export class FriendPresentsViewComponent implements OnInit {
  private presents: Present[];
  private friendListId: number;
  private friendId: number;
  private editPresentDialogRef: MatDialogRef<EditPresentDialogComponent>;
  private isDialogOpen: boolean;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private presentService: PresentService,
    private authService: AuthService,
    private breadCrumbService: BreadcrumbService,
    private location: Location,
    private dialog: MatDialog,
    public snackBar: MatSnackBar
  ) {} 
  
  public goToUrl(url: string): void {
    if (!/^http[s]?:\/\//.test(url)) {
      url = 'http://' + url;
    }
    window.open(url, "_blank");
  }

  public openEditPresentDialog(present: Present): void {
    if (!this.isDialogOpen) {
      this.editPresentDialogRef = this.dialog.open(EditPresentDialogComponent, {
        data: {
          name: present ? present.name : '',
          price: present ? present.price: '',
          link: present.link? present.link: '',
        }
      });
      this.isDialogOpen = true;
    }

    this.editPresentDialogRef.afterClosed().pipe(
      filter(result => result && result.name.trim() && result.price > 0))
      .subscribe(result => {
        present.name = result.name;
        present.price = result.price;
        present.link = (result.link.trim().length === 0) ? null : result.link;
        this.presentService.updatePresent(this.friendListId, present).subscribe((updatedPresent) => {
        });
    });
    this.isDialogOpen = false;
  }

  public addPresent(present: Present): void {
    this.presentService.addPresent(this.friendListId, present).subscribe((newHiddenPresent: Present) => {
      this.presents.push(newHiddenPresent);
    })
  }

  public removePresent(present: Present): void {  
    this.presentService.deletePresent(this.friendListId, present.id).subscribe(() => {
      var index = this.presents.indexOf(present);
      this.presents.splice(index, 1);   
    })
  }

  public setBuyer(present: Present) {
    if (present.user === null) {
      var buyer: UserDTO = new UserDTO(this.authService.getUser().id, this.authService.getUser().firstname, this.authService.getUser().lastname);
      present.user = buyer;
      this.presentService.updateFriendPresent(this.friendId, this.friendListId, present).subscribe((updatedPresent: Present) => {
      });
      this.snackBar.open("You bought: " + present.name + " for your friend", "Dismiss", {
        duration: 3000
      })
    } else {
      present.user = null;
      this.presentService.updateFriendPresent(this.friendId, this.friendListId, present).subscribe((updatedPresent: Present) => {
      });
    }
  }

  public setBuyerNull(present: Present) {
    if (present.user !== null) {
      present.user = null;
      this.presentService.updateFriendPresent(this.friendId, this.friendListId, present).subscribe((updatedPresent: Present) => {
      });
      this.snackBar.open(present.name + "'s buyer set to none", "Dismiss", {
        duration: 3000
      })
    }
  }

  public checkBuyer(present: Present): boolean {
    return present.user && !(present.user.id === this.authService.getUser().id);
  }

  public setBreadcrumbs(presentName: string): void {
    this.breadCrumbService.presentName = presentName;
  }

  ngOnInit() {
    if (this.authService.isUserAdmin()) {
      this.friendListId = parseInt(this.activatedRoute.snapshot.paramMap.get('listId'));
      this.friendId = parseInt(this.activatedRoute.snapshot.paramMap.get('userId'));
    } else {
      this.friendListId = parseInt(this.activatedRoute.snapshot.paramMap.get('friendListId'));
      this.friendId = parseInt(this.activatedRoute.snapshot.paramMap.get('friendId'));
    }


    this.presentService.listPresentsOfFriendsOrUsersList(this.friendId, this.friendListId).subscribe((friendsPresents: Present[]) => {
      this.presents = friendsPresents;
    });
  }
    
}
