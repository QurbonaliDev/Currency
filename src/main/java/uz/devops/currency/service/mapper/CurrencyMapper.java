package uz.devops.currency.service.mapper;

import org.mapstruct.*;
import uz.devops.currency.domain.Currency;
import uz.devops.currency.service.dto.CurrencyDTO;

/**
 * Mapper for the entity {@link Currency} and its DTO {@link CurrencyDTO}.
 */
@Mapper(componentModel = "spring")
public interface CurrencyMapper extends EntityMapper<CurrencyDTO, Currency> {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "ccy", target = "ccy")
    @Mapping(source = "ccyName", target = "ccyName")
    @Mapping(source = "rate", target = "rate")
    @Mapping(source = "date", target = "date")
    Currency toEntity(CurrencyDTO dto);
}
