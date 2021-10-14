import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PatternsComponent } from './patterns/patterns.component';
import { ShopComponent } from './shop.component';
import { KiraSharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { PATTERNS_ROUTE, PRODUCTS_ROUTE, SHOP_ROUTE } from 'app/shop/shop.route';
import { ProductsComponent } from './products/products.component';

@NgModule({
  declarations: [PatternsComponent, ShopComponent, ProductsComponent],
  imports: [CommonModule, KiraSharedModule, RouterModule.forChild([SHOP_ROUTE, PATTERNS_ROUTE, PRODUCTS_ROUTE])],
})
export class ShopModule {}
