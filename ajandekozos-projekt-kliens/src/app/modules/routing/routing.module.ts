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
import { FriendWishlistViewComponent } from '../../components/friend-wishlist-view/friend-wishlist-view.component';
import { FriendPresentsViewComponent } from '../../components/friend-presents-view/friend-presents-view.component'; 
import { CommentsViewComponent } from '../../components/comments-view/comments-view.component';

 const appRoutes: Routes = [
  { path: '',
      canActivateChild: [RouteGuardService], 
      children: [
        { path: '', component: IndexViewComponent, pathMatch: 'full'},
        { path: 'login', component: LoginViewComponent },
        { path: 'register', component: RegisterViewComponent },
        { path: 'user/wishlists', component: WishlistViewComponent, data: { roles: ['USER'] }},
        { path: 'user/wishlists/:listId/presents', component: PresentsViewComponent, data: { roles: ['USER'] } },
        { path: 'user/friends', component: FriendViewComponent, data: { roles: ['USER'] } },
        { path: 'user/friends/:userId/wishlists', component: FriendWishlistViewComponent, data: { roles: ['USER'] } },
        { path: 'user/friends/:friendId/wishlists/:friendListId/presents', component: FriendPresentsViewComponent, data: { roles: ['USER'] } },
        { path: 'user/friends/:friendId/wishlists/:friendListId/presents/:friendPresentId/comments', component: CommentsViewComponent, data: { roles: ['USER'] } },        
        { path: 'user/newrequest', component: AddFriendViewComponent, data: { roles: ['USER'] } },
        { path: 'user/friendrequests', component: IncomingRequestsViewComponent, data: { roles: ['USER'] } },
        { path: 'users', component: UsersViewComponent, data: { roles: ['ADMIN'] }, pathMatch: 'full' },
        { path: 'users/:userId/wishlists', component: WishlistViewComponent, data: { roles: ['ADMIN'] }, pathMatch: 'full' },        
        { path: 'users/:userId/wishlists/:listId/presents', component: FriendPresentsViewComponent, data: { roles: ['ADMIN'] }, pathMatch: 'full' }, 
        { path: 'users/:userId/wishlists/:listId/presents/:presentId/comments', component: CommentsViewComponent, data: { roles: ['ADMIN'] }, pathMatch: 'full' }, 
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
