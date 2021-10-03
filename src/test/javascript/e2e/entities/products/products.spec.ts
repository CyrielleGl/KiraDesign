import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProductsComponentsPage, ProductsDeleteDialog, ProductsUpdatePage } from './products.page-object';

const expect = chai.expect;

describe('Products e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let productsComponentsPage: ProductsComponentsPage;
  let productsUpdatePage: ProductsUpdatePage;
  let productsDeleteDialog: ProductsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Products', async () => {
    await navBarPage.goToEntity('products');
    productsComponentsPage = new ProductsComponentsPage();
    await browser.wait(ec.visibilityOf(productsComponentsPage.title), 5000);
    expect(await productsComponentsPage.getTitle()).to.eq('kiraApp.products.home.title');
    await browser.wait(ec.or(ec.visibilityOf(productsComponentsPage.entities), ec.visibilityOf(productsComponentsPage.noResult)), 1000);
  });

  it('should load create Products page', async () => {
    await productsComponentsPage.clickOnCreateButton();
    productsUpdatePage = new ProductsUpdatePage();
    expect(await productsUpdatePage.getPageTitle()).to.eq('kiraApp.products.home.createOrEditLabel');
    await productsUpdatePage.cancel();
  });

  it('should create and save Products', async () => {
    const nbButtonsBeforeCreate = await productsComponentsPage.countDeleteButtons();

    await productsComponentsPage.clickOnCreateButton();

    await promise.all([
      productsUpdatePage.setPdtNameInput('pdtName'),
      productsUpdatePage.setPdtSubtitleInput('pdtSubtitle'),
      productsUpdatePage.setPdtDescriptionInput('pdtDescription'),
      productsUpdatePage.setPdtMaterialInput('pdtMaterial'),
      productsUpdatePage.setPdtPriceInput('5'),
      productsUpdatePage.setPdtStockInput('5'),
      productsUpdatePage.pdtCategorySelectLastOption(),
    ]);

    expect(await productsUpdatePage.getPdtNameInput()).to.eq('pdtName', 'Expected PdtName value to be equals to pdtName');
    expect(await productsUpdatePage.getPdtSubtitleInput()).to.eq('pdtSubtitle', 'Expected PdtSubtitle value to be equals to pdtSubtitle');
    expect(await productsUpdatePage.getPdtDescriptionInput()).to.eq(
      'pdtDescription',
      'Expected PdtDescription value to be equals to pdtDescription'
    );
    expect(await productsUpdatePage.getPdtMaterialInput()).to.eq('pdtMaterial', 'Expected PdtMaterial value to be equals to pdtMaterial');
    expect(await productsUpdatePage.getPdtPriceInput()).to.eq('5', 'Expected pdtPrice value to be equals to 5');
    expect(await productsUpdatePage.getPdtStockInput()).to.eq('5', 'Expected pdtStock value to be equals to 5');
    const selectedPdtOnSold = productsUpdatePage.getPdtOnSoldInput();
    if (await selectedPdtOnSold.isSelected()) {
      await productsUpdatePage.getPdtOnSoldInput().click();
      expect(await productsUpdatePage.getPdtOnSoldInput().isSelected(), 'Expected pdtOnSold not to be selected').to.be.false;
    } else {
      await productsUpdatePage.getPdtOnSoldInput().click();
      expect(await productsUpdatePage.getPdtOnSoldInput().isSelected(), 'Expected pdtOnSold to be selected').to.be.true;
    }

    await productsUpdatePage.save();
    expect(await productsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await productsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Products', async () => {
    const nbButtonsBeforeDelete = await productsComponentsPage.countDeleteButtons();
    await productsComponentsPage.clickOnLastDeleteButton();

    productsDeleteDialog = new ProductsDeleteDialog();
    expect(await productsDeleteDialog.getDialogTitle()).to.eq('kiraApp.products.delete.question');
    await productsDeleteDialog.clickOnConfirmButton();

    expect(await productsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
