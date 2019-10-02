
package com.cognition.buildovov3.api.model.productEntity.construction.all;

import java.util.HashMap;
import java.util.Map;

public class AllProducts {

    private String id;
    private Brand brand;
    private Varient varient;
    private Product product;
    private Integer MRP;
    private Integer v;
    private Size size;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
        return MRP;
    }

    public void setMRP(Integer mRP) {
        this.MRP = mRP;
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
