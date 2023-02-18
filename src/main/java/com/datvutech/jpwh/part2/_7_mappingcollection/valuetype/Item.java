package com.datvutech.jpwh.part2._7_mappingcollection.valuetype;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.datvutech.jpwh.app.Constants;

@Table(name = "_7_vt_items")
@Entity
public class Item {

    @Id
    @GenericGenerator(
        name = "id_generator", 
        strategy = "enhanced-sequence",
        parameters = {
            @Parameter(name = "sequence_name", value = "_7_vt_items_sequence"),
            @Parameter(name = "initial_value", value = "1")
        }
    )
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    
    @CollectionTable(
        name = "_7_vt_items_setimages", 
        joinColumns = @JoinColumn(name = "item_id")
    ) // create a table and connect with items by joinColumns
    @ElementCollection // Marks property as a value type
    @Column(name = "file_name") // column in CollectionTable
    private Set<String> setImages = new HashSet<String>();

    @CollectionTable(
        name = "_7_vt_items_bagimages", 
        joinColumns =  @JoinColumn(name = "item_id")
    ) // create a table and connect with items by joinColumns
    @GenericGenerator(
        name = "list_images_generator", 
        strategy = "enhanced-sequence",
        parameters = {
            @Parameter(name = "sequence_name", value = "_7_vt_items_listimages_sequence"),
            @Parameter(name = "initial_value", value = "1")
        }
    )
    @CollectionId(
        columns = @Column(name = "image_id"),
        type = @Type(type = "long"),
        generator = "list_images_generator"
    ) // create primary key for CollectionTable
    @ElementCollection
    @Column(name = "file_name") // column in CollectionTable
    private Collection<String> bagImages = new ArrayList<String>();
    @OrderColumn(name = "order_in_list") // Set a collumn to keep order in list start from 0
    @CollectionTable(
        name = "_7_vt_items_listimages",
        joinColumns =  @JoinColumn(name = "item_id")
    ) // create a table and connect with items by joinColumns
    @ElementCollection
    @Column(name = "file_name")
    private List<String> listImages = new ArrayList<String>();

    @ElementCollection
    @CollectionTable(
        name = "_7_vt_items_mapimages",
        joinColumns = @JoinColumn(name = "item_id")
    ) // create a table and connect with items by joinColumns
    @MapKeyColumn(name = "file_name")
    @Column(name = "path")
    private Map<String, String> mapImages = new HashMap<String, String>();
}
