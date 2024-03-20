package uz.devops.currency.service.mapper;

import org.junit.jupiter.api.BeforeEach;

class CurrencyMapperTest {

    private CurrencyMapper currencyMapper;

    @BeforeEach
    public void setUp() {
        currencyMapper = new CurrencyMapperImpl();
    }
}
