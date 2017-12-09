import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { api } from '../config/api';
import { WishList } from '../model/wishlist';

@Injectable()
export class WishlistService {

  constructor(
    private httpClient: HttpClient
  ) {}

  public getWishLists(): Observable<WishList[]> {
    return this.httpClient.get<WishList[]>(api + 'user/wishlists');
  }

  public addWishList(wishlist: WishList): Observable<WishList> {
    return this.httpClient.post<WishList>(api + 'user/wishlists', wishlist);
  }

  public deleteWishList(listId: number): Observable<any> {
    return this.httpClient.delete(api + 'user/wishlists/' + listId);
  }

  public updateWishList(wishlist: WishList): Observable<WishList> {
    return this.httpClient.patch<WishList>(api + 'user/wishlists/' + wishlist.id, wishlist);
  }

  public readWishList(wishlistId: number): Observable<WishList> {
    return this.httpClient.get<WishList>(api + 'user/wishlists/' + wishlistId);
  }

  public listFriendsLists(friendId: number): Observable<WishList[]> {
    return this.httpClient.get<WishList[]>(api + 'user/friends/' + friendId + '/wishlists');
  }

  public adminDeleteUsersWishList(friendId: number, listId: number): Observable<any> {
    return this.httpClient.delete(api + 'user/friends/' + friendId + '/wishlists/' + listId);
  }

  public adminUpdateUsersWishList(friendId: number, wishlist: WishList): Observable<WishList> {
    return this.httpClient.patch<WishList>(api + 'user/friends/' + friendId + '/wishlists/' + wishlist.id, wishlist);
  }

  public adminAddToUsersWishList(friendId: number, wishlist: WishList): Observable<WishList> {
    return this.httpClient.post<WishList>(api + 'user/friends/' + friendId + '/wishlists', wishlist);
  }



}
