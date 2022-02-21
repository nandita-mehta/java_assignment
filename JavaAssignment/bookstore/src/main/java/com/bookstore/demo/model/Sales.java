package com.bookstore.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long sale_id;

    public Sales() {}

    public Sales(Long sale_id) {
        this.sale_id = sale_id;
    }

    public long getSale_Id() {
        return sale_id;
    }

    public void setSale_Id(Long sale_id){
        this.sale_id = sale_id;
    }    
}
