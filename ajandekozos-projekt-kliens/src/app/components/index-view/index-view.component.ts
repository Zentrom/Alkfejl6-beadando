import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { BreadcrumbService } from '../../services/breadcrumb.service';

@Component({
  selector: 'app-index-view',
  templateUrl: './index-view.component.html',
  styleUrls: ['./index-view.component.css'],
  providers: []
})
export class IndexViewComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private breadCrumbService: BreadcrumbService
  ) { }

  getHomeName(): string{
    return this.authService.getUser().firstname + " " + this.authService.getUser().lastname;
  }

  ngOnInit() {
    
  }

}
