package com.course.springcourse.controllers;

import com.course.springcourse.dto.Address;
import com.course.springcourse.dto.Products;
import com.course.springcourse.dto.ResponseMessage;
import com.course.springcourse.model.entities.FileInfo;
import com.course.springcourse.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2")
public class ProductServiceController {

    @Autowired
    FileStorageService storageService;

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

    @GetMapping(path = "/user/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> getUserId(@PathVariable String userId) {
        String firstName = "tiara";
        int firstNameLength = firstName.length();
        if(userId.equals("5") && firstNameLength == 5) {
            System.out.println("access");
            return new ResponseEntity("200", HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
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

    // create controller for upload & download file
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(ProductServiceController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


}
