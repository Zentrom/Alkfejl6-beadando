import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Present } from '../../model/present';
import { User } from '../../model/user';
import { Comment } from '../../model/comment';
import { CommentService } from '../../services/comment.service';
import { PresentService } from '../../services/present.service';
import { BreadcrumbService } from '../../services/breadcrumb.service';
import { AuthService } from '../../services/auth.service';
import { UserDTO } from '../../model/userdto';

@Component({
  selector: 'app-comments-view',
  templateUrl: './comments-view.component.html',
  styleUrls: ['./comments-view.component.css'],
  providers: [CommentService, PresentService]
})

export class CommentsViewComponent implements OnInit {
  private comments: Comment[];
  private friendId: number;
  private friendListId: number;
  private presentId: number;
  private date: Date;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private breadCrumbService: BreadcrumbService,
    private presentService: PresentService,
    private authService: AuthService,
    private commentService: CommentService
  ) {} 
  
  convertToDateString(comment: Comment): string{
    this.date=new Date(comment.timeStamp);

    var year = this.date.getFullYear().toString().slice(2, 4);
    var month = (this.date.getMonth() < 10) ? "0" + this.date.getMonth().toString() : this.date.getMonth().toString();
    var day = (this.date.getDate() < 10) ? "0" + this.date.getDate().toString() : this.date.getDate().toString();
    var hour = (this.date.getHours() < 10) ? "0" + this.date.getHours().toString() : this.date.getHours().toString();
    var minutes = (this.date.getMinutes() < 10) ? "0" + this.date.getMinutes().toString() : this.date.getMinutes().toString()

    return year + "." + month + "." + day + ", " + hour + ":" + minutes;
  }

  public postComment(commentText: string): void{
    var tmpComment = new Comment(null ,commentText, null, null);
    this.commentService.addComment(this.friendId,this.friendListId,this.presentId,tmpComment).subscribe((newComment:Comment) => {
      this.comments.push(newComment);
    });
  }

  public removeComment(comment: Comment): void{
    console.log("COMMENT ID: " + comment.id);
    this.commentService.deleteComment(this.friendId, this.friendListId, this.presentId, comment.id).subscribe(() => {
      var index = this.comments.indexOf(comment);
      this.comments.splice(index, 1);
      console.log("TÖRÖL COMMENT");    
    })

  }

  ngOnInit() {
    this.comments= null;
    if (this.authService.isUserAdmin()) {
      this.presentId = parseInt(this.activatedRoute.snapshot.paramMap.get('presentId'));
      this.friendListId = parseInt(this.activatedRoute.snapshot.paramMap.get('listId'));
      this.friendId = parseInt(this.activatedRoute.snapshot.paramMap.get('userId'));
    } else {
      this.presentId = parseInt(this.activatedRoute.snapshot.paramMap.get('friendPresentId'));
      this.friendListId = parseInt(this.activatedRoute.snapshot.paramMap.get('friendListId'));
      this.friendId = parseInt(this.activatedRoute.snapshot.paramMap.get('friendId'));
    }

    this.commentService.getComments(this.friendId, this.friendListId, this.presentId).subscribe((comments: Comment[]) => {
      this.comments = comments;
    });
  }
    
}