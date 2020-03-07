
package com.cognition.buildovov3.api.model.productEntity.construction.products;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("weight_quantity")
    @Expose
    private String weightQuantity;
    @SerializedName("MRP")
    @Expose
    private Integer mRP;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("BrandId")
    @Expose
    private Integer brandId;
    @SerializedName("CategoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("SubCategoryId")
    @Expose
    private Integer subCategoryId;
    @SerializedName("Brand")
    @Expose
    private Brand brand;
    @SerializedName("SubCategory")
    @Expose
    private SubCategory subCategory;
    @SerializedName("Category")
    @Expose
    private Category category;
    private final static long serialVersionUID = -1407769159839818981L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeightQuantity() {
        return weightQuantity;
    }

    public void setWeightQuantity(String weightQuantity) {
        this.weightQuantity = weightQuantity;
    }

    public Integer getMRP() {
        return mRP;
    }

    public void setMRP(Integer mRP) {
        this.mRP = mRP;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
