import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { KiraSharedModule } from 'app/shared/shared.module';
import { KiraCoreModule } from 'app/core/core.module';
import { KiraAppRoutingModule } from './app-routing.module';
import { KiraHomeModule } from './home/home.module';
import { KiraEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';

@NgModule({
  imports: [
    BrowserModule,
    KiraSharedModule,
    KiraCoreModule,
    KiraHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    KiraEntityModule,
    KiraAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
  providers: [
    {
      provide: LocationStrategy,
      useClass: HashLocationStrategy,
    },
  ],
})
export class KiraAppModule {}
