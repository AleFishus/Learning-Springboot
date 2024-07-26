package com.alejandro.springboot.di.factura.springboot_difactura;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alejandro.springboot.di.factura.springboot_difactura.models.Item;
import com.alejandro.springboot.di.factura.springboot_difactura.models.Product;

@PropertySource("classpath:data.properties")

@Configuration
public class AppConfig {

    @Bean
    List<Item> itemsInvoice(){
        Product p1 = new Product("Motherboard", 3000);
        Product p2 = new Product("M2 Ultra Speed", 2450);
        return Arrays.asList(new Item(p1 , 2), new Item(p2, 4) );
    }

    @Bean("default")
    List<Item> itemsInvoiceOffice(){
        Product p1 = new Product("Monitor Asus 24", 750);
        Product p2 = new Product("Notebook Razer", 2400);
        Product p3 = new Product("Impresora HP", 800);
        Product p4 = new Product("Escritorio Oficina", 350);
        return Arrays.asList(new Item(p1 , 4), new Item(p2, 6), new Item(p3 , 1), new Item(p4, 3) );
    }
}
