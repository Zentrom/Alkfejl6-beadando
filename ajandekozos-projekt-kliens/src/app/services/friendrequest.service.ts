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
    return this.httpClient.get<FriendRequest[]>(api + 'friendrequests');
  }

  // itt a status igazából egy szám (0 vagy 1) csak számként nem lehet paraméterben átküldeni
  public processRequests(requestId: number, status: string): Observable<any> {
      return this.httpClient.delete(api + 'friendrequests/' + requestId, {params: {status}});
  }

  public readRequest(requestId: number): Observable<FriendRequest> {
    return this.httpClient.get<FriendRequest>(api + 'friendrequests/' + requestId);
  }

  public listPossibleFriends(firstname: string, lastname: string): Observable<UserDTO[]> {
    return this.httpClient.get<UserDTO[]>(api + 'newrequest', {params: {firstname, lastname}});
  }

  // Get vagy Post?
  public createFriendRequest(friendRequest: FriendRequest): Observable<FriendRequest> {
    return this.httpClient.post<FriendRequest>(api + 'newrequest', friendRequest);
  }

}
