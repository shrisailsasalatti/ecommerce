package com.merin.newboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.merin.newboot.entity.Sale;



@Repository
public interface SaleRepository extends JpaRepository<Sale, String> {
	
	
	
}