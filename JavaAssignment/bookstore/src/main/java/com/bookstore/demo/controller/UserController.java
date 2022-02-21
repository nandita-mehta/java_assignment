package com.bookstore.demo.controller;

import java.util.List;
import javax.persistence.EntityManager;

import com.bookstore.demo.dao.UserDAO;
import com.bookstore.demo.model.User;
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
public class UserController {

	PersistenceDBConfig persistenceDbConfig = new PersistenceDBConfig();

	@PostMapping(value = "/user")
	public User addUser(@RequestBody User theUser, @RequestHeader(value = "tenant") String tenant_id) {
		EntityManager entityManager = persistenceDbConfig.getEntity(tenant_id);
		UserDAO userDAO = new UserDAO(entityManager);
		return (userDAO.saveUser(theUser, tenant_id));
	}

	@DeleteMapping(value = "/user/{userId}")
	public String deleteUser(@PathVariable Long userId, @RequestHeader(value = "tenant") String tenant_id) {
		EntityManager entityManager = persistenceDbConfig.getEntity(tenant_id);
		UserDAO userDAO = new UserDAO(entityManager);
		User tempUser = userDAO.getUser(userId, tenant_id);
		if (tempUser == null) {
			throw new RuntimeException("User Id not found");
		}
		userDAO.deleteUser(userId, tenant_id);
		return "Deleted user id " + userId;
	}

	@GetMapping("/user")
	public ResponseEntity<List<User>> getUser(@RequestHeader(value = "tenant") String tenant_id) {
		EntityManager entityManager = persistenceDbConfig.getEntity(tenant_id);
		UserDAO userDAO = new UserDAO(entityManager);
		return new ResponseEntity<List<User>>(userDAO.getUsers(tenant_id), HttpStatus.OK);
	}

	@PutMapping(value = "/user")
	public User updateUser(@RequestBody User user, @RequestHeader(value = "tenant") String tenant_id) {
		EntityManager entityManager = persistenceDbConfig.getEntity(tenant_id);
		UserDAO userDAO = new UserDAO(entityManager);
		User new_user = userDAO.getUser(user.getId(), tenant_id);
		if (new_user == null) {
			throw new RuntimeException("User to update doesn't exist");
		}
		return (userDAO.saveUser(user, tenant_id));
	}
}