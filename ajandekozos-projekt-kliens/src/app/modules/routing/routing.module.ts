import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginViewComponent} from '../../components/login-view/login-view.component';
import { RegisterViewComponent} from '../../components/register-view/register-view.component';
import { IndexViewComponent} from '../../components/index-view/index-view.component';
import { WishlistViewComponent} from '../../components/wishlist-view/wishlist-view.component';
import { PresentsViewComponent} from '../../components/presents-view/presents-view.component';

 const appRoutes: Routes = [
  { path: '', component: IndexViewComponent },
  { path: 'login', component: LoginViewComponent },
  { path: 'register', component: RegisterViewComponent },
  { path: 'user/wishlists', component: WishlistViewComponent },
  { path: 'user/wishlists/:listId/presents', component: PresentsViewComponent },
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})

export class RoutingModule { }
