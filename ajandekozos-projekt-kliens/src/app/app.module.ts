import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router'
import { HttpClientModule } from '@angular/common/http'; 
import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import { RoutingModule} from './modules/routing/routing.module';
import { UiModule} from "./modules/ui/ui.module";

import { LoginViewComponent } from './components/login-view/login-view.component';
import { RegisterViewComponent } from './components/register-view/register-view.component';
import { IndexViewComponent } from './components/index-view/index-view.component';
import { WishlistViewComponent } from './components/wishlist-view/wishlist-view.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginViewComponent,
    RegisterViewComponent,
    IndexViewComponent,
    WishlistViewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    RoutingModule,
    BrowserAnimationsModule,
    UiModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
