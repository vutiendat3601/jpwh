package com.datvutech.jpwh.part2._5_mappingvaluetype.type;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.hibernate.usertype.DynamicParameterizedType;

public class MonetaryAmountUserType
        implements CompositeUserType, DynamicParameterizedType {

    private final String DEFAULT_CURRENCY_VALUE = "USD";
    private Currency convertTo;

    @Override
    public void setParameterValues(Properties params) {
        
        // ParameterType paramType = (ParameterType) params.get(PARAMETER_TYPE);
        // String[] columns = parameterType.getColumns();
        // String table = parameterType.getTable();
        // Annotation[] annotations = parameterType.getAnnotationsMethod();

        String convertToParam = params.getProperty("convertTo");
        this.convertTo = Currency.getInstance(
            convertToParam != null ? convertToParam : DEFAULT_CURRENCY_VALUE
        );
    }

    @Override
    public String[] getPropertyNames() {
        return new String[] { "value", "currency" };
    }

    @Override
    public Type[] getPropertyTypes() {
        return new Type[] {
            StandardBasicTypes.BIG_DECIMAL,
            StandardBasicTypes.CURRENCY
        };
    }

    @Override
    public Object getPropertyValue(
            Object component, int property
        ) throws HibernateException {
        MonetaryAmount amount = (MonetaryAmount) component;
        if (property == 0) {
            return amount.getValue();
        }
        else {
            return amount.getCurrency();
        }
    }

    @Override
    public void setPropertyValue(
            Object component, int property, Object value
        ) throws HibernateException {
        throw new UnsupportedOperationException("MonetaryAmount is immutable");
    }

    @Override
    public Class<?> returnedClass() {
        return MonetaryAmount.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x == y || !(x == null || y == null) && x.equals(y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(
            ResultSet rs, String[] names, 
            SharedSessionContractImplementor session, Object owner
        ) throws HibernateException, SQLException {
        // Value be retrieved from the database and need to convert to UserType

        if (rs.wasNull()) {
            return null;
        }
        BigDecimal amount = rs.getBigDecimal(names[0]);
        Currency currency = Currency.getInstance(names[1]);
        return new MonetaryAmount(amount, currency);
    }

    @Override
    public void nullSafeSet(
            PreparedStatement ps, Object value, int index, 
            SharedSessionContractImplementor session
        ) throws HibernateException, SQLException {
        // value has to be stored in the database and need to convert to Database type

        if (value == null) {
            ps.setNull(index++, StandardBasicTypes.BIG_DECIMAL.sqlType());
            ps.setNull(index, StandardBasicTypes.CURRENCY.sqlType());
        } else {
            MonetaryAmount amount = (MonetaryAmount) value;
            MonetaryAmount dbAmount = convert(amount, convertTo); // Bussiness code
            ps.setBigDecimal(index++, dbAmount.getValue());
            ps.setString(index, convertTo.getCurrencyCode());
        }
    }

    private MonetaryAmount convert(
            MonetaryAmount amount, Currency toCurrency
        ) {
        return new MonetaryAmount(
            amount.getValue().multiply(new BigDecimal(2)),
            toCurrency
        );
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(
            Object value, 
            SharedSessionContractImplementor session
        ) throws HibernateException {
        // Stores a value in the global shared second-level cache
        return value.toString();
    }

    @Override
    public Object assemble(
            Serializable cached, 
            SharedSessionContractImplementor session, 
            Object owner
        ) throws HibernateException {
        // Reads the serialized representation from the global shared second-level cache.
        return MonetaryAmount.of((String) cached);
    }

    @Override
    public Object replace(
            Object original, Object target, SharedSessionContractImplementor session,
            Object owner
        ) throws HibernateException {
        // Called during EntityManager#merge() operations
        // If value type is immutable, return the original
        return original;
    }

}
