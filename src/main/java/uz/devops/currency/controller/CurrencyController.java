package uz.devops.currency.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.devops.currency.service.CurrencyService;
import uz.devops.currency.service.dto.CurrencyDTO;


@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;
    private final Logger log = LoggerFactory.getLogger(CurrencyController.class);

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    /**
     * {@code GET /api/currencies/sync} : Synchronize currencies from external API.
     */
    @GetMapping("/sync")
    public ResponseEntity<List<CurrencyDTO>> synchronizeCurrencies() throws IOException {
        List<CurrencyDTO> currencyDTOList = currencyService.syncAndSaveToDatabase();
        return ResponseEntity.ok(currencyDTOList);
    }

    @PostMapping
    public ResponseEntity<CurrencyDTO> createCurrency(@RequestBody CurrencyDTO currencyDTO) {
        log.debug("REST request to save currency: {}", currencyDTO);
        CurrencyDTO savedCurrency = currencyService.save(currencyDTO);
        return ResponseEntity.created(URI.create("/api/currencies/" + savedCurrency.getId())).body(savedCurrency);
    }

    @GetMapping()
    public ResponseEntity<Page<CurrencyDTO>> getCurrencies(@RequestBody Pageable pageable){
        Page<CurrencyDTO> all = currencyService.findAll(pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyDTO> getCurrencyById(@PathVariable Long id) {
        Optional<CurrencyDTO> currency = currencyService.findOne(id);
        return currency.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}

