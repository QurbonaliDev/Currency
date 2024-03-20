package uz.devops.currency.web.rest;

import java.io.IOException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.devops.currency.service.CurrencyService;
import uz.devops.currency.service.dto.CurrencyDTO;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyResource {

    private final CurrencyService currencyService;

    public CurrencyResource(CurrencyService currencyService) {
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
}
