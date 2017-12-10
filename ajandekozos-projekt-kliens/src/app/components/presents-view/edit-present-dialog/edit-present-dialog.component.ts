import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormGroup, FormBuilder } from '@angular/forms'
@Component({
  selector: 'app-edit-present-dialog',
  templateUrl: './edit-present-dialog.component.html',
  styleUrls: ['./edit-present-dialog.component.css'],
})
export class EditPresentDialogComponent implements OnInit {
  form: FormGroup;
  
    constructor(
      private formBuilder: FormBuilder,
      private dialogRef: MatDialogRef<EditPresentDialogComponent>,
      @Inject(MAT_DIALOG_DATA) private data
    ) { }
  
    ngOnInit() {
      this.form = this.formBuilder.group({
        //title: this.data ? this.data.title : ''
        name: '',
        price: '',
        link: ''
      });
    }
  
    public submit(form) {
      this.dialogRef.close({name: `${form.value.name}`, price: `${form.value.price}`, link: `${form.value.link}`});
    }
  
  }
