package com.datvutech.jpwh.part2._6_mappinginheritance.mappedsuperclass;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass // Per subclass is a table in database
public abstract class BillingDetail {
    @Getter
    @Setter
    @NotNull
    private String owner;
}
