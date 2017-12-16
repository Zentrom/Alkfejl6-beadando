import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginViewComponent} from '../../components/login-view/login-view.component';
import { RegisterViewComponent} from '../../components/register-view/register-view.component';
import { IndexViewComponent} from '../../components/index-view/index-view.component';
import { WishlistViewComponent} from '../../components/wishlist-view/wishlist-view.component';
import { PresentsViewComponent} from '../../components/presents-view/presents-view.component';
import { AddFriendViewComponent } from '../../components/add-friend-view/add-friend-view.component';
import { FriendViewComponent} from '../../components/friend-view/friend-view.component';
import { IncomingRequestsViewComponent} from '../../components/incoming-requests-view/incoming-requests-view.component';
import { AuthService } from '../../services/auth.service';
import { RouteGuardService } from '../../services/route-guard.service';
import { UsersViewComponent } from '../../components/admin/users-view/users-view.component';

 const appRoutes: Routes = [
  { path: '',
      canActivateChild: [RouteGuardService], 
      children: [
        { path: '', component: IndexViewComponent, pathMatch: 'full'},
        { path: 'login', component: LoginViewComponent },
        { path: 'register', component: RegisterViewComponent },
        { path: 'user/wishlists', component: WishlistViewComponent, data: { roles: ['USER', 'ADMIN'] }},
        { path: 'user/wishlists/:listId/presents', component: PresentsViewComponent, data: { roles: ['USER', 'ADMIN'] } },
        { path: 'user/friends', component: FriendViewComponent, data: { roles: ['USER', 'ADMIN'] } },
        { path: 'user/newrequest', component: AddFriendViewComponent, data: { roles: ['USER', 'ADMIN'] } },
        { path: 'user/friendrequests', component: IncomingRequestsViewComponent, data: { roles: ['USER', 'ADMIN'] } },
        { path: 'users', component: UsersViewComponent, data: { roles: ['ADMIN'] }, pathMatch: 'full' },
        { path: '**', component: IndexViewComponent }
      ]}
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes, { useHash: true })
  ],
  exports: [
    RouterModule
  ],
  declarations: [],
  providers: [RouteGuardService, AuthService]
})

export class RoutingModule { }
