package com.datvutech.jpwh.part2._5_mappingvaluetype.entity;

import java.sql.Blob;
import java.sql.Clob;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.Type;

import com.datvutech.jpwh.app.Constants;
import com.datvutech.jpwh.part2._5_mappingvaluetype.type.embeddable.Address;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
public class User {
    @Getter
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    protected Long id;

    @Getter
    @Setter
    private Address homeAddress;

    // @Embedded // Only need when use third-party
    @Getter
    @Setter
    @AttributeOverrides({
        @AttributeOverride(
            name = "street", 
            column = @Column(name = "billing_street")
        ),
        @AttributeOverride(
            name = "zipcode",
            column = @Column(name = "billing_zipcode")
        ),
        @AttributeOverride(
            name = "city",
            column = @Column(name = "billing_city")
        )
    })
    private Address billingAddress;

    // @Lob // map to Blob data type
    // protected byte[] image;
    
    // @Lob // map to Clob data type
    // protected String description;

    @Lob // map to Blob data type
    private Blob image; // binary large object

    @Lob // map to Clob data type
    private Clob description; // character large object

    @Type(type = "yes_no") // Change default mapping
    private boolean verified = false;
}
