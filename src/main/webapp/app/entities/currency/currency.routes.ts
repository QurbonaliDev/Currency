import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { CurrencyComponent } from './list/currency.component';
import { CurrencyDetailComponent } from './detail/currency-detail.component';
import { CurrencyUpdateComponent } from './update/currency-update.component';
import CurrencyResolve from './route/currency-routing-resolve.service';

const currencyRoute: Routes = [
  {
    path: '',
    component: CurrencyComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CurrencyDetailComponent,
    resolve: {
      currency: CurrencyResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CurrencyUpdateComponent,
    resolve: {
      currency: CurrencyResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CurrencyUpdateComponent,
    resolve: {
      currency: CurrencyResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default currencyRoute;
