package com.zhihao.miao.shop.model;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 *
 * 库存数量model
 * @Date :2018/2/6
 * @since 1.0
 */
public class ProductInventory {

    //商品id
    private Integer productId;

    //库存数量
    private Long inventoryCnt;

    public ProductInventory() {

    }

    public ProductInventory(Integer productId, Long inventoryCnt) {
        this.productId = productId;
        this.inventoryCnt = inventoryCnt;
    }

    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public Long getInventoryCnt() {
        return inventoryCnt;
    }
    public void setInventoryCnt(Long inventoryCnt) {
        this.inventoryCnt = inventoryCnt;
    }
}
