package com.example.laptopstore.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.laptopstore.exception.CouponException;
import com.example.laptopstore.exception.ResourceNotfound;
import com.example.laptopstore.model.LappyModel;
import com.example.laptopstore.model.LaptopOrderRequest;
import com.example.laptopstore.repository.LappyRepository;


@RestController
@RequestMapping("/api/v2/")
public class LappyController {
	
		
		@Autowired
		private LappyRepository laptopRepository;
		
		
		@GetMapping("/laptops")
		public List<LappyModel> getAllLaptopInStore(){
			return laptopRepository.findAll();
		}
		
		 public long getInventoryCount(long laptopId) {
			    Optional<LappyModel> laptop = laptopRepository.findById(laptopId);
			    
			    return laptopId;
			  }
		 @PostMapping("/laptops")
		 public LappyModel createNewLaptop(@RequestBody LappyModel lappyModel) {
			 return laptopRepository.save(lappyModel);
		 }
		 
		 @GetMapping("/laptops/{id}")
		 public long getInfoOfLaptopById(@PathVariable Long id) {
			 long lappy = laptopRepository.findById(id).get().getInventory();
			 return lappy;
		 }
		 
		 @PutMapping("/laptops/{id}")
		 public ResponseEntity<LappyModel> updateLaptop(@PathVariable Long id, @RequestBody LappyModel lappyModel){
			 LappyModel lappy = laptopRepository.findById(id).orElseThrow(() -> new ResourceNotfound("no updation in lappy"));
			 lappy.setInventory(lappyModel.getInventory());
			 LappyModel updatedInventory  = laptopRepository.save(lappy);
			 return ResponseEntity.ok(updatedInventory);
		 }
		 
		  @GetMapping("/available")
		    public List<LappyModel> getAvailableLaptops() {
		        return laptopRepository.findAllByQuantityAvailableGreaterThan(0);
		    }
		  @PostMapping("/apply-discount")
		    public BigDecimal applyDiscount(@RequestBody List<LaptopOrderRequest> laptopOrders, @RequestParam(required = false) String couponCode) {
		        Map<Long, Integer> laptopQuantities = new HashMap<>();
		        BigDecimal totalPrice = BigDecimal.ZERO;

		        for (LaptopOrderRequest orderRequest : laptopOrders) {
		            LappyModel laptop = laptopRepository.findById(orderRequest.getLaptopId())
		                                      .orElseThrow(() -> new ResourceNotfound("Laptop not found"));
		            totalPrice = totalPrice.add(laptop.getPrice().multiply(BigDecimal.valueOf(orderRequest.getQuantity())));

		            laptopQuantities.put(laptop.getId(), (int) (laptopQuantities.getOrDefault(laptop.getId(), 0) + orderRequest.getQuantity()));
		        }

		        BigDecimal discount = BigDecimal.ZERO;

		        for (Map.Entry<Long, Integer> entry : laptopQuantities.entrySet()) {
		            int quantity = entry.getValue();
		            if (quantity == 1) {
		                discount = discount.add(totalPrice.multiply(BigDecimal.valueOf(0.25)));
		            } else if (quantity == 2) {
		                discount = discount.add(totalPrice.multiply(BigDecimal.valueOf(0.4)));
		            } else {
		                // Apply up to 25% discount for different models
		                discount = discount.add(totalPrice.multiply(BigDecimal.valueOf(0.25)));
		            }
		        }

//		        if ("NEWYEAR".equals(couponCode)) {
//		            // Apply additional discount for coupon code
//		            discount = discount.add(totalPrice.multiply(BigDecimal.valueOf(0.1))); // Example 10% additional discount
//		        }
		        if (couponCode != null && "NEWYEAR".equalsIgnoreCase(couponCode)) {
		            // Apply additional discount for valid coupon code
		            discount = discount.add(totalPrice.multiply(BigDecimal.valueOf(0.1)));
		        } else if (couponCode != null) {
		            // Handle invalid coupon code
		            throw new CouponException("Invalid coupon code");
		        }

		        return totalPrice.subtract(discount);
		    }
}
