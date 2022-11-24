import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { Routes, RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ReactiveFormsModule } from '@angular/forms';
import {
  OktaCallbackComponent,
  OktaAuthModule,
  OKTA_CONFIG,
} from '@okta/okta-angular';
import { LoginComponent } from './Components/login/login.component';
import { CheckoutComponent } from './Components/checkout/checkout.component';
import { CartDetailsComponent } from './Components/cart-details/cart-details.component';
import { ProductDetailsComponent } from './Components/product-details/product-details.component';
import { ProductListComponent } from './Components/product-list/product-list.component';
import { AppComponent } from './app.component';
import { ProductCategoryMenuComponent } from './Components/product-category-menu/product-category-menu.component';
import { SearchComponent } from './Components/search/search.component';
import { CartStatusComponent } from './Components/cart-status/cart-status.component';
import { LoginStatusComponent } from './Components/login-status/login-status.component';
import { ProductService } from './services/product.service';
import myAppConfig from './config/my-app-config';
import { OktaAuth } from '@okta/okta-auth-js';

const oktaConfig = myAppConfig.oidc;

const oktaAuth = new OktaAuth(oktaConfig);

const routes: Routes = [
  { path: 'login/callback', component: OktaCallbackComponent },
  { path: 'login', component: LoginComponent },
  { path: 'checkout', component: CheckoutComponent },
  { path: 'cart-details', component: CartDetailsComponent },
  { path: 'products/:id', component: ProductDetailsComponent },
  { path: 'search/:keyword', component: ProductListComponent },
  { path: 'category/:id/:name', component: ProductListComponent },
  { path: 'category', component: ProductListComponent },
  { path: 'products', component: ProductListComponent },
  { path: '', component: ProductListComponent, pathMatch: 'full' },
  { path: '**', component: ProductListComponent, pathMatch: 'full' },
];
@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    ProductCategoryMenuComponent,
    SearchComponent,
    ProductDetailsComponent,
    CartStatusComponent,
    CartDetailsComponent,
    CheckoutComponent,
    LoginComponent,
    LoginStatusComponent,
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule,
    OktaAuthModule,
  ],
  providers: [ProductService, { provide: OKTA_CONFIG, useValue: { oktaAuth } }],
  bootstrap: [AppComponent],
})
export class AppModule {}
