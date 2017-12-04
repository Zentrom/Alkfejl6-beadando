import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';

import { User,Role } from '../../model/User';
import { AppComponent } from '../../app.component';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-settings-view',
  templateUrl: './settings-view.component.html',
  styleUrls: ['./settings-view.component.css']
})
export class SettingsViewComponent implements OnInit {



  settingsForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.email])
  });

  adminUser: User;
  roleVar : String;

  authService: AuthService;

  ngOnInit() {
    this.adminUser= new User("admin","admin","Fadmin","Ladmin","admin@gmail.com",Role.ADMIN);
    this.roleVar = Role[this.adminUser.role];
    this.authService = AppComponent.authService;
  }

  submit() {
    /*this.loginService.login(new User(this.username.value, this.password.value))
      .subscribe(
        res => console.log(res),
        err => console.log(err))*/
  }

  get username(): AbstractControl {
    return this.settingsForm.get('username');
  }

  get password(): AbstractControl {
    return this.settingsForm.get('password');
  }

  get firstname(): AbstractControl {
    return this.settingsForm.get('firstname');
  }

  get lastname(): AbstractControl {
    return this.settingsForm.get('lastname');
  }

  get email(): AbstractControl {
    return this.settingsForm.get('email');
  }

  /*get role(): AbstractControl {
    return this.loginForm.get('role');
  }*/
}