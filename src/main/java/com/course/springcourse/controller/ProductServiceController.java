package com.course.springcourse.controller;

import com.course.springcourse.dto.Address;
import com.course.springcourse.dto.Products;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
public class ProductServiceController {

    /*@GetMapping
    public String getProducts() {
        return "get products was called!";
    }*/

    @GetMapping
    public String getProductPageLimit(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        return "get products was called with page = " + page + " and limit = " + limit;
    }

    @GetMapping(path = "/products")
    public String getProductPageLimitSort(@RequestParam(value = "page", defaultValue = "1") int page,
                                          @RequestParam(value = "limit", defaultValue = "50") int limit,
                                          @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
        return "get products was called with page = " + page + " and limit = " + limit + " and sort by " + sort;
    }

    @GetMapping(path = "/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @GetMapping(path = "/products_list")
    public ResponseEntity<Products> getProductList() {
        String[] name = {"Lala", "Bombom", "Tiara"};
        name[2] = "";
        for (int i=0; i<name.length; i++) {
            System.out.println(name[i]);
        }
        HashMap<String, Object> response = new HashMap<>();
        ArrayList<Object> addressList = new ArrayList<Object>();
        Products products = new Products();
        products.setId(1);
        products.setProduct_name("Kue Nastar");
        products.setProduct_desc("Kue untuk Lebaran");
        products.setProduct_price(25000.00);
        Address address = new Address();
        Address address1 = new Address();
        address.setId(1);
        address.setAddress("Jakarta");
        address1.setId(2);
        address1.setAddress("Bekasi");
        addressList.add(address);
        addressList.add(address1);
        response.put("product", products);
        response.put("address", addressList);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
