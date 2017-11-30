import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material';

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

  constructor(public snackBar: MatSnackBar) {}

  authService: AuthService;

  //snackBar : MatSnackBar;

  ngOnInit() {
    this.authService = AppComponent.authService;
    //this.snackBar = new MatSnackBar();
  }

  submit() {
    if(this.loginForm.get('username').value == "admin" && this.loginForm.get('password').value == "admin"){
      //console.log(AuthService.isLoggedIn);
      AppComponent.authService.isLoggedIn = true;
      this.snackBar.open("Successful login as:","admin",{duration: 2000});
    }

    //console.log(AuthService.isLoggedIn);
    /*this.loginService.login(new User(this.username.value, this.password.value))
      .subscribe(
        res => console.log(res),
        err => console.log(err))*/
  }

  logout(){
    AppComponent.authService.isLoggedIn = false;
    this.snackBar.open("You logged out","",{duration: 2000});
  }

  get username(): AbstractControl {
    return this.loginForm.get('username');
  }

  get password(): AbstractControl {
    return this.loginForm.get('password');
  }
}
