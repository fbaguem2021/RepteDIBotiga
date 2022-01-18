package com.example.reptedibotiga;

import java.io.Serializable;

public class Producte implements Serializable {
    private final String name;
    private final int image;
    private final double price;
    private final int inStock;
    private final int inStorage;
    private final boolean inDiscount;
    private int discount;
    private double discountedPrice;

    public Producte(String name, int image, double price, int inStock, int inStorage, boolean inDiscount, int discount) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.inStock = inStock;
        this.inStorage = inStorage;
        this.inDiscount = inDiscount;
        this.discount = discount;
        this.discountedPrice = calculateDiscountedPrice(price, discount);
    }

    public Producte(String name, int image, double price, int inStock, int inStorage, boolean inDiscount) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.inStock = inStock;
        this.inStorage = inStorage;
        this.inDiscount = inDiscount;
    }

    private double calculateDiscountedPrice(double price, int discount) {
        double toDiscount = (double) (price / 100) * discount;

        return ( price - toDiscount );
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    public int getInStock() {
        return inStock;
    }

    public int getInStorage() {
        return inStorage;
    }

    public boolean isInDiscount() {
        return inDiscount;
    }

    public int getDiscount() {
        return discount;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }
}
