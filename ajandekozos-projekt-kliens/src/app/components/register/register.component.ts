import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { AppComponent } from '../../app.component';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
  });

  authService: AuthService;


  ngOnInit() {
    this.authService= AppComponent.authService;
  }

  submit() {
    /*this.authService.register(new User(this.username.value, this.password.value, this.email.value))
      .subscribe(
        res => console.log(res),
        err => console.log(err))*/
  }

  get username(): AbstractControl {
    return this.registerForm.get('username');
  }

  get password(): AbstractControl {
    return this.registerForm.get('password');
  }

  get confirmPassword(): AbstractControl {
    return this.registerForm.get('confirmPassword');
  }

  get email(): AbstractControl {
    return this.registerForm.get('email');
  }

  get firstname(): AbstractControl {
    return this.registerForm.get('firstname');
  }

  get lastname(): AbstractControl {
    return this.registerForm.get('lastname');
  }
}
