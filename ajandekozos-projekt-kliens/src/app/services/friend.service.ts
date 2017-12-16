import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { api } from '../config/api';
import { UserDTO } from '../model/userdto';

@Injectable()
export class FriendService {

  constructor(
    private httpClient: HttpClient
  ) {}

  public listFriends(firstname: string, lastname: string): Observable<UserDTO[]> {
    return this.httpClient.get<UserDTO[]>(api + 'user/friends', {params: {firstname, lastname}});
  }

  public deleteFriend(userId: number): Observable<any> {
    return this.httpClient.delete(api + 'user/friends/' + userId, {responseType: 'text'});
  }

}
