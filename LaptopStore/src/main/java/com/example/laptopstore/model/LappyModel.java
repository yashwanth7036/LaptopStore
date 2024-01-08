package com.example.laptopstore.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "laptop_store")
public class LappyModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "laptop_model")
    private String model;
    
    @Column(name = "laptop_invent")
    private long inventory;
    
    @Column(name = "laptop_price")
    private BigDecimal price;
    
    @Column(nullable = false)
    private int quantityAvailable;

    
    public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public LappyModel() {
    	
    }
    
    public LappyModel(String model, int inventory, BigDecimal price) {
    	super();
    	this.model=model;
    	this.inventory=inventory;
    	this.price=price;
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public long getInventory() {
		return inventory;
	}

	public void setInventory(long inventory) {
		this.inventory = inventory;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}



}
