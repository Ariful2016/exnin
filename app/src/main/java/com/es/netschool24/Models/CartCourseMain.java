package com.es.netschool24.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartCourseMain {

    @SerializedName("carts")
    @Expose
    private List<Cart> carts = null;
    @SerializedName("payTotal")
    @Expose
    private Integer payTotal;

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public Integer getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(Integer payTotal) {
        this.payTotal = payTotal;
    }

}