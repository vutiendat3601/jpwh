package com.datvutech.jpwh.part2._8_mappingentityassociation.onetomany.set;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.datvutech.jpwh.app.Constants;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "_8_otms_items")
@Entity
public class Item {

    @Getter
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    @GenericGenerator(
        name = Constants.ID_GENERATOR, strategy = "enhanced-sequence", 
        parameters = {
            @Parameter(name = "sequence_name", value = "_8_otms_items_sequence"),
            @Parameter(name = "initial_value", value = "1")
        }
    )
    private Long id;

    @Getter
    @Setter
    private String name;

    @Cascade(
        {
            CascadeType.SAVE_UPDATE,
            CascadeType.DELETE
        }
    )
    // If bidirectional, mapBy is required, orphanRemoval for deleting element in
    // collection
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, orphanRemoval = true)
    // @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Bid> bids = new HashSet<>();

    public void addBid(Bid bid) {
        bids.add(bid);
    }

    public void clearAllBids() {
        bids.clear();
    }

    public Item(String name) {
        this.name = name;
    }
}
