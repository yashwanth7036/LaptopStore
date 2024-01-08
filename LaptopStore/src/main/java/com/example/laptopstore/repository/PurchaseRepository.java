package com.example.laptopstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.laptopstore.model.LappyModel;
import com.example.laptopstore.model.PurchasesModel;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchasesModel, Long>{

}
