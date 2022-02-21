package com.bookstore.demo.controller;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.demo.dao.SalesDAO;
import com.bookstore.demo.model.Sales;
import com.bookstore.demo.utils.PersistenceDBConfig;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesController {

	PersistenceDBConfig persistenceDbConfig = new PersistenceDBConfig();

    @GetMapping("/sales")
    public ResponseEntity<List<Sales>> getSale(@RequestHeader (value = "tenant") String tenant_id) {
        EntityManager entityManager = persistenceDbConfig.getEntity(tenant_id);
        SalesDAO salesDAO = new SalesDAO(entityManager);
        return new ResponseEntity<List<Sales>>(salesDAO.getSales(tenant_id), HttpStatus.OK);
    }

    @PostMapping(value = "/sales")
    public Sales addSale(@RequestBody Sales sale, @RequestHeader (value = "tenant") String tenant_id) {
        EntityManager entityManager = persistenceDbConfig.getEntity(tenant_id);
        SalesDAO salesDAO = new SalesDAO(entityManager);
        return(salesDAO.saveSale(sale, tenant_id));
    }

    @PutMapping(value = "/sales")
    public Sales updatSale(@RequestBody Sales sale, @RequestHeader (value = "tenant") String tenant_id) {
        EntityManager entityManager = persistenceDbConfig.getEntity(tenant_id);
        SalesDAO salesDAO = new SalesDAO(entityManager);
        Sales new_sale = salesDAO.getSale(sale.getSale_Id(), tenant_id);
        if(new_sale == null) {
            throw new RuntimeException("Sale to update doesn't exist");
        }
        return (salesDAO.saveSale(sale, tenant_id));
    }

    @DeleteMapping(value = "/sales/{salesId}")
    public String deleteUser(@PathVariable Long salesId, @RequestHeader (value = "tenant") String tenant_id) {
        EntityManager entityManager = persistenceDbConfig.getEntity(tenant_id);
        SalesDAO salesDAO = new SalesDAO(entityManager);
        Sales tempSales = salesDAO.getSale(salesId, tenant_id);
        if(tempSales == null){
            throw new RuntimeException("Sales Id not found");
        }
        salesDAO.deleteSale(salesId, tenant_id);
        return "Deleted sales id " + salesId;
    }
}