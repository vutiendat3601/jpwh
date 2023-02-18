package com.datvutech.jpwh.part2._6_mappinginheritance.tableperclass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.datvutech.jpwh.app.Constants;

import lombok.Getter;
import lombok.Setter;

// super a table, per subclass per table. Use UNION to connect
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) 
@Entity
public abstract class BillingDetail {

    @Getter
    @Id
    @GenericGenerator(
        name = "id_generator", 
        strategy = "enhanced-sequence", 
        parameters = {
            @Parameter(name = "sequence_name", value = "_6_tpcbillingdetail_sequence"),
            @Parameter(name = "initial_value", value = "1")
        }
    )
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id; // Require identifier in super class

    @Getter
    @Setter
    @NotNull
    private String owner;
}
