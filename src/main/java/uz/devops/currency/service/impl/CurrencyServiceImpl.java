package uz.devops.currency.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import uz.devops.currency.domain.Currency;
import uz.devops.currency.repository.CurrencyRepository;
import uz.devops.currency.service.CurrencyService;
import uz.devops.currency.service.dto.CurrencyDTO;
import uz.devops.currency.service.mapper.CurrencyMapper;

/**
 * Service Implementation for managing {@link uz.devops.currency.domain.Currency}.
 */
@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

    private final Logger log = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    private final CurrencyRepository currencyRepository;
    private final RestTemplate restTemplate;
    private final CurrencyMapper currencyMapper;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, RestTemplate restTemplate, CurrencyMapper currencyMapper) {
        this.currencyRepository = currencyRepository;
        this.restTemplate = restTemplate;
        this.currencyMapper = currencyMapper;
    }

    @Override
    public CurrencyDTO save(CurrencyDTO currencyDTO) {
        log.debug("Request to save Currency : {}", currencyDTO);
        Currency currency = currencyMapper.toEntity(currencyDTO);
        currency = currencyRepository.save(currency);
        return currencyMapper.toDto(currency);
    }

    @Override
    public CurrencyDTO update(CurrencyDTO currencyDTO) {
        log.debug("Request to update Currency : {}", currencyDTO);
        Currency currency = currencyMapper.toEntity(currencyDTO);
        currency = currencyRepository.save(currency);
        return currencyMapper.toDto(currency);
    }

    @Override
    public Optional<CurrencyDTO> partialUpdate(CurrencyDTO currencyDTO) {
        log.debug("Request to partially update Currency : {}", currencyDTO);

        return currencyRepository
            .findById(currencyDTO.getId())
            .map(existingCurrency -> {
                currencyMapper.partialUpdate(existingCurrency, currencyDTO);

                return existingCurrency;
            })
            .map(currencyRepository::save)
            .map(currencyMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CurrencyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Currencies");
        return currencyRepository.findAll(pageable).map(currencyMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CurrencyDTO> findOne(Long id) {
        log.debug("Request to get Currency : {}", id);
        return currencyRepository.findById(id).map(currencyMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Currency : {}", id);
        currencyRepository.deleteById(id);
    }

    @Override
    public List<CurrencyDTO> sync() {
        ResponseEntity<List<CurrencyDTO>> response = restTemplate.exchange(
            "https://cbu.uz/uz/arkhiv-kursov-valyut/json/",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    public List<CurrencyDTO> syncAndSaveToDatabase() throws IOException {
        ResponseEntity<List<CurrencyDTO>> response = restTemplate.exchange(
            "https://cbu.uz/uz/arkhiv-kursov-valyut/json/",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {}
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            List<CurrencyDTO> currencyDTOList = response.getBody();
            saveToDatabase(currencyDTOList);
        } else {
            System.out.println("HTTP status code: " + response.getStatusCode());
        }
        return response.getBody();
    }

    @Override
    public Currency getCurrencyById(Long currencyId) {
        Currency currency = new Currency();
        currency.getId();
        return currency;
    }

    private void saveToDatabase(List<CurrencyDTO> currencyDTOList) {
        List<Currency> currencyList = mapToEntities(currencyDTOList);
        currencyRepository.saveAll(currencyList);
    }

    private List<Currency> mapToEntities(List<CurrencyDTO> currencyDTOList) {
        List<Currency> currencyList = new ArrayList<>();
        for (CurrencyDTO currencyDTO : currencyDTOList) {
            Currency currency = new Currency();
            currency.setId(currencyDTO.getId());
            currency.setCode(currencyDTO.getCode());
            currency.setCcy(currencyDTO.getCcy());
            currency.setCcyName(currencyDTO.getCcyName());
            currencyList.add(currency);
        }
        return currencyList;
    }
}
