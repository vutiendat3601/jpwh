package com.datvutech.jpwh.part2._8_mappingentityassociation.onetoone.sharedprimarykey;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.datvutech.jpwh.part2._8_mappingentityassociation.onetoone.foreigngenerator.Address;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "_8_otospk_users")
@Entity
public class User {
    @Id
    private Long id;

    @Setter
    @Getter
    @OneToOne(
        fetch = FetchType.LAZY, 
        optional = false, 
        cascade = { CascadeType.ALL }
    )
    // Selects shared primary key strategy for OneToOne relationship
    // make User#id foreign key
    @PrimaryKeyJoinColumn
    private Address shippingAddress;

    @Getter
    @Setter
    private String username;

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
