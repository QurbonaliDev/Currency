import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'currency',
    data: { pageTitle: 'currencyApp.currency.home.title' },
    loadChildren: () => import('./currency/currency.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
