import { Component } from '@angular/core';

//import { User,Role } from './model/user';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [AuthService]
})
export class AppComponent {
  title = 'app';

  static authService: AuthService;


  ngOnInit() {
    AppComponent.authService = new AuthService(false);
  }

  /*getAuthService(){
    return this.authService;
  }

  setAuthService(bejelentkezve: boolean){
    this.authService.isLoggedIn= bejelentkezve;
  }*/
}
