package com.bookstore.demo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.bookstore.demo.model.Book;

public class BookDAO {

	private EntityManager entityManager;

	public BookDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void deleteBook(Long id, String tenant_id) {
		Query query = (Query) entityManager.createQuery("delete from book where book_id=:id");
		query.setParameter("book_id", id);
		query.executeUpdate();
		entityManager.getTransaction().commit();
	}

	public Book getBook(Long id, String tenant_id) {
		Book book = entityManager.find(Book.class, id);
		return book;
	}

	public List<Book> getBooks(String tenant_id) {
		Query query = (Query) entityManager.createQuery("from book");
		List<Book> books = query.getResultList();
		return books;
	}

	public Book saveBook(Book book, String tenant_id) {
		Book new_book = entityManager.merge(book);
		book.setId(new_book.getId());
		entityManager.getTransaction().commit();
		return new_book;
	}
}