
package com.cognition.buildovov3.api.model.productEntity.construction.product;

import com.cognition.buildovov3.api.model.productEntity.construction.search.Brand;
import com.cognition.buildovov3.api.model.productEntity.construction.search.Varient;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MRP {
    @Override
    public String toString() {
        return "MRP{" +
                "id='" + id + '\'' +
                ", product='" + product + '\'' +
                ", MRP=" + MRP +
                ", v=" + v +
                '}';
    }

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("brand")
    @Expose
    private com.cognition.buildovov3.api.model.productEntity.construction.search.Brand brand;
    @SerializedName("varient")
    @Expose
    private com.cognition.buildovov3.api.model.productEntity.construction.search.Varient varient;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("MRP")
    @Expose
    private Integer MRP;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public com.cognition.buildovov3.api.model.productEntity.construction.search.Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public com.cognition.buildovov3.api.model.productEntity.construction.search.Varient getVarient() {
        return varient;
    }

    public void setVarient(Varient varient) {
        this.varient = varient;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getMRP() {
        return MRP;
    }

    public void setMRP(Integer MRP) {
        this.MRP = MRP;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
