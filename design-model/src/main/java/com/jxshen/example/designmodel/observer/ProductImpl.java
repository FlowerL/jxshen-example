package com.jxshen.example.designmodel.observer;

/**
 * Observer model<br>
 * Observer model define a one-to-many dependency relationship<br>
 * among Objects. It let one or more observers to observe a subject<br>
 * When the state of the subject changes, system could notify all observers<br>
 * relied on this subject to update themselves automatically.<br>
 * <p>
 * reference from : http://blog.csdn.net/xsl1990/article/details/16359289<br>
 * <p>
 */
public class ProductImpl extends AbstractObservable {

    private String name;
    private double price;
    
    public ProductImpl(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        notifyAllObservers(name);
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
        notifyAllObservers(price);
    }
    
    
}
