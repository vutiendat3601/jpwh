package com.datvutech.jpwh.part2._5_mappingvaluetype.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.datvutech.jpwh.app.Constants;
import com.datvutech.jpwh.part2._5_mappingvaluetype.converter.MonetaryAmountConverter;
import com.datvutech.jpwh.part2._5_mappingvaluetype.type.AuctionType;
import com.datvutech.jpwh.part2._5_mappingvaluetype.type.MonetaryAmount;
import com.datvutech.jpwh.part2._5_mappingvaluetype.type.MonetaryAmountUserType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@TypeDefs({
        @TypeDef(
            name = "monetary_amount_usd",
            typeClass = MonetaryAmountUserType.class,
            parameters = {
                @Parameter(name="convertTo", value = "USD"),
            }
        ),
        @TypeDef(
            name = "monetary_amount_eur",
            typeClass = MonetaryAmountUserType.class,
            parameters = {
                @Parameter(name="convertTo", value = "USD"),
            }
        )
    }
)
@Table(name = "_5_items")
@Entity
public class Item {
    @Id
    @GenericGenerator(
        name = "id_generator", 
        strategy = "enhanced-sequence", 
        parameters = {
            @Parameter(name = "sequence_name", value = "_5_items_sequence"),
            @Parameter(name = "initial_value", value = "1")
        }
    )
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @Getter
    @Setter
    @Access(AccessType.PROPERTY) // AccessType.PROPERTY requires getter, setter for loading and storing
    private String name;

    @Getter
    @Setter
    // Formula - calculate at runtime by evaluating an SQL expression, read-only
    @Formula("concat(substr(description, 1, 12), ' ...')") // `description` is a column of Item.
    private String description;

    // Formula - calculate at runtime by evaluating an SQL expression, read-only
    @Formula("(SELECT avg(b.amount) FROM bids b WHERE b.item_id = id)") // `id` is a column of Item.
    private BigDecimal averageBidAmount;

    // ColumnTransformer - convert the value of the database column
    @ColumnTransformer(
        read = "weight_g / 1000", 
        write = "? * 1000" // contain exactly one '?' placeholder for the value
    )
    @Column(name = "weight_g")
    private double weight_kg;

    @Temporal(TemporalType.TIMESTAMP) // save Date as timestamp in database
    @Generated(GenerationTime.ALWAYS) // Refreshes the entity instance after every SQL UPDATE/INSERT
    @Column(name = "last_modified", insertable = false, updatable = false) // don't allow INSERT/UPDATE depends on
                                                                           // database triggers
    private Date lastModified;

    // @Basic(optional = false) // Seldom to use, optional=false is require not null
    @ColumnDefault("1.00")
    @Generated(GenerationTime.INSERT)
    @Column(
        name = "initial_price", 
        nullable = false, 
        insertable = false // Don't allow INSERT
    )
    private BigDecimal initialPrice;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp // Generated value timestamp
    // @UpdateTimestamp
    @Column(name = "created_on")
    private Date createdOn;

    @NotNull
    @Enumerated(EnumType.STRING) // Convert enum value to string in database
    @Column(name = "auction_type")
    protected AuctionType auctionType = AuctionType.HIGHEST_BID;

    @NotNull
    @Convert(converter = MonetaryAmountConverter.class, disableConversion = false)
    @Column(name = "price", length = 64)
    private MonetaryAmount buyNowPrice;

    @NotNull
    @Type(type = "monetary_amount_usd")
    @Columns(columns = {
            @Column(name = "buynow_amount"),
            @Column(name = "buynow_currency")
        }
    )
    private MonetaryAmount buyNowPrice2Colums;

    @NotNull
    @Type(type = "monetary_amount_eur")
    @Columns(columns = {
            @Column(name = "initial_amount"),
            @Column(name = "initial_currency")
        }
    )
    private MonetaryAmount initialPrice2Colums;
}
