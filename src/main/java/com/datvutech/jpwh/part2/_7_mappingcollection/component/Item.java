package com.datvutech.jpwh.part2._7_mappingcollection.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.datvutech.jpwh.app.Constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "_7_c_items")
@Entity
public class Item {

    @Id
    @GenericGenerator(
        name = Constants.ID_GENERATOR, 
        strategy = "enhanced-sequence",
        parameters = {
            @Parameter(name = "sequence_name", value = "_7_c_items_sequence"),
            @Parameter(name = "initial_value", value = "1")
        }
    )
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @ElementCollection
    @CollectionTable(
        name = "_7_c_items_setimages",
        joinColumns = @JoinColumn(name = "item_id")
    ) // create a table and connect with items by joinColumns
    @AttributeOverride(
        name = "path", 
        column = @Column(name="file_name", nullable = false)
    )
    private Set<Image> setImages = new HashSet<Image>();

    @ElementCollection
    @CollectionTable(
        name = "_7_c_items_bagimages",
        joinColumns = @JoinColumn(name = "item_id")
    ) // create a table and connect with items by joinColumns
    @GenericGenerator(
        name = Constants.ID_GENERATOR, 
        strategy = "enhanced-sequence",
        parameters = {
            @Parameter(name = "sequence_name", value = "_7_c_items_bagimages_sequence"),
            @Parameter(name = "initial_value", value = "1")
        }
    )
    @CollectionId(
        columns = @Column(name = "image_id"),
        type = @Type(type = "long"),
        generator = Constants.ID_GENERATOR
    ) // create primary key for CollectionTable
    @AttributeOverride(
        name = "path", 
        column = @Column(name="file_name", nullable = false)
    )
    private Collection<Image> bagImages = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
        name = "_7_c_items_mapimages",
        joinColumns = @JoinColumn(name = "item_id")
    )
    @MapKeyColumn(name = "file_name")
    private Map<String, Image> mapImages = new HashMap<String, Image>();
}
