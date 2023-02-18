package com.datvutech.jpwh.part2._8_mappingentityassociation.onetoone.jointable;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.datvutech.jpwh.app.Constants;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor/* (access = AccessLevel.PACKAGE) */
@Table(name = "_8_otojt_shipments")
@Entity
public class Shipment implements Serializable {
    
    @Getter
    @Id
    @GenericGenerator(
        name = "id_generator", 
        strategy = "enhanced-sequence", 
        parameters = {
            @Parameter(name = "sequence_name", value = "_8_otojt_shipments_sequence"),
            @Parameter(name = "initial_value", value = "1")
        }
    )
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(
        name = "_8_otojt_items_shipments", 
        joinColumns = @JoinColumn(name = "shipment_id"), 
        inverseJoinColumns = {
            @JoinColumn(
                name = "item_id", 
                nullable = false, 
                unique = true
            )
        }
    )
    private Item auction;

    public Shipment(Item auction) {
        this.auction = auction;
    }
}
