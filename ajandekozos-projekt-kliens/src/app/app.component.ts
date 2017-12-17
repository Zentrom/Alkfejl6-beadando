import { Component, OnInit } from '@angular/core';

import { AuthService } from './services/auth.service';
import { BreadcrumbService } from './services/breadcrumb.service';
import 'rxjs/add/operator/take';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [AuthService]
})
export class AppComponent implements OnInit {

  constructor (
    private authService: AuthService,
    private breadCrumbService: BreadcrumbService
  ) {}

  isAdmin(): boolean {
    return localStorage.getItem("isAdmin") == 'true';
  }

  ngOnInit() {
    this.authService.syncLoginStatus();
  }

}