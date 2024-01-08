package com.example.laptopstore.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LaptoptotController {
	  private static Map<String, Integer> inventory = new HashMap<>();

	    static {
	        // Initialize inventory with available laptops
	        inventory.put("model1", 40);
	        inventory.put("model2", 40);
	        // Add more laptop models and quantities as needed
	    }

	    public static boolean selectLaptop(String model, int quantity) {
	        return inventory.containsKey(model) && inventory.get(model) >= quantity;
	    }

	    public static double applyDiscount(int quantity, double totalPrice, boolean isSameModel, String couponCode) {
	        if (isSameModel && quantity == 2) {
	            totalPrice *= 0.6; // 40% off for two of the same model
	        } else if (!isSameModel) {
	            totalPrice *= 0.75; // 25% off for different models
	        }

	        if ("NEWYEAR".equals(couponCode)) {
	            totalPrice *= 0.9; // Additional 10% off with coupon code
	        }

	        return totalPrice;
	    }

	    public static String completePurchase(String model, int quantity, boolean isSameModel, String couponCode) {
	        if (selectLaptop(model, quantity)) {
	            double totalPrice = quantity * getLaptopPrice(model);
	            totalPrice = applyDiscount(quantity, totalPrice, isSameModel, couponCode);

	            // Update inventory
	            inventory.put(model, inventory.get(model) - quantity);

	            // Save the purchase record in the database
	            savePurchaseRecord(model, quantity, totalPrice);

	            // Send confirmation email with a confirmation number
	            sendConfirmationEmail(model, quantity, totalPrice);

	            return "Purchase successful. Total price: $" + totalPrice;
	        } else {
	            return "Laptop not available in the requested quantity.";
	        }
	    }

	    private static double getLaptopPrice(String model) {
	        // Add logic to fetch the actual price from your system
	        // For simplicity, assuming all laptops have the same price
	        return 1000;
	    }

	    private static void savePurchaseRecord(String model, int quantity, double totalPrice) {
	        // Add logic to save the purchase record in your database
	        System.out.println("Purchase record saved.");
	    }

	    private static void sendConfirmationEmail(String model, int quantity, double totalPrice) {
	        // Add logic to send a confirmation email to the customer
	        int confirmationNumber = Objects.hash(model, quantity, totalPrice);
	        System.out.println("Email sent. Confirmation Number: " + confirmationNumber);
	    }

	    public static void main(String[] args) {
	        // Example usage
	        String model = "model1";
	        int quantity = 2;
	        boolean isSameModel = true;
	        String couponCode = "NEWYEAR";

	        String result = completePurchase(model, quantity, isSameModel, couponCode);
	        System.out.println(result);
	    }
}
