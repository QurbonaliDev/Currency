package uz.devops.currency.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static uz.devops.currency.domain.CurrencyTestSamples.*;

import org.junit.jupiter.api.Test;
import uz.devops.currency.web.rest.TestUtil;

class CurrencyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Currency.class);
        Currency currency1 = getCurrencySample1();
        Currency currency2 = new Currency();
        assertThat(currency1).isNotEqualTo(currency2);

        currency2.setId(currency1.getId());
        assertThat(currency1).isEqualTo(currency2);

        currency2 = getCurrencySample2();
        assertThat(currency1).isNotEqualTo(currency2);
    }
}
