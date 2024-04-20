package com.gxl.design.strategy.it;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 错误示例
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/7 13:23
 */
public class IncorrectExample {
    public static void main(String[] args) {
        System.out.println(payment(new BigDecimal("100"), new BigDecimal("90"), "alipay"));
        System.out.println(payment(new BigDecimal("100"), new BigDecimal("200"), "alipay"));
        System.out.println(payment(new BigDecimal("100"), new BigDecimal("20"), "wechatpay"));
        System.out.println(payment(new BigDecimal("100"), new BigDecimal("200"), "wechatpay"));
    }

    /**
     * 调用支付渠道进行支付
     *
     * @param balance
     * @param amount
     * @param code
     * @return
     */
public static Result payment(BigDecimal balance, BigDecimal amount, String code) {
    // 支付宝支付渠道
    if (code.equalsIgnoreCase("alipay")) {
        if (balance.compareTo(amount) >= 0) {
            return Result.buildResult(true, String.format("支付宝支付成功，剩余余额:%s", balance.subtract(amount).toString()));
        }
        return Result.buildResult(false, "支付失败,支付宝余额不足!");
    }
    // 微信支付渠道
    else if (code.equalsIgnoreCase("wechatpay")) {
        if (balance.compareTo(amount) >= 0) {
            return Result.buildResult(true, String.format("微信支付成功，剩余余额:%s", balance.subtract(amount).toString()));
        }
        return Result.buildResult(false, "支付失败,微信余额不足!");
    } else {
        // 其它支付渠道
    }
    return Result.buildResult(false, "支付渠道不存在");
}


    /**
     * 封装响应结果
     */
    @Data
    @AllArgsConstructor
    static class Result {
        boolean rlt;
        String msg;

        /**
         * 构建响应结果
         *
         * @param rlt
         * @param msg
         * @return
         */
        static Result buildResult(boolean rlt, String msg) {
            return new Result(rlt, msg);
        }
    }
}
