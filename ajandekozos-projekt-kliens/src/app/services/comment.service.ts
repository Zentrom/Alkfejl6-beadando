import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { api } from '../config/api';
import { Comment } from '../model/comment';

@Injectable()
export class CommentService {

  constructor(
    private httpClient: HttpClient
  ) {}

  public getComments(friendId: number, friendListId: number, presentId: number): Observable<Comment[]> {
    return this.httpClient.get<Comment[]>(api + 'user/friends/' + friendId + '/wishlists/' + friendListId + '/presents/' + presentId + '/comments');
  }

  public addComment(friendId: number, friendListId: number, presentId: number, comment: Comment): Observable<Comment> {
    return this.httpClient.post<Comment>(api + 'user/friends/' + friendId + '/wishlists/' + friendListId + '/presents/' + presentId + '/comments', comment);
  }

  public updateComment(friendId: number, friendListId: number, presentId: number, comment: Comment): Observable<Comment> {
    return this.httpClient.patch<Comment>(api + 'user/friends/' + friendId + '/wishlists/' + friendListId + '/presents/' + presentId + '/comments/' + comment.id, comment);
  }

  public deleteComment(friendId: number, friendListId: number, presentId: number, commentId: number): Observable<any> {
    return this.httpClient.delete(api + 'user/friends/' + friendId + '/wishlists/' + friendListId + '/presents/' + presentId + '/comments/' + commentId, {responseType: 'text'});
  }

  public readComment(friendId: number, friendListId: number, presentId: number, commentId: number): Observable<Comment> {
    return this.httpClient.get<Comment>(api + 'user/friends/' + friendId + '/wishlists/' + friendListId + '/presents/' + presentId + '/comments/' + commentId);
  }

}
