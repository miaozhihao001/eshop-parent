package com.zhihao.miao.shop.mapper;

import com.zhihao.miao.shop.model.ProductInventory;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 *
 * 库存数量Mapper
 * @Date :2018/2/6
 * @since 1.0
 */
public interface ProductInventoryMapper {

    //更新库存数量
    void updateProductInventory(ProductInventory productInventory);

    //根据商品id查询商品库存信息
    ProductInventory findProductInventory(@Param("productId") Integer productId);
}
