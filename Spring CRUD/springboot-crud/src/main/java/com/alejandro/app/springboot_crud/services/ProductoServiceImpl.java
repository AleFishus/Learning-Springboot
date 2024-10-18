package com.alejandro.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.app.springboot_crud.entities.Product;
import com.alejandro.app.springboot_crud.repositories.ProductRepository;

@Service
public class ProductoServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Transactional
     @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> optionalProductDb = repository.findById(id);
        
        if(optionalProductDb.isPresent()){
            Product productDb = optionalProductDb.get();

            productDb.setName(product.getName());
            productDb.setDescription(product.getDescription());
            productDb.setPrice(product.getPrice());
            return Optional.of(repository.save(productDb));
        }
        return optionalProductDb;
    }

    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> optionalProduct = repository.findById(id);
        optionalProduct.ifPresent(productDb -> repository.delete(productDb));

        return optionalProduct;
    }

   

}
