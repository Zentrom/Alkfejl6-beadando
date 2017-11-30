import {Component, OnInit} from '@angular/core';
import { AppComponent } from '../../app.component';

import { User,Role } from '../../model/User';

import { AuthService } from '../../services/auth.service';



interface MenuItem {
  link: String;
  title: String;
}

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
  providers: [AuthService]
})
export class MenuComponent implements OnInit {
  private notCommon : MenuItem = {link: '/main', title: 'Home'}
  private common: MenuItem[] = [
    {link: '/wishlist', title: 'Wishlist'},
    {link: '/friends', title: 'Friends'},
    {link: '/settings', title: 'Settings'}
  ];

  adminUser: User;
  authService: AuthService;


  /*private roleMenus = new Map<Role, MenuItem[]>([
    [Role.GUEST, [...this.common]],
    [Role.USER, [...this.common, {link: '/issues', title: 'Issues'}]],
    // [Role.ADMIN, [{link: '/stats', title: 'Statistics'}, {link: '/issues', title: 'Issues'}]]
  ]);*/

  menus: MenuItem[];
  mainP: MenuItem;


  //constructor(private authService: AuthService) {
  //}

  ngOnInit() {
    
    this.mainP = this.notCommon;
    this.menus = this.common;

    this.adminUser= new User("admin","admin","Fadmin","Ladmin","admin@gmail.com",Role.ADMIN);
    //console.log(AuthService.isLoggedIn);
    this.authService = AppComponent.authService;
    //console.log(this.authService.isLoggedIn());

    /*if (this.authService.isLoggedIn) {
      this.menus = this.roleMenus.get(this.authService.user.role);
    } else {
      this.menus = this.roleMenus.get(Role.GUEST)
    }*/
  }
}