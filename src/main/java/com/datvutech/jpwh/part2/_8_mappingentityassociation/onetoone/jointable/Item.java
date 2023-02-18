package com.datvutech.jpwh.part2._8_mappingentityassociation.onetoone.jointable;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class Item implements Serializable {
    @Id
    @GenericGenerator(
        name = "id_generator", 
        strategy = "enhanced-sequence", 
        parameters = {
            @Parameter(name = "sequence_name", value = "_8_otojt_items_sequence"),
            @Parameter(name = "initial_value", value = "1")
        }
    )
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @Getter
    @Setter
    private String name;

    public Item(String name) {
        this.name = name;
    }
}
