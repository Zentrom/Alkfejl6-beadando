import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { BreadcrumbService } from '../../services/breadcrumb.service';
import {MatSnackBar} from '@angular/material';

import { User } from '../../model/user';

@Component({
  selector: 'app-login-view',
  templateUrl: './login-view.component.html',
  styleUrls: ['./login-view.component.css'],
  providers: []
})
export class LoginViewComponent implements OnInit {
  private error: string = '';

  constructor(
    private authService: AuthService,
    private breadCrumbService: BreadcrumbService,
    private router: Router,
    public snackBar: MatSnackBar
  ) {}

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }


  ngOnInit() {
  }

  private tryLogin(username: string, password: string) {
    this.authService.login(username, password).subscribe((success: boolean) => {
      if (success) {
        this.router.navigate(['/']);
        this.breadCrumbService.userName=username;
        this.openSnackBar("Logged in as:",username);
      } else {
        this.error = 'Error: Wrong username or password!';
      }
    });
  }

}