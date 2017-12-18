import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { api } from '../config/api';
import { Present } from '../model/present';

@Injectable()
export class PresentService {

  constructor(
    private httpClient: HttpClient
  ) {}

  public getPresents(listId: number): Observable<Present[]> {
    return this.httpClient.get<Present[]>(api + 'user/wishlists/' + listId + '/presents');
  }

  public addPresent(listId: number, present: Present): Observable<Present> {
    return this.httpClient.post<Present>(api + 'user/wishlists/' + listId + '/presents', present);
  }

  public deletePresent(listId: number, presentId: number): Observable<any> {
    return this.httpClient.delete(api + 'user/wishlists/' + listId + '/presents/' + presentId, {responseType: 'text'});
  }

  public updatePresent(listId: number, present: Present): Observable<Present> {
    return this.httpClient.patch<Present>(api + 'user/wishlists/' + listId + '/presents/' + present.id, present);
  }

  public listPresentsOfFriendsOrUsersList(friendId: number, friendListId: number): Observable<Present[]> {
    return this.httpClient.get<Present[]>(api + 'user/friends/' + friendId + '/wishlists/' + friendListId + '/presents');
  }

  public updateFriendPresent(friendId: number, friendListId: number, present: Present): Observable<Present> {
    return this.httpClient.patch<Present>(api + 'user/friends/' + friendId + '/wishlists/' + friendListId + '/presents/' + present.id, present);
  }

}
