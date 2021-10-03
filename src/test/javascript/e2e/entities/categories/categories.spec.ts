import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CategoriesComponentsPage, CategoriesDeleteDialog, CategoriesUpdatePage } from './categories.page-object';

const expect = chai.expect;

describe('Categories e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let categoriesComponentsPage: CategoriesComponentsPage;
  let categoriesUpdatePage: CategoriesUpdatePage;
  let categoriesDeleteDialog: CategoriesDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Categories', async () => {
    await navBarPage.goToEntity('categories');
    categoriesComponentsPage = new CategoriesComponentsPage();
    await browser.wait(ec.visibilityOf(categoriesComponentsPage.title), 5000);
    expect(await categoriesComponentsPage.getTitle()).to.eq('kiraApp.categories.home.title');
    await browser.wait(ec.or(ec.visibilityOf(categoriesComponentsPage.entities), ec.visibilityOf(categoriesComponentsPage.noResult)), 1000);
  });

  it('should load create Categories page', async () => {
    await categoriesComponentsPage.clickOnCreateButton();
    categoriesUpdatePage = new CategoriesUpdatePage();
    expect(await categoriesUpdatePage.getPageTitle()).to.eq('kiraApp.categories.home.createOrEditLabel');
    await categoriesUpdatePage.cancel();
  });

  it('should create and save Categories', async () => {
    const nbButtonsBeforeCreate = await categoriesComponentsPage.countDeleteButtons();

    await categoriesComponentsPage.clickOnCreateButton();

    await promise.all([
      categoriesUpdatePage.setCatNameInput('catName'),
      categoriesUpdatePage.setCatDescriptionInput('catDescription'),
      categoriesUpdatePage.setCatOrderInput('5'),
    ]);

    expect(await categoriesUpdatePage.getCatNameInput()).to.eq('catName', 'Expected CatName value to be equals to catName');
    expect(await categoriesUpdatePage.getCatDescriptionInput()).to.eq(
      'catDescription',
      'Expected CatDescription value to be equals to catDescription'
    );
    expect(await categoriesUpdatePage.getCatOrderInput()).to.eq('5', 'Expected catOrder value to be equals to 5');

    await categoriesUpdatePage.save();
    expect(await categoriesUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await categoriesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Categories', async () => {
    const nbButtonsBeforeDelete = await categoriesComponentsPage.countDeleteButtons();
    await categoriesComponentsPage.clickOnLastDeleteButton();

    categoriesDeleteDialog = new CategoriesDeleteDialog();
    expect(await categoriesDeleteDialog.getDialogTitle()).to.eq('kiraApp.categories.delete.question');
    await categoriesDeleteDialog.clickOnConfirmButton();

    expect(await categoriesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
