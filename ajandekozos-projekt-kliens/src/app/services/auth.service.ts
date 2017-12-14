import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { User } from '../model/user';
import { api } from '../config/api';
import { Router } from '@angular/router';

@Injectable()
export class AuthService {
  private static user: User = null;

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  public login(username: string, password: string): Observable<boolean> {
    const result = new Subject<boolean>();
    this.http.post(api + '/login', { username, password }).subscribe((user) => {
      AuthService.user = user as User;
      result.next(true);
    }, (error) => {
      AuthService.user = null as User;
      result.next(false);
    });
    return result;
  }

  public register(username: string, password: string, firstname: string, lastname: string, email: string): Observable<boolean> {
    const result = new Subject<boolean>();
    this.http.post(api + '/register', { username, password, firstname, lastname, email }).subscribe((user) => {
      AuthService.user = user as User;
      result.next(true);
    }, (error) => {
      AuthService.user = null as User;
      result.next(false);
    });
    return result;
  }

  public logout(): void {
    this.http.get(api + 'user/logout', {responseType: 'text'}).subscribe(() => {
      AuthService.user = null;
      this.router.navigate(['']);
    });
  }

  public isLoggedIn(): boolean {
    return AuthService.user !== null;
  }

  public syncLoginStatus(): void {
    this.http.get(api).subscribe((user) => {
      if (user) {
        AuthService.user = user as User;
      } else {
        AuthService.user = null;
      }
    });
  }

  public userHasRole(role: string[]): boolean {
    if (!this.isLoggedIn()) {
      return false;
    }
    return role.includes(AuthService.user.role);
  }

  public getRole(): string {
    if (this.isLoggedIn()) {
      return AuthService.user.role;
    }
    return undefined;
  }
}
