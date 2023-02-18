package com.datvutech.jpwh.part2._5_mappingvaluetype.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.datvutech.jpwh.part2._5_mappingvaluetype.type.MonetaryAmount;

// Creating custom JPA converters, to one column
@Converter(autoApply = true)
public class MonetaryAmountConverter
        implements AttributeConverter<MonetaryAmount, String> {

    @Override
    public String convertToDatabaseColumn(MonetaryAmount amount) {
        return amount.toString();
    }

    @Override
    public MonetaryAmount convertToEntityAttribute(String amountDB) {
        return MonetaryAmount.of(amountDB);
    }

}
