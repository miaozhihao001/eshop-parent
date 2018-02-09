package com.zhihao.miao.cache.model;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 *
 * 商品信息
 * @Date :2018/2/8
 * @since 1.0
 */
public class ProductInfo {

    private Long id;
    private String name;
    private Double price;

    public ProductInfo() {

    }

    public ProductInfo(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
