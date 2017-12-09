import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import {Location} from '@angular/common';

import { User } from '../../model/user';
import { Present } from '../../model/present';
import { PresentService } from '../../services/present.service';

@Component({
  selector: 'app-presents-view',
  templateUrl: './presents-view.component.html',
  styleUrls: ['./presents-view.component.css'],
  providers: [AuthService, PresentService]
})

export class PresentsViewComponent implements OnInit {
  private presents: Present[];
  public listId: number;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private presentService: PresentService,
    private location: Location
  ) {} 

  public moveBack(): void {
    this.location.back();
  }

  public addPresent(present: Present): void {
    this.presentService.addPresent(this.listId, present).subscribe(() => {
      this.presentService.getPresents(this.listId).subscribe((presents: Present[]) => {
        this.presents = presents;
      });
    });
  }

  ngOnInit() {
    this.listId = parseInt(this.activatedRoute.snapshot.paramMap.get('listId'));

    this.presentService.getPresents(this.listId).subscribe((presents: Present[]) => {
      this.presents = presents;
    });
  }
    
}
