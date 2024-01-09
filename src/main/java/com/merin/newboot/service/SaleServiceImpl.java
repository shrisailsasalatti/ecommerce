package com.merin.newboot.service;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.merin.newboot.entity.Sale;
import com.merin.newboot.repository.SaleRepository;



@Service
public class SaleServiceImpl implements SaleService {
	
	Logger logger=LoggerFactory.getLogger(SaleServiceImpl.class);

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public List<Sale> getAllSales() {
    	
    	logger.info("sale1....");
        return saleRepository.findAll();
    }

    @Override
    public Sale getSaleById(String id) {
    	
        return saleRepository.findById(id).orElse(null);
    }

    @Override
    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }
}