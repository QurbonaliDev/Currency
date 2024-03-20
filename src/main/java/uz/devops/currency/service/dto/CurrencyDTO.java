package uz.devops.currency.service.dto;

import static uz.devops.currency.domain.Currency_.ccyName;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link uz.devops.currency.domain.Currency} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CurrencyDTO implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("CcyNm_RU")
    private String ccyNm_RU;

    @JsonProperty("CcyNm_UZC")
    private String ccyNm_UZC;

    @JsonProperty("CcyNm_EN")
    private String ccyNm_EN;

    @JsonProperty("Nominal")
    private String nominal;

    @JsonProperty("Diff")
    private String diff;

    @JsonProperty("Code")
    private String code;

    @JsonProperty("Ccy")
    private String ccy;

    @JsonProperty("CcyNm_UZ")
    private String ccyNm_UZ;

    @JsonProperty("Rate")
    private String rate;

    @JsonProperty("Date")
    private String date;

    private String ccyName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getCcyName() {
        return ccyName;
    }

    public void setCcyName(String ccyName) {
        this.ccyName = ccyName;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = String.valueOf(rate);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CurrencyDTO)) {
            return false;
        }

        CurrencyDTO currencyDTO = (CurrencyDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, currencyDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CurrencyDTO{" +
            "id=" + getId() +
            ", Code='" + getCode() + "'" +
            ", ccy='" + getCcy() + "'" +
            ", ccyName='" + getCcyName() + "'" +
            ", rate=" + getRate() +
            ", date='" + getDate() + "'" +
            "}";
    }
}
