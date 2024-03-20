import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ICurrency } from '../currency.model';
import { CurrencyService } from '../service/currency.service';

@Component({
  standalone: true,
  templateUrl: './currency-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class CurrencyDeleteDialogComponent {
  currency?: ICurrency;

  constructor(
    protected currencyService: CurrencyService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.currencyService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
