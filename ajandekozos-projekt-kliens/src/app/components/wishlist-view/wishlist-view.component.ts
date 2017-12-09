import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

import { User } from '../../model/user';

@Component({
  selector: 'app-wishlist-view',
  templateUrl: './wishlist-view.component.html',
  styleUrls: ['./wishlist-view.component.css'],
  providers: [AuthService]
})

export class WishlistViewComponent implements OnInit {
  
    constructor(
      private router: Router
    ) {}
  
    ngOnInit() {
    }
  
}
  
