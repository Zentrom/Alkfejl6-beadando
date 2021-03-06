import {
  MatButtonModule,
  MatFormFieldModule,
  MatInputModule,
  MatSelectModule,
  MatTableModule,
  MatToolbarModule,
  MatTooltipModule,
  MatCardModule,
  MatListModule,
  MatRadioModule,
  MatSnackBarModule,
  MatIconModule, 
  MatProgressSpinnerModule,
  MatDialogModule,
  MatTabsModule
} from '@angular/material';
import {CdkTableModule} from '@angular/cdk/table';
import {NgModule} from "@angular/core";

@NgModule({
  imports: [MatFormFieldModule, MatInputModule, MatButtonModule, 
    MatToolbarModule, MatTableModule, MatSelectModule, MatTooltipModule, 
    CdkTableModule, MatCardModule, MatListModule, MatRadioModule,MatSnackBarModule,MatIconModule,
    MatProgressSpinnerModule, MatDialogModule, MatTabsModule
  ],
  exports: [MatFormFieldModule, MatInputModule, MatButtonModule,
    MatToolbarModule, MatTableModule, MatSelectModule, MatTooltipModule, 
    CdkTableModule, MatCardModule, MatListModule, MatRadioModule,MatSnackBarModule,MatIconModule,
    MatProgressSpinnerModule, MatDialogModule, MatTabsModule
  ]
})
export class UiModule {
}