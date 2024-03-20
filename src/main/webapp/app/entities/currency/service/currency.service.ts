import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICurrency, NewCurrency } from '../currency.model';

export type PartialUpdateCurrency = Partial<ICurrency> & Pick<ICurrency, 'id'>;

export type EntityResponseType = HttpResponse<ICurrency>;
export type EntityArrayResponseType = HttpResponse<ICurrency[]>;

@Injectable({ providedIn: 'root' })
export class CurrencyService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/currencies');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(currency: NewCurrency): Observable<EntityResponseType> {
    return this.http.post<ICurrency>(this.resourceUrl, currency, { observe: 'response' });
  }

  update(currency: ICurrency): Observable<EntityResponseType> {
    return this.http.put<ICurrency>(`${this.resourceUrl}/${this.getCurrencyIdentifier(currency)}`, currency, { observe: 'response' });
  }

  partialUpdate(currency: PartialUpdateCurrency): Observable<EntityResponseType> {
    return this.http.patch<ICurrency>(`${this.resourceUrl}/${this.getCurrencyIdentifier(currency)}`, currency, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICurrency>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICurrency[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCurrencyIdentifier(currency: Pick<ICurrency, 'id'>): number {
    return currency.id;
  }

  compareCurrency(o1: Pick<ICurrency, 'id'> | null, o2: Pick<ICurrency, 'id'> | null): boolean {
    return o1 && o2 ? this.getCurrencyIdentifier(o1) === this.getCurrencyIdentifier(o2) : o1 === o2;
  }

  addCurrencyToCollectionIfMissing<Type extends Pick<ICurrency, 'id'>>(
    currencyCollection: Type[],
    ...currenciesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const currencies: Type[] = currenciesToCheck.filter(isPresent);
    if (currencies.length > 0) {
      const currencyCollectionIdentifiers = currencyCollection.map(currencyItem => this.getCurrencyIdentifier(currencyItem)!);
      const currenciesToAdd = currencies.filter(currencyItem => {
        const currencyIdentifier = this.getCurrencyIdentifier(currencyItem);
        if (currencyCollectionIdentifiers.includes(currencyIdentifier)) {
          return false;
        }
        currencyCollectionIdentifiers.push(currencyIdentifier);
        return true;
      });
      return [...currenciesToAdd, ...currencyCollection];
    }
    return currencyCollection;
  }
}
