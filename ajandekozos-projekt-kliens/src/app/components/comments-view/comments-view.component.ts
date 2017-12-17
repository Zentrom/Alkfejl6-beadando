import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service';

import { Present } from '../../model/present';
import { User } from '../../model/user';
import { Comment } from '../../model/comment';
import { CommentService } from '../../services/comment.service';
import { PresentService } from '../../services/present.service';
import { BreadcrumbService } from '../../services/breadcrumb.service';
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
  
  convertToDateString(wilcommen: Comment): string{
    this.date=new Date(wilcommen.timeStamp);
    return this.date.toDateString();
  }

  public postComment(commentText: string): void{
    var tmpComment = new Comment(null ,commentText, null, null);
    this.commentService.addComment(this.friendId,this.friendListId,this.presentId,tmpComment).subscribe((newComment:Comment) => {
      this.comments.push(newComment);
    });
  }

  public removeComment(comment: Comment): void{
    this.commentService.deleteComment(this.friendId,this.friendListId,this.presentId,comment.id).subscribe(() => {
      var index = this.comments.indexOf(comment);
      this.comments.splice(index, 1);    
    });
  }

  ngOnInit() {
    this.comments= null;
    this.presentId = parseInt(this.activatedRoute.snapshot.paramMap.get('friendPresentId'));
    this.friendListId = parseInt(this.activatedRoute.snapshot.paramMap.get('friendListId'));
    
    this.friendId = parseInt(this.activatedRoute.snapshot.paramMap.get('friendId'));
    
    //var ts = new Date(1420844400000);
   // console.log(ts.toDateString());
    // this.getCommentDate();
    this.commentService.getComments(this.friendId, this.friendListId, this.presentId).subscribe((comments: Comment[]) => {
      this.comments = comments;
      //for(let i: number=0;i<this.comments.length;i++){
     //   this.comments.timeStamp[i]=(new Date(this.comments[i].timeStamp)).toDateString();
     // }
      //console.log(this.comments[0].timeStamp);
      //console.log(this.date.toLocaleDateString());
    });
    
  }
    
}