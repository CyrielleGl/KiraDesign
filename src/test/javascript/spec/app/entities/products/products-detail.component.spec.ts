import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KiraTestModule } from '../../../test.module';
import { ProductsDetailComponent } from 'app/entities/products/products-detail.component';
import { Products } from 'app/shared/model/products.model';

describe('Component Tests', () => {
  describe('Products Management Detail Component', () => {
    let comp: ProductsDetailComponent;
    let fixture: ComponentFixture<ProductsDetailComponent>;
    const route = ({ data: of({ products: new Products(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KiraTestModule],
        declarations: [ProductsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProductsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load products on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.products).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
