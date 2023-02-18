package com.datvutech.jpwh.part2._6_mappinginheritance.joined;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

// Create primary key, also foreign key depends on super class
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "_6_j_credit_card")
@Entity
public class CreditCard extends BillingDetail
        implements Serializable {

    @Getter
    @Setter
    @NotNull
    @Column(name = "card_number")
    private String cardNumber;

    @Getter
    @Setter
    @NotNull
    @Column(name = "exp_month")
    private String expMonth;

    @Getter
    @Setter
    @NotNull
    @Column(name = "exp_year")
    private String expYear;
}
