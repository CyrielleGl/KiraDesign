import { Route } from '@angular/router';

import { ShopComponent } from './shop.component';
import { PatternsComponent } from './patterns/patterns.component';
import { ProductsComponent } from 'app/shop/products/products.component';

export const SHOP_ROUTE: Route = {
  path: 'shop',
  component: ShopComponent,
  data: {
    authorities: [],
    pageTitle: 'shop.title',
  },
};

export const PRODUCTS_ROUTE: Route = {
  path: 'products',
  component: ProductsComponent,
  data: {
    authorities: [],
    pageTitle: 'shop.products.title',
  },
};

export const PATTERNS_ROUTE: Route = {
  path: 'patterns',
  component: PatternsComponent,
  data: {
    authorities: [],
    pageTitle: 'shop.patterns.title',
  },
};
