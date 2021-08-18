package kr.or.connect.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ProductPrice {
    private int id;
    private int productId;
    private String priceTypeName;
    private int price;
    private int discountRate;
    private String createDate;
    private String modifyDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getPriceTypeName() {
        return priceTypeName;
    }

    public void setPriceTypeName(String priceTypeName) {
        this.priceTypeName = priceTypeName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createDate = format.format(createDate);
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.modifyDate = format.format(modifyDate);
    }
}
