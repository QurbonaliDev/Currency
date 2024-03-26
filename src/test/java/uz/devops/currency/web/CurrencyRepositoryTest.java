package uz.devops.currency.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uz.devops.currency.IntegrationTest;
import uz.devops.currency.domain.Currency;
import uz.devops.currency.repository.CurrencyRepository;
import uz.devops.currency.service.dto.CurrencyDTO;
import uz.devops.currency.web.rest.TestUtil;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@IntegrationTest
@AutoConfigureMockMvc
public class CurrencyRepositoryTest {

    private static final String CCY_NAME = "BAA dirhami";
    private static final String CCY = "AED";
    private static final String CODE = "784";
    private static final String CCY_NAME1 = "US Dollar";
    private static final String CCY1 = "USD";
    private static final String CODE1 = "840";
    private static final String CCY_NAME2 = "Yaman riali";
    private static final String CCY2 = "YER";
    private static final String CODE2 = "886";
    private static final BigDecimal RATE=new BigDecimal("3420.52");
    private static final BigDecimal RATE1=new BigDecimal("12561.87");
    private static final BigDecimal RATE2=new BigDecimal("50.2");
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

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
    @WithMockUser(username = "admin")
    public void testGetAllCurrencies() throws Exception {
        // Currency ni saqlash
        Long currencyId = saveCurrency(CCY_NAME1, CCY1, CODE1,RATE1);

        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/currencies/" + currencyId)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.ccyName").value(CCY_NAME1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.Ccy").value(CCY1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.Code").value(CODE1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.Rate").value(RATE1));



        Long currencyId2 = saveCurrency(CCY_NAME2, CCY2, CODE2,RATE2);

        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/currencies/" + currencyId2)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.ccyName").value(CCY_NAME2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.Ccy").value(CCY2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.Code").value(CODE2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.Rate").value(RATE2));

        Long currencyId3=saveCurrency(CCY_NAME,CCY,CODE,RATE);

        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/currencies/" + currencyId3)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.ccyName").value(CCY_NAME))
            .andExpect(MockMvcResultMatchers.jsonPath("$.Ccy").value(CCY))
            .andExpect(MockMvcResultMatchers.jsonPath("$.Code").value(CODE))
            .andExpect(MockMvcResultMatchers.jsonPath("$.Rate").value(RATE));
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void notSaveWithExistedCcy() throws Exception {
        // Currency ni saqlash
        CurrencyDTO currency = getCurrency(CCY_NAME1, CCY1, CODE1,RATE1);


        mockMvc
            .perform(post("/api/currencies")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(currency))
            )
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.ccyName").value(CCY_NAME1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.Ccy").value(CCY1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.Code").value(CODE1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.Rate").value(RATE1));

//        mockMvc
//            .perform(MockMvcRequestBuilders.post("/api/currencies/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(currency))
//            )
//            .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    private CurrencyDTO getCurrency(String ccyName, String ccy, String code, BigDecimal rate) {
        CurrencyDTO currency = new CurrencyDTO();
        currency.setCcyName(ccyName);
        currency.setCcy(ccy);
        currency.setCode(code);
        currency.setRate(rate);
        return currency;
    }


    private Long saveCurrency(String ccyName, String ccy, String code,BigDecimal rate) {
        Currency currency = new Currency();
        currency.setCcyName(ccyName);
        currency.setCcy(ccy);
        currency.setCode(code);
        currency.setRate(rate);
        currencyRepository.save(currency);
        return currency.getId();
    }

}
