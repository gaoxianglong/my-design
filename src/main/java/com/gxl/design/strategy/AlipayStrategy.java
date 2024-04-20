package com.gxl.design.strategy;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 支付宝支付策略类
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/4 10:03
 */
@Service
public class AlipayStrategy implements PaymentStrategy {
    @Override
    public Result payment(BigDecimal balance, BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            return Result.buildResult(true, String.format("支付宝支付成功，剩余余额:%s", balance.subtract(amount).toString()));
        }
        return Result.buildResult(false, "支付失败,支付宝余额不足!");
    }

    @Override
    public PaymentChannel getChannel() {
        return PaymentChannel.ALIPAY;
    }
}
