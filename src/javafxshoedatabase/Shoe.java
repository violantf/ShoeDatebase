/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxshoedatabase;

/**
 *
 * @author owner
 */
public class Shoe {
    
    final public static int NAME_SIZE = 30;
    final public static int BRAND_SIZE = 30;
    final public static int STYLE_SIZE = 30;
    final public static int ID_SIZE = NAME_SIZE + BRAND_SIZE + STYLE_SIZE;
    
    private String ID;
    private String name;
    private String brand;
    private double size;
    private int stock;
    private String style;
    
    public Shoe() {
    }
    
    public Shoe(String name, String brand, double size, int stock, String style) {
      this.name = name;
      this.brand = brand;
      this.size = size;
      this.stock = stock;
      this.style = style;
      this.ID = brand.toUpperCase() + name.toUpperCase() + style.toUpperCase();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
