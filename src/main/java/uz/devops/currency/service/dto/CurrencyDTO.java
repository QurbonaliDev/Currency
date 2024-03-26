package uz.devops.currency.service.dto;

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
    private BigDecimal rate;

    @JsonProperty("Date")
    private String date;

    private String ccyName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCcyNm_RU() {
        return ccyNm_RU;
    }

    public void setCcyNm_RU(String ccyNm_RU) {
        this.ccyNm_RU = ccyNm_RU;
    }

    public String getCcyNm_UZC() {
        return ccyNm_UZC;
    }

    public void setCcyNm_UZC(String ccyNm_UZC) {
        this.ccyNm_UZC = ccyNm_UZC;
    }

    public String getCcyNm_EN() {
        return ccyNm_EN;
    }

    public void setCcyNm_EN(String ccyNm_EN) {
        this.ccyNm_EN = ccyNm_EN;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
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

    public String getCcyNm_UZ() {
        return ccyNm_UZ;
    }

    public void setCcyNm_UZ(String ccyNm_UZ) {
        this.ccyNm_UZ = ccyNm_UZ;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCcyName() {
        return ccyName;
    }

    public void setCcyName(String ccyName) {
        this.ccyName = ccyName;
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
            "id=" + id +
            ", ccyNm_RU='" + ccyNm_RU + '\'' +
            ", ccyNm_UZC='" + ccyNm_UZC + '\'' +
            ", ccyNm_EN='" + ccyNm_EN + '\'' +
            ", nominal='" + nominal + '\'' +
            ", diff='" + diff + '\'' +
            ", code='" + code + '\'' +
            ", ccy='" + ccy + '\'' +
            ", ccyNm_UZ='" + ccyNm_UZ + '\'' +
            ", rate=" + rate +
            ", date='" + date + '\'' +
            ", ccyName='" + ccyName + '\'' +
            '}';
    }


}
