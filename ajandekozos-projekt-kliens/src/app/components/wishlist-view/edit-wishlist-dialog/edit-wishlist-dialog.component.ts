import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormGroup, FormBuilder } from '@angular/forms'

@Component({
  selector: 'app-edit-wishlist-dialog',
  templateUrl: './edit-wishlist-dialog.component.html',
  styleUrls: ['./edit-wishlist-dialog.component.css'],
})
export class EditWishlistDialogComponent implements OnInit {
  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<EditWishlistDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private data
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      //title: this.data ? this.data.title : ''
      title: ''
    });
  }

  public submit(form) {
    this.dialogRef.close(`${form.value.title}`);
  }

}
