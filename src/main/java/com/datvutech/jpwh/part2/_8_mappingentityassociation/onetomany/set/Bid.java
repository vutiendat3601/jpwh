package com.datvutech.jpwh.part2._8_mappingentityassociation.onetomany.set;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.datvutech.jpwh.app.Constants;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "_8_otms_bids")
@Entity
public class Bid {

    @Getter
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    @GenericGenerator(
        name = Constants.ID_GENERATOR,
        strategy = "enhanced-sequence",
        parameters = {
            @Parameter(name = "sequence_name", value = "_8_otms_bids_sequence"),
            @Parameter(name = "initial_value", value = "1")
        }
    )
    private Long id;

    @Getter
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id"/* , nullable = false */)
    private Item item;

    /* Constructor */

    // Bid always requires a Item
    public Bid(BigDecimal amount, Item item) {
        this.amount = amount;
        this.item = item;
        item.addBid(this);
    }
}
