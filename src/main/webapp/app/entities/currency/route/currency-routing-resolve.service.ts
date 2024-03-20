import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICurrency } from '../currency.model';
import { CurrencyService } from '../service/currency.service';

export const currencyResolve = (route: ActivatedRouteSnapshot): Observable<null | ICurrency> => {
  const id = route.params['id'];
  if (id) {
    return inject(CurrencyService)
      .find(id)
      .pipe(
        mergeMap((currency: HttpResponse<ICurrency>) => {
          if (currency.body) {
            return of(currency.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default currencyResolve;
