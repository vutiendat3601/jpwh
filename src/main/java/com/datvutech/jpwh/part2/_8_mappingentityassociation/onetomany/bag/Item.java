package com.datvutech.jpwh.part2._8_mappingentityassociation.onetomany.bag;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "_8_b_items")
@Entity
public class Item {
    @Getter
    @Id
    @GenericGenerator(
        name = "id_generator",
        strategy = "enhanced-sequence",
        parameters = {
            @Parameter(name = "sequence_name", value = "_8_s_items_sequence"),
            @Parameter(name = "initial_value", value = "1")
        }
    )
    private Long id;

    @Getter
    private String name;

    @JoinColumn(
        name = "item_id",
        nullable = false
    )
    @OrderColumn(name = "order_in_list", nullable = false)
    @OneToMany
    private Collection<Bid> bids = new ArrayList<>();

    public Item(String name) {
        this.name = name;
    }
}
