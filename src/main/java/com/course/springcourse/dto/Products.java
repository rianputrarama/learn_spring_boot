package com.course.springcourse.dto;

public class Products {

    private int id;
    private String product_name;
    private String product_desc;
    private double product_price;

    public Products(int id, String product_name, String product_desc, double product_price) {
        this.id = id;
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_price = product_price;
    }

    public Products() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", product_name='" + product_name + '\'' +
                ", product_desc='" + product_desc + '\'' +
                ", product_price=" + product_price +
                '}';
    }
}
