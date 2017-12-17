import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

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
    private router: Router
  ) {}

  ngOnInit() {
  }

  private tryLogin(username: string, password: string) {
    this.authService.login(username, password).subscribe((success: boolean) => {
      if (success) {
        this.router.navigate(['/']);
      } else {
        this.error = 'Error: Wrong username or password!';
      }
    });
  }

}