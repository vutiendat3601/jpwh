package com.datvutech.jpwh.part2._4_mappingclass.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@DynamicInsert // Disable generation of INSERT SQL statements on startup
@DynamicUpdate // Disable generation of UPDATE SQL statements on startup
@Table(name = "_4_items")
@Entity
public class Item implements Serializable {

    @Getter
    @Id // Mark the identifier property of an entity class
    @GeneratedValue(generator = "id_generator") // Generate a primary key value when save
    protected Long id;

}
