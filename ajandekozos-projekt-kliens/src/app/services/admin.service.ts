import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { api } from '../config/api';
import { UserDTO } from '../model/userdto';

@Injectable()
export class AdminService {

  constructor(
    private httpClient: HttpClient
  ) {}

  public getUsers(firstname: string, lastname: string): Observable<UserDTO[]> {
    return this.httpClient.get<UserDTO[]>(api + 'users', {params: {firstname, lastname}});
  }

  public deleteUser(userId: number): Observable<any> {
    return this.httpClient.delete(api + 'users/' + userId, {responseType: 'text'});
  }


}
