import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ICurrency } from '../currency.model';
import { CurrencyService } from '../service/currency.service';
import { CurrencyFormService, CurrencyFormGroup } from './currency-form.service';

@Component({
  standalone: true,
  selector: 'jhi-currency-update',
  templateUrl: './currency-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CurrencyUpdateComponent implements OnInit {
  isSaving = false;
  currency: ICurrency | null = null;

  editForm: CurrencyFormGroup = this.currencyFormService.createCurrencyFormGroup();

  constructor(
    protected currencyService: CurrencyService,
    protected currencyFormService: CurrencyFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ currency }) => {
      this.currency = currency;
      if (currency) {
        this.updateForm(currency);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const currency = this.currencyFormService.getCurrency(this.editForm);
    if (currency.id !== null) {
      this.subscribeToSaveResponse(this.currencyService.update(currency));
    } else {
      this.subscribeToSaveResponse(this.currencyService.create(currency));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICurrency>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(currency: ICurrency): void {
    this.currency = currency;
    this.currencyFormService.resetForm(this.editForm, currency);
  }
}
