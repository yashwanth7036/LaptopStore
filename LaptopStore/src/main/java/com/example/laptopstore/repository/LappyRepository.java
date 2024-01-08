package com.example.laptopstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.laptopstore.model.LappyModel;



@Repository
public interface LappyRepository extends JpaRepository<LappyModel, Long>{
	List<LappyModel> findAllByQuantityAvailableGreaterThan(long inventory);
}
