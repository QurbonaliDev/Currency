package uz.devops.currency.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uz.devops.currency.domain.Currency;
import uz.devops.currency.repository.CurrencyRepository;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfiguration
public class CurrencyRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CurrencyRepository currencyRepository;

    @BeforeEach
    void setUp() {
        currencyRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        currencyRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void testGetAllCurrencies() throws Exception {
        Currency currency1 = new Currency();
        currency1.setCcyName("US Dollar");
        currency1.setCcy("USD");
        currency1.setCode("864");

        currencyRepository.save(currency1);
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/currencies").contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].ccyName").value("US Dollar"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].ccy").value("USD"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("864"));

        List<Currency> currencies = currencyRepository.findAll();
        assertThat(currencies).hasSize(1);
    }
}
