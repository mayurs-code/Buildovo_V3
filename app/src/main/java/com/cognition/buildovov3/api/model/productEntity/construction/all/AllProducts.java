
package com.cognition.buildovov3.api.model.productEntity.construction.all;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllProducts {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("brand")
    @Expose
    private Brand brand;
    @SerializedName("varient")
    @Expose
    private Varient varient;
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("MRP")
    @Expose
    private Integer mRP;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("size")
    @Expose
    private Size size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Varient getVarient() {
        return varient;
    }

    public void setVarient(Varient varient) {
        this.varient = varient;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getMRP() {
        return mRP;
    }

    public void setMRP(Integer mRP) {
        this.mRP = mRP;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

}
