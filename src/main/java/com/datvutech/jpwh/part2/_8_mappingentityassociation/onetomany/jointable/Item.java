package com.datvutech.jpwh.part2._8_mappingentityassociation.onetomany.jointable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.datvutech.jpwh.app.Constants;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "_8_otojt_items")
@Entity
public class Item {
    @Getter
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    @GenericGenerator(
        name = Constants.ID_GENERATOR, strategy = "enhanced-sequence", 
        parameters = {
            @Parameter(name = "sequence_name", value = "_8_otojt_items_sequence"),
            @Parameter(name = "initial_value", value = "1")
        }
    )
    private Long id;

    @Getter
    @Setter
    private String name;

    @ManyToOne
    @JoinTable(
        name = "_8_jt_items_buyers",
        joinColumns =
        @JoinColumn(name = "item_id"),
        inverseJoinColumns =
        @JoinColumn(nullable = false)
    )
    private User buyer;
}
