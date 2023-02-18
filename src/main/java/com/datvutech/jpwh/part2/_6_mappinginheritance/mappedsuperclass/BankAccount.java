package com.datvutech.jpwh.part2._6_mappinginheritance.mappedsuperclass;

import java.io.Serializable;

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

@Table(name = "_6_msc_bank_account")
@Entity
public class BankAccount extends BillingDetail
        implements Serializable {

    @Getter
    @Id
    @GenericGenerator(
        name = "id_generator", 
        strategy = "enhanced-sequence", 
        parameters = {
            @Parameter(name = "sequence_name", value = "_6_bankaccount_sequence"),
            @Parameter(name = "initial_value", value = "1")
        }
    )
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

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
