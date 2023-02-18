package com.datvutech.jpwh.part2._8_mappingentityassociation.onetomany.bag;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Getter;

@Table(name = "_8_b_bids")
@Entity
public class Bid {
    @Getter
    @Id
    @GenericGenerator(
        name = "id_generator",
        strategy = "enhanced-sequence",
        parameters = {
            @Parameter(name = "sequence_name", value = "_8_s_bids_sequence"),
            @Parameter(name = "initial_value", value = "1")
        }
    )
    private Long id;

    @Getter
    private BigDecimal amount;

    @NotNull
    @JoinColumn(
        name = "item_id",
        insertable = false,
        updatable = false
    )
    @ManyToOne
    private Item item;

    public Bid(BigDecimal amount, Item item) {
        this.amount = amount;
        this.item = item;
    }
}
