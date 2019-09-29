
package com.cognition.buildovov3.api.model.productEntity.construction.search;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchedProduct {

    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("MRP")
    @Expose
    private List<MRP> mRP = null;
    @SerializedName("productStockType")
    @Expose
    private String productStockType;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("productCategory")
    @Expose
    private String productCategory;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<MRP> getMRP() {
        return mRP;
    }

    public void setMRP(List<MRP> mRP) {
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

}
