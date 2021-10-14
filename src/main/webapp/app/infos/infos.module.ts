import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContactComponent } from './contact/contact.component';
import { AProposComponent } from './a-propos/a-propos.component';
import { MentionsLegalesComponent } from './mentions-legales/mentions-legales.component';
import { KiraSharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { APROPOS_ROUTE, CONTACT_ROUTE, MENTIONSLEGALES_ROUTE } from 'app/infos/infos.route';

@NgModule({
  declarations: [ContactComponent, AProposComponent, MentionsLegalesComponent],
  imports: [CommonModule, KiraSharedModule, RouterModule.forChild([APROPOS_ROUTE, CONTACT_ROUTE, MENTIONSLEGALES_ROUTE])],
})
export class InfosModule {}
