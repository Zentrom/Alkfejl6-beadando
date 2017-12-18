import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { Comment } from '../../../model/comment';

@Component({
  selector: 'app-add-comment-view',
  templateUrl: './add-comment-view.component.html',
  styleUrls: ['./add-comment-view.component.css'],
})
export class AddCommentViewComponent implements OnInit {
  @Output()
  public createComment: EventEmitter<string> = new EventEmitter();

  private error: string = "";

  constructor() { }

  public postComment(commentText: string): void {
    if (commentText && commentText.trim().length > 0) {
      this.error = "";

      this.createComment.emit(commentText);
    } else {
      this.error = "Please enter something!";
    }
  } 

  ngOnInit() {
  }

}
