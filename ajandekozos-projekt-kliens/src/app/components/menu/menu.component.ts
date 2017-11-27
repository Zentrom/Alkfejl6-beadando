import {Component, OnInit} from '@angular/core';
import { AppComponent } from '../../app.component';

import { User,Role } from '../../model/user';





interface MenuItem {
  link: String;
  title: String;
}

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  private notCommon : MenuItem = {link: '/main', title: 'Home'}
  private common: MenuItem[] = [
    {link: '/wishlist', title: 'Wishlist'},
    {link: '/friends', title: 'Friends'},
    {link: '/settings', title: 'Settings'}
  ];

  adminUser: User;

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

    this.adminUser= new User("admin","admin","admin@gmail.com",Role.ADMIN);

    /*if (this.authService.isLoggedIn) {
      this.menus = this.roleMenus.get(this.authService.user.role);
    } else {
      this.menus = this.roleMenus.get(Role.GUEST)
    }*/
  }
}