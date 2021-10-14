import { element, by, ElementFinder } from 'protractor';

export class CategoriesComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-categories div table .btn-danger'));
  title = element.all(by.css('jhi-categories div h2#page-heading span')).first();
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

export class CategoriesUpdatePage {
  pageTitle = element(by.id('jhi-categories-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  catNameInput = element(by.id('field_catName'));
  catDescriptionInput = element(by.id('field_catDescription'));
  catOrderInput = element(by.id('field_catOrder'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setCatNameInput(catName: string): Promise<void> {
    await this.catNameInput.sendKeys(catName);
  }

  async getCatNameInput(): Promise<string> {
    return await this.catNameInput.getAttribute('value');
  }

  async setCatDescriptionInput(catDescription: string): Promise<void> {
    await this.catDescriptionInput.sendKeys(catDescription);
  }

  async getCatDescriptionInput(): Promise<string> {
    return await this.catDescriptionInput.getAttribute('value');
  }

  async setCatOrderInput(catOrder: string): Promise<void> {
    await this.catOrderInput.sendKeys(catOrder);
  }

  async getCatOrderInput(): Promise<string> {
    return await this.catOrderInput.getAttribute('value');
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

export class CategoriesDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-categories-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-categories'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
