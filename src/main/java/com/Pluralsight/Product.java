package com.Pluralsight;

public class Product {

    private String productId;
    private String productName;
    private double productPrice;

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public Product(String productId, String productName, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String toString() {
        return String.format("%s | %s | %.2f",
                productId, productName, productName);
    }
}
