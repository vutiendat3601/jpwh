package com.datvutech.jpwh.part2._8_mappingentityassociation.onetoone.foreigngenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "_8_otofg_addresses")
@Entity
public class Address {

    @Id
    @GenericGenerator(
        name = "addressKeyGenerator", 
        strategy = "foreign", // generate foreign key for id
        parameters = {
            @Parameter(name = "property", value = "user")
        }
    )
    @GeneratedValue(generator = "addressKeyGenerator")
    private Long id;

    @NotNull
    private String street;

    @NotNull
    private String zipcode;

    @NotNull
    private String city;

    @Getter
    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    private User user;

    /* Constructor */

    public Address(User user, String street, String zipcode, String city) {
        this.user = user;
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }
}
