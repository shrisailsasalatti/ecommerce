package com.merin.newboot.controller;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.merin.newboot.entity.Sale;
import com.merin.newboot.service.SaleService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@Tag(name = "Sale Controller", description = "Sale Management Portal")
public class SaleController {

    Logger logger = LoggerFactory.getLogger(SaleController.class);

    @Autowired
    private SaleService saleService;

    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        logger.info("Fetching all sales");
        List<Sale> sales = saleService.getAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable("id") String id) {
        logger.info("Fetching sale with id: {}", id);

        Sale sale = saleService.getSaleById(id);

        if (sale != null) {
            logger.info("Sale found: {}", sale);
            return new ResponseEntity<>(sale, HttpStatus.OK);
        } else {
            logger.warn("Sale with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        Sale createdSale = saleService.createSale(sale);
        return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
    }
}
