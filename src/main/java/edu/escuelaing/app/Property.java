package edu.escuelaing.app;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Property entity class that represents a real estate property.
 * Contains basic information about properties including address, price, size
 * and description.
 */
@Entity
@Table(name = "properties")
public class Property {

    /**
     * The unique identifier for the property.
     * Generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The address of the property.
     */
    @Column(nullable = false)
    private String address;

    /**
     * The price of the property.
     */
    @Column(nullable = false)
    private Double price;

    /**
     * The size of the property in square meters.
     */
    @Column(nullable = false)
    private Double size;

    /**
     * A description of the property.
     */
    @Column(length = 1000)
    private String description;

    /**
     * Default constructor required by JPA.
     */
    public Property() {
    }

    /**
     * Constructor with all fields except ID.
     *
     * @param address     the property address
     * @param price       the property price
     * @param size        the property size
     * @param description the property description
     */
    public Property(String address, Double price, Double size, String description) {
        this.address = address;
        this.price = price;
        this.size = size;
        this.description = description;
    }

    /**
     * Gets the property ID.
     *
     * @return the property ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the property ID.
     *
     * @param id the property ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the property address.
     *
     * @return the property address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the property address.
     *
     * @param address the property address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the property price.
     *
     * @return the property price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the property price.
     *
     * @param price the property price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Gets the property size.
     *
     * @return the property size
     */
    public Double getSize() {
        return size;
    }

    /**
     * Sets the property size.
     *
     * @param size the property size
     */
    public void setSize(Double size) {
        this.size = size;
    }

    /**
     * Gets the property description.
     *
     * @return the property description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the property description.
     *
     * @param description the property description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}