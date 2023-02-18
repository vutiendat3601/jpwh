package com.datvutech.jpwh.part2._6_mappinginheritance.tableperclass;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Table(name = "_6_tpc_bank_account")
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
