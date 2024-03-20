package uz.devops.currency.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Currency.
 */
@Entity
@Table(name = "currency")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "ccy")
    private String ccy;

    @Column(name = "ccy_name")
    private String ccyName;

    @Column(name = "rate", precision = 21, scale = 2)
    private BigDecimal rate;

    @Column(name = "date")
    private String date;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Currency id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Currency code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCcy() {
        return this.ccy;
    }

    public Currency ccy(String ccy) {
        this.setCcy(ccy);
        return this;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getCcyName() {
        return this.ccyName;
    }

    public Currency ccyName(String ccyName) {
        this.setCcyName(ccyName);
        return this;
    }

    public void setCcyName(String ccyName) {
        this.ccyName = ccyName;
    }

    public BigDecimal getRate() {
        return this.rate;
    }

    public Currency rate(BigDecimal rate) {
        this.setRate(rate);
        return this;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getDate() {
        return this.date;
    }

    public Currency date(String date) {
        this.setDate(date);
        return this;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Currency)) {
            return false;
        }
        return getId() != null && getId().equals(((Currency) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Currency{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", ccy='" + getCcy() + "'" +
            ", ccyName='" + getCcyName() + "'" +
            ", rate=" + getRate() +
            ", date='" + getDate() + "'" +
            "}";
    }
}
