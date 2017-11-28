import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';

import { User,Role } from '../../model/user';

@Component({
  selector: 'app-settings-view',
  templateUrl: './settings-view.component.html',
  styleUrls: ['./settings-view.component.css']
})
export class SettingsViewComponent implements OnInit {



  loginForm: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  });


  adminUser: User;
  roleVar : String;

  ngOnInit() {
    this.adminUser= new User("admin","admin","Fadmin","Ladmin","admin@gmail.com",Role.ADMIN);
    this.roleVar = Role[this.adminUser.role];
  }

  submit() {
    /*this.loginService.login(new User(this.username.value, this.password.value))
      .subscribe(
        res => console.log(res),
        err => console.log(err))*/
  }

  get username(): AbstractControl {
    return this.loginForm.get('username');
  }

  get password(): AbstractControl {
    return this.loginForm.get('password');
  }

  get firstname(): AbstractControl {
    return this.loginForm.get('firstname');
  }

  get lastname(): AbstractControl {
    return this.loginForm.get('lastname');
  }

  get email(): AbstractControl {
    return this.loginForm.get('email');
  }

  /*get role(): AbstractControl {
    return this.loginForm.get('role');
  }*/
}
