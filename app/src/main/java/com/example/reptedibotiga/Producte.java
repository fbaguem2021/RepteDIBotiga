package com.example.reptedibotiga;

import java.io.Serializable;

public class Producte implements Serializable {
    private String name;
    private int image;
    private double price;
    private int inStock;
    private int inStorage;
    private boolean inDiscount;
    private int discount;
    private double discountedPrice;

    private int cantidadProductos;
    private double totPrice;

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

    public Producte(String name, int cantidadProductos, double totPrice) {
        this.name = name;
        this.cantidadProductos = cantidadProductos;
        this.totPrice = totPrice;
    }

    public void setCantidadProductos(int nuevaCantidad) {
        cantidadProductos = nuevaCantidad;
    }

    public void setTotPrice(double nuevoTotal) {
        totPrice = nuevoTotal;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public double getTotPrice() {
        return totPrice;
    }

    private double calculateDiscountedPrice(double price, int discount) {
        double toDiscount = (double) (price / 100) * discount;

        return (price - toDiscount);
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

    @Override
    public String toString(){
        return "Comprado: "+getName()+" (x"+getCantidadProductos()+") "+getTotPrice()+"€";
    }
}
