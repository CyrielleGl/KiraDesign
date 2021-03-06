import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'categories',
        loadChildren: () => import('./categories/categories.module').then(m => m.KiraCategoriesModule),
      },
      {
        path: 'products',
        loadChildren: () => import('./products/products.module').then(m => m.KiraProductsModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class KiraEntityModule {}
