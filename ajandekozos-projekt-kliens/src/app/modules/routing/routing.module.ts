import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainpageViewComponent } from '../../pages/mainpage-view/mainpage-view.component';
import { WishlistViewComponent } from '../../pages/wishlist-view/wishlist-view.component';
import { FriendsViewComponent} from '../../pages/friends-view/friends-view.component';
import { SettingsViewComponent} from '../../pages/settings-view/settings-view.component';

export const appRoutes: Routes = [
  {path: '', redirectTo: 'main', pathMatch: 'full'},
  { path: 'main', component: MainpageViewComponent },
  { path: 'wishlist', component: WishlistViewComponent },
  { path: 'friends', component: FriendsViewComponent },
  { path: 'settings', component: SettingsViewComponent }
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
