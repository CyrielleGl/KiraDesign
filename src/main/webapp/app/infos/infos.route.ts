import { Route } from '@angular/router';
import { AProposComponent } from 'app/infos/a-propos/a-propos.component';
import { ContactComponent } from 'app/infos/contact/contact.component';
import { MentionsLegalesComponent } from 'app/infos/mentions-legales/mentions-legales.component';

export const APROPOS_ROUTE: Route = {
  path: 'a-propos',
  component: AProposComponent,
  data: {
    authorities: [],
    pageTitle: 'a-propos.title',
  },
};

export const CONTACT_ROUTE: Route = {
  path: 'contact',
  component: ContactComponent,
  data: {
    authorities: [],
    pageTitle: 'contact.title',
  },
};

export const MENTIONSLEGALES_ROUTE: Route = {
  path: 'mentions-legales',
  component: MentionsLegalesComponent,
  data: {
    authorities: [],
    pageTitle: 'mentions-legales.title',
  },
};
