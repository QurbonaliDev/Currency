package uz.devops.currency.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CurrencyTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Currency getCurrencySample1() {
        return new Currency().id(1L).code("code1").ccy("ccy1").ccyName("ccyName1").date("date1");
    }

    public static Currency getCurrencySample2() {
        return new Currency().id(2L).code("code2").ccy("ccy2").ccyName("ccyName2").date("date2");
    }

    public static Currency getCurrencyRandomSampleGenerator() {
        return new Currency()
            .id(longCount.incrementAndGet())
            .code(UUID.randomUUID().toString())
            .ccy(UUID.randomUUID().toString())
            .ccyName(UUID.randomUUID().toString())
            .date(UUID.randomUUID().toString());
    }
}
