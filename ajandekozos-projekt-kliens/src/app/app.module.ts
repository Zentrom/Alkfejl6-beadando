import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router'

import {AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import { RegisterComponent } from './components/register/register.component';
import { RoutingModule} from './modules/routing/routing.module';
import { UiModule} from "./modules/ui/ui.module";
import { MenuComponent } from './components/menu/menu.component';
import { MainpageViewComponent } from './pages/mainpage-view/mainpage-view.component';
import { WishlistViewComponent } from './pages/wishlist-view/wishlist-view.component';
import { FriendsViewComponent } from './pages/friends-view/friends-view.component';
import { SettingsViewComponent } from './pages/settings-view/settings-view.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    MenuComponent,
    MainpageViewComponent,
    WishlistViewComponent,
    FriendsViewComponent,
    SettingsViewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    RouterModule.forRoot(RoutingModule),
    BrowserAnimationsModule,
    UiModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
