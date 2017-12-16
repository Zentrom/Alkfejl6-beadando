import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { api } from '../config/api';
import { FriendRequest } from '../model/friendrequest';
import { UserDTO } from '../model/userdto';

@Injectable()
export class FriendrequestService {

  constructor(
    private httpClient: HttpClient
  ) {}

  public getFriendRequests(): Observable<FriendRequest[]> {
    return this.httpClient.get<FriendRequest[]>(api + 'user/friendrequests');
  }
  
  public processRequests(requestId: number, status: string): Observable<any> {
      return this.httpClient.delete(api + 'user/friendrequests/' + requestId, {params: {status}, responseType: 'text'});
  }

  public readRequest(requestId: number): Observable<FriendRequest> {
    return this.httpClient.get<FriendRequest>(api + 'user/friendrequests/' + requestId);
  }

  public listPossibleFriends(firstname: string, lastname: string): Observable<UserDTO[]> {
    return this.httpClient.get<UserDTO[]>(api + 'user/newrequest', {params: {firstname, lastname}});
  }

  public createFriendRequest(requesteeId: number): Observable<FriendRequest> {
    return this.httpClient.post<FriendRequest>(api + 'user/newrequest', requesteeId);
  }

}
