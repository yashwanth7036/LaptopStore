package com.example.laptopstore.model;

public class LaptopOrderRequest {
	 private Long laptopId;
	    private long quantity;
		public Long getLaptopId() {
			return laptopId;
		}
		public void setLaptopId(Long laptopId) {
			this.laptopId = laptopId;
		}
		public long getQuantity() {
			return quantity;
		}
		public void setQuantity(long quantity) {
			this.quantity = quantity;
		}
}
