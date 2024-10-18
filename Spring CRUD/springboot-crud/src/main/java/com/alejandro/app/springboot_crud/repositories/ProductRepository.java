package com.alejandro.app.springboot_crud.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alejandro.app.springboot_crud.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

    

}
