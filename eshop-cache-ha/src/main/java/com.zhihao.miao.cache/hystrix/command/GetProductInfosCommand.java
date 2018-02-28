package com.zhihao.miao.cache.hystrix.command;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import com.zhihao.miao.cache.model.ProductInfo;
import com.zhihao.miao.cache.util.HttpClientUtils;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class GetProductInfosCommand extends HystrixObservableCommand<ProductInfo> {

    private String[] productIds;

    public GetProductInfosCommand(String[] productIds) {
        //将command放到了这个group中去执行，所有的请求都会放到这个线程池中去执行
        super(HystrixCommandGroupKey.Factory.asKey("GetProductInfoGroup"));
        this.productIds = productIds;
    }

    @Override
    protected Observable<ProductInfo> construct() {
        return Observable.create(new Observable.OnSubscribe<ProductInfo>() {

            public void call(Subscriber<? super ProductInfo> observer) {
                try {
                    for(String productId : productIds) {
                        String url = "http://127.0.0.1:8082/getProductInfo?productId=" + productId;
                        String response = HttpClientUtils.sendGetRequest(url);
                        ProductInfo productInfo = JSONObject.parseObject(response, ProductInfo.class);
                        observer.onNext(productInfo);
                    }
                    observer.onCompleted();
                } catch (Exception e) {
                    observer.onError(e);
                }
            }

        }).subscribeOn(Schedulers.io());
    }

}

