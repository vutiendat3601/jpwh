package com.datvutech.jpwh.part2._8_mappingentityassociation.onetoone.foreigngenerator;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@Table(name = "_8_otofg_users")
@Entity
public class User {
    @Getter
    @Id
    @GenericGenerator(
        name = "id_generator", 
        strategy = "enhanced-sequence",
        parameters = {
            @Parameter(name = "sequence_name", value = "_8_otofg_users_sequence"),
            @Parameter(name = "initial_value", value = "1")
        }
    )
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @Setter
    @Getter
    @OneToOne(
        mappedBy = "user",
        fetch = FetchType.LAZY
    )
    @Cascade({ CascadeType.SAVE_UPDATE })
    private Address shippingAddress;

    @Getter
    @Setter
    private String username;

    public User(String username) {
        this.username = username;
    }
}
