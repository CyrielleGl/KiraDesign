import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProducts, Products } from 'app/shared/model/products.model';
import { ProductsService } from './products.service';
import { ICategories } from 'app/shared/model/categories.model';
import { CategoriesService } from 'app/entities/categories/categories.service';

@Component({
  selector: 'jhi-products-update',
  templateUrl: './products-update.component.html',
})
export class ProductsUpdateComponent implements OnInit {
  isSaving = false;
  categories: ICategories[] = [];

  editForm = this.fb.group({
    id: [],
    pdtName: [],
    pdtSubtitle: [],
    pdtDescription: [],
    pdtMaterial: [],
    pdtPrice: [],
    pdtStock: [],
    pdtOnSold: [],
    pdtCategory: [],
  });

  constructor(
    protected productsService: ProductsService,
    protected categoriesService: CategoriesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ products }) => {
      this.updateForm(products);

      this.categoriesService.query().subscribe((res: HttpResponse<ICategories[]>) => (this.categories = res.body || []));
    });
  }

  updateForm(products: IProducts): void {
    this.editForm.patchValue({
      id: products.id,
      pdtName: products.pdtName,
      pdtSubtitle: products.pdtSubtitle,
      pdtDescription: products.pdtDescription,
      pdtMaterial: products.pdtMaterial,
      pdtPrice: products.pdtPrice,
      pdtStock: products.pdtStock,
      pdtOnSold: products.pdtOnSold,
      pdtCategory: products.pdtCategory,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const products = this.createFromForm();
    if (products.id !== undefined) {
      this.subscribeToSaveResponse(this.productsService.update(products));
    } else {
      this.subscribeToSaveResponse(this.productsService.create(products));
    }
  }

  private createFromForm(): IProducts {
    return {
      ...new Products(),
      id: this.editForm.get(['id'])!.value,
      pdtName: this.editForm.get(['pdtName'])!.value,
      pdtSubtitle: this.editForm.get(['pdtSubtitle'])!.value,
      pdtDescription: this.editForm.get(['pdtDescription'])!.value,
      pdtMaterial: this.editForm.get(['pdtMaterial'])!.value,
      pdtPrice: this.editForm.get(['pdtPrice'])!.value,
      pdtStock: this.editForm.get(['pdtStock'])!.value,
      pdtOnSold: this.editForm.get(['pdtOnSold'])!.value,
      pdtCategory: this.editForm.get(['pdtCategory'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProducts>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ICategories): any {
    return item.id;
  }
}
