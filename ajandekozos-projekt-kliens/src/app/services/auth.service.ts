import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {User} from "../model/User";
import {RoutesKetto, Server} from "../modules/routing/routing.module";

@Injectable()
export class AuthService {
  user: User;
  static isLoggedIn: boolean = false;

  constructor(jelen: boolean) {
    AuthService.isLoggedIn = jelen;
  }

  isLoggedIn(){
    return AuthService.isLoggedIn;
  }

  /*login(user: User) {
    return this.http.post(Server.routeTo(RoutesKetto.LOGIN), user)
      .map(res => {
        this.isLoggedIn = true;
        this.user = res.json();
        return this.user;
      })
  }

  register(user: User) {
    return this.http.post(Server.routeTo(RoutesKetto.REGISTER), user)
      .map(res => {
        this.isLoggedIn = true;
        this.user = res.json();
        return this.user;
      })
  }

  logout() {
    return this.http.get(Server.routeTo(RoutesKetto.LOGOUT))
      .map(res => {
        this.user = null;
        this.isLoggedIn = false;
      })
  }*/
}
