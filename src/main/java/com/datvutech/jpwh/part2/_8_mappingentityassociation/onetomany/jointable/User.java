package com.datvutech.jpwh.part2._8_mappingentityassociation.onetomany.jointable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.datvutech.jpwh.app.Constants;

import lombok.Getter;

@Entity
@Table(name = "_8_otmjt_users")
public class User {

    @Getter
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    @GenericGenerator(
        name = Constants.ID_GENERATOR,
        strategy = "enhanced-sequence",
        parameters = {
            @Parameter(name = "sequence_name", value = "_8_otmjt_users_sequence"),
            @Parameter(name = "initial_value", value = "1")
        }
    )
    private Long id;

    @OneToMany(mappedBy = "buyer")
    private Set<Item> boughtItems = new HashSet<Item>();
}
