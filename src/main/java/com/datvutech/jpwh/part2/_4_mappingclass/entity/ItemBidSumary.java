package com.datvutech.jpwh.part2._4_mappingclass.entity;

import javax.persistence.Entity;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Synchronize({ "Item", "Bid" })
@Immutable // no public setter methods for any properties (all set in constructor)
@Entity
@Subselect("") // create an application-level view,
public class ItemBidSumary {

    @Getter
    private Long itemId;

    @Getter
    private String name;

    @Getter
    private long numberOfBids;

}
