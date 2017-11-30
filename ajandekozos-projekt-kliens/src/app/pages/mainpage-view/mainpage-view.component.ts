import { Component, OnInit } from '@angular/core';

import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-mainpage-view',
  templateUrl: './mainpage-view.component.html',
  styleUrls: ['./mainpage-view.component.css'],
  providers: [AuthService]
})
export class MainpageViewComponent implements OnInit {

  constructor() { }

  authService: AuthService;


  ngOnInit() {
    this.authService = new AuthService(false);
  }

}
