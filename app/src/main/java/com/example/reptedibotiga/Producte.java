package com.example.reptedibotiga;

public class Producte {
    private final String name;
    private final int image;
    private final float pricce;
    private final int inStock;
    private final boolean inDiscount;
    private int discount;

    public Producte(String name, int image, float pricce, int inStock, boolean inDiscount, int discount) {
        this.name = name;
        this.image = image;
        this.pricce = pricce;
        this.inStock = inStock;
        this.inDiscount = inDiscount;
        this.discount = discount;
    }

    public Producte(String name, int image, float pricce, int inStock, boolean inDiscount) {
        this.name = name;
        this.image = image;
        this.pricce = pricce;
        this.inStock = inStock;
        this.inDiscount = inDiscount;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public float getPricce() {
        return pricce;
    }

    public int getInStock() {
        return inStock;
    }

    public boolean isInDiscount() {
        return inDiscount;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "Producte{" + "name='" + name + '\'' + ", image=" + image + ", pricce=" + pricce +
                ", inStock=" + inStock + ", inDiscount=" + inDiscount + ", discount=" + discount +
                '}';
    }
}
