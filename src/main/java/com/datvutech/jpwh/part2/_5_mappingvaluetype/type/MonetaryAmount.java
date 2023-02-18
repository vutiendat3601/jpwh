package com.datvutech.jpwh.part2._5_mappingvaluetype.type;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

// Custom type
public class MonetaryAmount implements Serializable {
    private final BigDecimal value;
    private final Currency currency;

    public BigDecimal getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public MonetaryAmount(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    /* @Override */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final MonetaryAmount o = (MonetaryAmount) obj;
        if (!value.equals(o.value)) {
            return false;
        }
        if (!currency.equals(o.currency)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return value + " " + currency;
    }

    /* Bussiness */

    public static MonetaryAmount of(String amount) {
        String[] split = amount.split(" ");
        return new MonetaryAmount(
                new BigDecimal(split[0]),
                Currency.getInstance(split[1]));
    }


}
