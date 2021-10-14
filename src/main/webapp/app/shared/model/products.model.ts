import { ICategories } from 'app/shared/model/categories.model';

export interface IProducts {
  id?: number;
  pdtName?: string;
  pdtSubtitle?: string;
  pdtDescription?: string;
  pdtMaterial?: string;
  pdtPrice?: number;
  pdtStock?: number;
  pdtOnSold?: boolean;
  pdtCategory?: ICategories;
}

export class Products implements IProducts {
  constructor(
    public id?: number,
    public pdtName?: string,
    public pdtSubtitle?: string,
    public pdtDescription?: string,
    public pdtMaterial?: string,
    public pdtPrice?: number,
    public pdtStock?: number,
    public pdtOnSold?: boolean,
    public pdtCategory?: ICategories
  ) {
    this.pdtOnSold = this.pdtOnSold || false;
  }
}
