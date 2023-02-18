package com.datvutech.jpwh.part2._6_mappinginheritance.singletable;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@DiscriminatorValue("ba") // for connect with super class
@Table(name = "_6_st_bank_account")
@Entity
public class BankAccount extends BillingDetail
        implements Serializable {

    @Getter
    @Setter
    @NotNull
    private String account;

    @Getter
    @Setter
    @NotNull
    private String bankname;

    @Getter
    @Setter
    @NotNull
    private String swift;

}
