package com.datvutech.jpwh.part2._8_mappingentityassociation.onetoone.sharedprimarykey;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.datvutech.jpwh.app.Constants;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "_8_otospk_addresses")
@Entity
public class Address {
    @Getter
    @Id
    @GenericGenerator(
        name = "id_generator", 
        strategy = "enhanced-sequence",
        parameters = {
            @Parameter(name = "sequence_name", value = "_8_otospk_addresses_sequence"),
            @Parameter(name = "initial_value", value = "1")
        }
    )
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @NotNull
    private String street;

    @NotNull
    private String zipcode;

    @NotNull
    private String city;

    /* Constructor */

    public Address(String street, String zipcode, String city) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }
}
