/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class Order {
    
public int orderNo;
    public String name;
    public String contact;
    public Meal meal;
    public String diningType;

    public Order(int orderNo, String name, String contact, Meal meal, String diningType) {
        this.orderNo = orderNo;
        this.name = name;
        this.contact = contact;
        this.meal = meal;
        this.diningType = diningType;
    }
    
    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public void setDiningType(String diningType) {
        this.diningType = diningType;
    }

    // Optional: add getter methods if needed later
    public Meal getMeal() {
        return meal;
    }

    public String getDiningType() {
        return diningType;
    }
}


