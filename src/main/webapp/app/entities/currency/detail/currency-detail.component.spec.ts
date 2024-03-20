import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { CurrencyDetailComponent } from './currency-detail.component';

describe('Currency Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CurrencyDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: CurrencyDetailComponent,
              resolve: { currency: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(CurrencyDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load currency on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', CurrencyDetailComponent);

      // THEN
      expect(instance.currency).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
