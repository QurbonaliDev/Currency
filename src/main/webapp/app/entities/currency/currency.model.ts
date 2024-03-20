export interface ICurrency {
  id: number;
  code?: string | null;
  ccy?: string | null;
  ccyName?: string | null;
  rate?: number | null;
  date?: string | null;
}

export type NewCurrency = Omit<ICurrency, 'id'> & { id: null };
