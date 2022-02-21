package com.bookstore.demo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.bookstore.demo.model.User;

public class UserDAO {

	private EntityManager entityManager;

	public UserDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void deleteUser(Long id, String tenant_id) {
		Query query = (Query) entityManager.createQuery("delete from user where id=:userId");
		query.setParameter("userId", id);
		query.executeUpdate();
		entityManager.getTransaction().commit();
	}
	
	public User getUser(Long id, String tenant_id) {
		User user = entityManager.find(User.class, id);
		return user;
	}
	
	public List<User> getUsers(String tenant_id) {
		Query query = (Query) entityManager.createQuery("from user");
		List<User> users = query.getResultList();
		return users;
	}

	public User saveUser(User user, String tenant_id) {
		User dbUser = entityManager.merge(user);
		user.setId(dbUser.getId());
		entityManager.getTransaction().commit();
		return user;
	}
}