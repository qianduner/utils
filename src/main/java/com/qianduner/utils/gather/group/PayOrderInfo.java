package com.qianduner.utils.gather.group;//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//import AOSCodec;
//import U;
//import RRException;
//import Dto;
//import Dtos;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class PayOrderInfo {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    public String createPayOrder(String subject, String body, String payType, String orderCode, String totalAmount, String uid, String key, String wallet, String url, String type,String point) {
//        Dto dto = Dtos.newDto();
//        dto.put("subject", subject);
//        dto.put("body", body);
//        dto.put("pay_type", payType);
//        dto.put("out_trade_no", orderCode);
//        dto.put("total_amount", "" + totalAmount);
//        Map orderPay = new HashMap();
//        orderPay.put("orderCode", orderCode);
//        orderPay.put("url", url);
//        orderPay.put("type", type);
//        orderPay.put("point", point);
//        G payResult = restTemplate.postForObject("http://finance-server/pay/order/notify/return", U.getHttpEntity(orderPay), G.class);
//        if (payResult.getCode() != 0)
//            throw new RRException("订单创建报错" + payResult.toJson());
//        return AOSCodec.encryptDES(dto.toJson(), uid + key + wallet);
//    }
//}
