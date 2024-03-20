import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICurrency, NewCurrency } from '../currency.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICurrency for edit and NewCurrencyFormGroupInput for create.
 */
type CurrencyFormGroupInput = ICurrency | PartialWithRequiredKeyOf<NewCurrency>;

type CurrencyFormDefaults = Pick<NewCurrency, 'id'>;

type CurrencyFormGroupContent = {
  id: FormControl<ICurrency['id'] | NewCurrency['id']>;
  code: FormControl<ICurrency['code']>;
  ccy: FormControl<ICurrency['ccy']>;
  ccyName: FormControl<ICurrency['ccyName']>;
  rate: FormControl<ICurrency['rate']>;
  date: FormControl<ICurrency['date']>;
};

export type CurrencyFormGroup = FormGroup<CurrencyFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CurrencyFormService {
  createCurrencyFormGroup(currency: CurrencyFormGroupInput = { id: null }): CurrencyFormGroup {
    const currencyRawValue = {
      ...this.getFormDefaults(),
      ...currency,
    };
    return new FormGroup<CurrencyFormGroupContent>({
      id: new FormControl(
        { value: currencyRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      code: new FormControl(currencyRawValue.code),
      ccy: new FormControl(currencyRawValue.ccy),
      ccyName: new FormControl(currencyRawValue.ccyName),
      rate: new FormControl(currencyRawValue.rate),
      date: new FormControl(currencyRawValue.date),
    });
  }

  getCurrency(form: CurrencyFormGroup): ICurrency | NewCurrency {
    return form.getRawValue() as ICurrency | NewCurrency;
  }

  resetForm(form: CurrencyFormGroup, currency: CurrencyFormGroupInput): void {
    const currencyRawValue = { ...this.getFormDefaults(), ...currency };
    form.reset(
      {
        ...currencyRawValue,
        id: { value: currencyRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): CurrencyFormDefaults {
    return {
      id: null,
    };
  }
}
