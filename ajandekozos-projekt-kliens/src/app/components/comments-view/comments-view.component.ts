import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service';

import { User } from '../../model/user';
import { Comment } from '../../model/comment';
import { CommentService } from '../../services/comment.service';

@Component({
  selector: 'app-comments-view',
  templateUrl: './comments-view.component.html',
  styleUrls: ['./comments-view.component.css'],
  providers: [AuthService, CommentService]
})

export class CommentsViewComponent implements OnInit {
  private comments: Comment[];
  private friendId: number;
  private friendListId: number;
  private presentId: number;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private commentService: CommentService
  ) {} 

  ngOnInit() {
    this.presentId = parseInt(this.activatedRoute.snapshot.paramMap.get('friendPresentId'));
    this.friendListId = parseInt(this.activatedRoute.snapshot.paramMap.get('friendListId'));
    this.friendId = parseInt(this.activatedRoute.snapshot.paramMap.get('friendId'));

    this.commentService.getComments(this.friendId, this.friendListId, this.presentId).subscribe((comments: Comment[]) => {
      this.comments = comments;
    });
  }
    
}