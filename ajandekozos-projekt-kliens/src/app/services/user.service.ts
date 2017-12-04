import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { api } from '../config/api';
import { UserDTO } from '../model/userdto';

@Injectable()
export class UserService {

  constructor(
    private httpClient: HttpClient
  ) {}

  public getFriends(): Observable<UserDTO[]> {
    return this.httpClient.get<UserDTO[]>(api + 'friends');
  }

  public deleteFriendOrUser(userId: number): Observable<any> {
    return this.httpClient.delete(api + 'friends/' + userId);
  }

}
