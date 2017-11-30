import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';

import { AuthService } from '../../services/auth.service';
import { AppComponent } from '../../app.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  });

  authService: AuthService;

  ngOnInit() {
    this.authService = AppComponent.authService;
  }

  submit() {
    if(this.loginForm.get('username').value == "admin" && this.loginForm.get('password').value == "admin"){
      //console.log(AuthService.isLoggedIn);
      AppComponent.authService.isLoggedIn = true;
    }

    //console.log(AuthService.isLoggedIn);
    /*this.loginService.login(new User(this.username.value, this.password.value))
      .subscribe(
        res => console.log(res),
        err => console.log(err))*/
  }

  logout(){
    AppComponent.authService.isLoggedIn = false;
  }

  get username(): AbstractControl {
    return this.loginForm.get('username');
  }

  get password(): AbstractControl {
    return this.loginForm.get('password');
  }
}
