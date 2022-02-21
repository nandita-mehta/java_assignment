package com.bookstore.demo.controller;

import java.util.List;

import javax.persistence.EntityManager;

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

import com.bookstore.demo.dao.BookDAO;
import com.bookstore.demo.model.Book;
import com.bookstore.demo.utils.PersistenceDBConfig;

@RestController
public class BookController {

	PersistenceDBConfig persistenceDbConfig = new PersistenceDBConfig();

	@PostMapping("/book")
	public Book addBook(@RequestBody Book book, @RequestHeader("tenant") String tenant_id) {
		EntityManager entityManager = persistenceDbConfig.getEntity(tenant_id);
		BookDAO bookDAO = new BookDAO(entityManager);
		return bookDAO.saveBook(book, tenant_id);
	}

	@DeleteMapping(value = "/book/{bookId}")
	public String deleteBook(@PathVariable Long bookId, @RequestHeader(value = "tenant") String tenant_id) {
		EntityManager entityManager = persistenceDbConfig.getEntity(tenant_id);
		BookDAO bookDAO = new BookDAO(entityManager);
		Book tempBook = bookDAO.getBook(bookId, tenant_id);
		if (tempBook == null) {
			throw new RuntimeException("Book not found");
		}
		bookDAO.deleteBook(bookId, tenant_id);
		return "Deleted book with id " + bookId;
	}

	@GetMapping("/book")
	public ResponseEntity<List<Book>> getBook(@RequestHeader(value = "tenant") String tenant_id) {
		EntityManager entityManager = persistenceDbConfig.getEntity(tenant_id);
		BookDAO bookDAO = new BookDAO(entityManager);
		return new ResponseEntity<List<Book>>(bookDAO.getBooks(tenant_id), HttpStatus.OK);
	}

	@PutMapping(value = "/book")
	public Book updateBook(@RequestBody Book book, @RequestHeader(value = "tenant") String tenant_id) {
		EntityManager entityManager = persistenceDbConfig.getEntity(tenant_id);
		BookDAO bookDAO = new BookDAO(entityManager);
		Book new_book = bookDAO.getBook(book.getId(), tenant_id);
		if (new_book == null) {
			throw new RuntimeException("Book not found");
		}
		return (bookDAO.saveBook(book, tenant_id));
	}
}