import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { User } from '../../model/user';

@Component({
  selector: 'app-register-view',
  templateUrl: './register-view.component.html',
  styleUrls: ['./register-view.component.css'],
  providers: []
})
export class RegisterViewComponent implements OnInit {
  private error: string = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
  }

  private tryRegister(username: string, password: string, firstname: string, lastname: string, email: string) {
    this.authService.register(username, password, firstname, lastname, email).subscribe((success: boolean) => {
      if (success) {
        this.router.navigate(['/']);
      } else {
        this.error = 'Error: something went wrong!';
      }
    });
  }
}
