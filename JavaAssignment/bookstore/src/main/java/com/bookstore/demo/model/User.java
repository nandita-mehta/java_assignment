package com.bookstore.demo.model;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="user_id")
    private Long id;

    @Column(name="username")
    private String username;

    @OneToMany(targetEntity = Sales.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "user_id")
    private List<Sales> sale;

    public User() {}

    public User(String username, List<Sales> sale) {
    	super();
        this.username = username;
        this.sale = sale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public List<Sales> getSale() {
        return sale;
    }

    public void setSale(List<Sales> sale) {
        this.sale = sale;
    }   
}