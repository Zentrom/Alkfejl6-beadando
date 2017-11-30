import { Component, OnInit } from '@angular/core';

import { AppComponent } from '../../app.component';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-friends-view',
  templateUrl: './friends-view.component.html',
  styleUrls: ['./friends-view.component.css']
})
export class FriendsViewComponent implements OnInit {

  adminFriends = ['Jancsika','Kappa','KorsosJuli','MeggyesiKarcsibá'];

  authService: AuthService;

  constructor() { }

  ngOnInit() {
    this.authService = AppComponent.authService;
  }

  submit(){
    
  }

}
