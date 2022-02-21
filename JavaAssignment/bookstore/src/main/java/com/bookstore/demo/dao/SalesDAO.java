package com.bookstore.demo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.bookstore.demo.model.Sales;

public class SalesDAO {

	private EntityManager entityManager;

	public SalesDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void deleteSale(Long id, String tenant_id) {
		Query query = (Query) entityManager.createQuery("delete from Sale where id=:salesId");
		query.setParameter("salesId", id);
		query.executeUpdate();
		entityManager.getTransaction().commit();
	}
	
	public Sales getSale(Long id, String tenant_id) {
		Sales sales = entityManager.find(Sales.class, id);
		return sales;
	}
	
	public List<Sales> getSales(String tenant_id) {
		Query query = (Query) entityManager.createQuery("from Sale");
		List<Sales> sales = query.getResultList();
		return sales;
	}

	public Sales saveSale(Sales sales, String tenant_id) {
		Sales dbSale = entityManager.merge(sales);
		sales.setSale_Id(dbSale.getSale_Id());
		entityManager.getTransaction().commit();
		return sales;
	}
}