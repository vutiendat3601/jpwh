package com.datvutech.jpwh.part2._6_mappinginheritance.mappedsuperclass;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.datvutech.jpwh.app.Constants;

import lombok.Getter;
import lombok.Setter;

@Table(name = "_6_msc_credit_card")
@Entity
public class CreditCard extends BillingDetail
        implements Serializable {

    @Getter
    @Id
    @GenericGenerator(name = "id_generator", strategy = "enhanced-sequence", parameters = {
            @Parameter(name = "sequence_name", value = "_6_creditcard_sequence"),
            @Parameter(name = "initial_value", value = "1")
    })
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

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
