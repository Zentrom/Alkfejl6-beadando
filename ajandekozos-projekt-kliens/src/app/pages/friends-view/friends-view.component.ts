import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-friends-view',
  templateUrl: './friends-view.component.html',
  styleUrls: ['./friends-view.component.css']
})
export class FriendsViewComponent implements OnInit {

  adminFriends = ['Jancsika','Kappa','KorsosJuli','MeggyesiKarcsibá'];

  constructor() { }

  ngOnInit() {
  }

  submit(){
    
  }

}
