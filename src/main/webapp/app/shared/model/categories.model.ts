import { IProducts } from 'app/shared/model/products.model';

export interface ICategories {
  id?: number;
  catName?: string;
  catDescription?: string;
  catOrder?: number;
  catProducts?: IProducts[];
}

export class Categories implements ICategories {
  constructor(
    public id?: number,
    public catName?: string,
    public catDescription?: string,
    public catOrder?: number,
    public catProducts?: IProducts[]
  ) {}
}
