package com.merin.newboot.service;

import java.util.List;

import com.merin.newboot.entity.Sale;

public interface SaleService {
    
    List<Sale> getAllSales();
    
    Sale getSaleById(String id);
    
    Sale createSale(Sale sale);
    
   
}