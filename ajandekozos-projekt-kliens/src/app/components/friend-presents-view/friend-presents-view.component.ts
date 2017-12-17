import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Location } from '@angular/common';
import { MatDialog, MatDialogRef } from '@angular/material';
import { filter } from 'rxjs/operators';

import { User } from '../../model/user';
import { Present } from '../../model/present';
import { PresentService } from '../../services/present.service';
//import { EditPresentDialogComponent } from './edit-present-dialog/edit-present-dialog.component';
import { BreadcrumbService } from '../../services/breadcrumb.service';

@Component({
  selector: 'app-friend-presents-view',
  templateUrl: './friend-presents-view.component.html',
  styleUrls: ['./friend-presents-view.component.css'],
  providers: [PresentService]
  //encapsulation: ViewEncapsulation.None
})

export class FriendPresentsViewComponent implements OnInit {
  private presents: Present[];
  private listId: number;
  private friendId: number;
  //private editPresentDialogRef: MatDialogRef<EditPresentDialogComponent>;
  private isDialogOpen: boolean;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private presentService: PresentService,
    private breadCrumbService: BreadcrumbService,
    private location: Location,
    private dialog: MatDialog
  ) {} 



  public getButtonClass(present: Present): string {
    if (present.link) {
      return "btn btn-primary innerbtn";
    }

    return "btn btn-danger innerbtn";
  }
  
  public goToUrl(url: string): void {
    if (!/^http[s]?:\/\//.test(url)) {
      url = 'http://' + url;
    }
    window.open(url, "_blank");
  }

  /*public openEditPresentDialog(present: Present): void {
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
        present.link = result.link;
        this.presentService.updatePresent(this.listId, present).subscribe((updatedPresent) => {
        });
    });
    this.isDialogOpen = false;

    
  }*/

  public addPresent(present: Present): void {
    this.presentService.addPresent(this.listId, present).subscribe((newPresent) => {
      this.presents.push(newPresent);
    });
  }

  public removePresent(present: Present): void {  
    this.presentService.deletePresent(this.listId, present.id).subscribe(() => {
      var index = this.presents.indexOf(present);
      this.presents.splice(index, 1);   
    })
  }

  public setBreadcrumbs(presentId: number,presentTitle: string): void {
    this.breadCrumbService.presentId = presentId;
    this.breadCrumbService.presentName = presentTitle;
  }

  ngOnInit() {
    this.listId = parseInt(this.activatedRoute.snapshot.paramMap.get('friendListId'));
    this.friendId = parseInt(this.activatedRoute.snapshot.paramMap.get('friendId'));
    //console.log(this.listId);
    this.presentService.getPresents(this.listId).subscribe((presents: Present[]) => {
      this.presents = presents;
    });
  }
    
}
