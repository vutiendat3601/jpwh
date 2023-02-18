package com.datvutech.jpwh.part2._6_mappinginheritance.singletable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.datvutech.jpwh.app.Constants;

import lombok.Getter;
import lombok.Setter;

// super a table, per subclass per table. Use UNION to connect
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "bd_type") // for which subclass to connect for the row
@Table(name = "_6_st_billing_detail")
@Entity
public abstract class BillingDetail {

    @Getter
    @Id
    @GenericGenerator(
        name = "id_generator", 
        strategy = "enhanced-sequence", 
        parameters = {
            @Parameter(name = "sequence_name", value = "_6_stbillingdetail_sequence"),
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
