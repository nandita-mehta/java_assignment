package com.bookstore.demo.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceDBConfig {
	public EntityManager getEntity(String tenant) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(tenant);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        return entityManager;
    }
}
