
package com.cognition.buildovov3.api.model.productEntity.construction.all;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product {

    private List<String> images = null;
    private List<String> mRP = null;
    private String productStockType;
    private String id;
    private String productName;
    private String description;
    private String productCategory;
    private Integer v;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getMRP() {
        return mRP;
    }

    public void setMRP(List<String> mRP) {
        this.mRP = mRP;
    }

    public String getProductStockType() {
        return productStockType;
    }

    public void setProductStockType(String productStockType) {
        this.productStockType = productStockType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
