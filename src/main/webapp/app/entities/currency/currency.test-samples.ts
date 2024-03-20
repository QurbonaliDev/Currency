import { ICurrency, NewCurrency } from './currency.model';

export const sampleWithRequiredData: ICurrency = {
  id: 29855,
};

export const sampleWithPartialData: ICurrency = {
  id: 3451,
  ccy: 'abnormally always',
  ccyName: 'inasmuch whoa',
  date: 'meh besides',
};

export const sampleWithFullData: ICurrency = {
  id: 21122,
  code: 'vainly yet eek',
  ccy: 'modulo',
  ccyName: 'ha',
  rate: 3192.19,
  date: 'psst',
};

export const sampleWithNewData: NewCurrency = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
