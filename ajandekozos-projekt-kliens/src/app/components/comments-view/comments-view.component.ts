import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service';

import { Present } from '../../model/present';
import { User } from '../../model/user';
import { Comment } from '../../model/comment';
import { CommentService } from '../../services/comment.service';
import { PresentService } from '../../services/present.service';

@Component({
  selector: 'app-comments-view',
  templateUrl: './comments-view.component.html',
  styleUrls: ['./comments-view.component.css'],
  providers: [AuthService,PresentService,CommentService]
})

export class CommentsViewComponent implements OnInit {
  private present: Present;
  private comments: Comment[];
  private friendId: number;
  private friendListId: number;
  private presentId: number;
  private date: Date;
  

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private presentService: PresentService,
    private commentService: CommentService
  ) {} 

  getPresentName(){
    this.presentService.readPresent(this.friendListId,this.presentId).subscribe((present: Present) => {
      this.present= present;
    });
  }

  getCommentDate(){
    console.log(this.date.toLocaleDateString());
  }

  ngOnInit() {
    //this.present= null;
    this.comments= null;
    this.presentId = parseInt(this.activatedRoute.snapshot.paramMap.get('friendPresentId'));
    this.friendListId = parseInt(this.activatedRoute.snapshot.paramMap.get('friendListId'));
    
    console.log(this.presentId);
    this.getPresentName();
    this.friendId = parseInt(this.activatedRoute.snapshot.paramMap.get('friendId'));
    
    var ts = new Date(1420844400000);
    console.log(ts.toDateString());
    // this.getCommentDate();
    this.commentService.getComments(this.friendId, this.friendListId, this.presentId).subscribe((comments: Comment[]) => {
      this.comments = comments;
      console.log(this.comments[0].timeStamp);
      //console.log(this.date.toLocaleDateString());
    });
    
  }
    
}