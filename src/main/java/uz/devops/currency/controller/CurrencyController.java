package uz.devops.currency.controller;

import java.net.URI;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.devops.currency.domain.Currency;
import uz.devops.currency.repository.CurrencyRepository;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    private final CurrencyRepository currencyRepository;

    public CurrencyController(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @PostMapping
    public ResponseEntity<Currency> createCurrency(@RequestBody Currency currency) {
        Currency savedCurrency = currencyRepository.save(currency);
        return ResponseEntity.created(URI.create("/api/currencies/")).body(savedCurrency);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Currency>> getCurrencyById(@PathVariable Long id) {
        Optional<Currency> currency = currencyRepository.findById(id);

        if (currency != null) {
            return ResponseEntity.ok(currency);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
