package com.datvutech.jpwh.part2._5_mappingvaluetype.type.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Embeddable // Embeddable is component, has no identifier property
public class Address {

    @Getter @Setter
    @NotNull
    @Column(nullable = false)
    private String street;

    @Getter @Setter
    @NotNull
    @Column(nullable = false, length = 5)
    private String zipcode;

    @Getter @Setter
    @NotNull
    @Column(nullable = false)
    private String city;

    public Address(String street, String zipcode, String city) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }
}
