import { Component, OnInit } from '@angular/core';

import { AuthService } from '../../services/auth.service';
import { AppComponent } from '../../app.component';

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
    this.authService = AppComponent.authService;
  }

}
