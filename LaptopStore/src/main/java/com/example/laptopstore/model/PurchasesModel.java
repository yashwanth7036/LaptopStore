package com.example.laptopstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Purchases")
public class PurchasesModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "customer_email")
    private String model;
	
	@Column(name = "laptop_ids")
    private String model1;
	
	
	@Column(name = "quant")
    private int quantity;
	
	@Column(name = "Total_price")
    private double price;
	
	@Column(name = "Confirmation_number")
    private int num ;
	
	public PurchasesModel() {
		
	}

	public PurchasesModel(String model,String model1,  int quantity, double price, int num) {
		super();
		this.model = model;
		this.model1 = model1;
		this.quantity = quantity;
		this.price = price;
		this.num=num;
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
	
	public String getModel1() {
		return model;
	}

	public void setModel1(String model1) {
		this.model1 = model1;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
