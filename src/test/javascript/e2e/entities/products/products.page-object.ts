import { element, by, ElementFinder } from 'protractor';

export class ProductsComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-products div table .btn-danger'));
  title = element.all(by.css('jhi-products div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class ProductsUpdatePage {
  pageTitle = element(by.id('jhi-products-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  pdtNameInput = element(by.id('field_pdtName'));
  pdtSubtitleInput = element(by.id('field_pdtSubtitle'));
  pdtDescriptionInput = element(by.id('field_pdtDescription'));
  pdtMaterialInput = element(by.id('field_pdtMaterial'));
  pdtPriceInput = element(by.id('field_pdtPrice'));
  pdtStockInput = element(by.id('field_pdtStock'));
  pdtOnSoldInput = element(by.id('field_pdtOnSold'));

  pdtCategorySelect = element(by.id('field_pdtCategory'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setPdtNameInput(pdtName: string): Promise<void> {
    await this.pdtNameInput.sendKeys(pdtName);
  }

  async getPdtNameInput(): Promise<string> {
    return await this.pdtNameInput.getAttribute('value');
  }

  async setPdtSubtitleInput(pdtSubtitle: string): Promise<void> {
    await this.pdtSubtitleInput.sendKeys(pdtSubtitle);
  }

  async getPdtSubtitleInput(): Promise<string> {
    return await this.pdtSubtitleInput.getAttribute('value');
  }

  async setPdtDescriptionInput(pdtDescription: string): Promise<void> {
    await this.pdtDescriptionInput.sendKeys(pdtDescription);
  }

  async getPdtDescriptionInput(): Promise<string> {
    return await this.pdtDescriptionInput.getAttribute('value');
  }

  async setPdtMaterialInput(pdtMaterial: string): Promise<void> {
    await this.pdtMaterialInput.sendKeys(pdtMaterial);
  }

  async getPdtMaterialInput(): Promise<string> {
    return await this.pdtMaterialInput.getAttribute('value');
  }

  async setPdtPriceInput(pdtPrice: string): Promise<void> {
    await this.pdtPriceInput.sendKeys(pdtPrice);
  }

  async getPdtPriceInput(): Promise<string> {
    return await this.pdtPriceInput.getAttribute('value');
  }

  async setPdtStockInput(pdtStock: string): Promise<void> {
    await this.pdtStockInput.sendKeys(pdtStock);
  }

  async getPdtStockInput(): Promise<string> {
    return await this.pdtStockInput.getAttribute('value');
  }

  getPdtOnSoldInput(): ElementFinder {
    return this.pdtOnSoldInput;
  }

  async pdtCategorySelectLastOption(): Promise<void> {
    await this.pdtCategorySelect.all(by.tagName('option')).last().click();
  }

  async pdtCategorySelectOption(option: string): Promise<void> {
    await this.pdtCategorySelect.sendKeys(option);
  }

  getPdtCategorySelect(): ElementFinder {
    return this.pdtCategorySelect;
  }

  async getPdtCategorySelectedOption(): Promise<string> {
    return await this.pdtCategorySelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class ProductsDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-products-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-products'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
