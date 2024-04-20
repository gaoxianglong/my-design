package com.gxl.design.strategy;

import java.math.BigDecimal;

/**
 * 支付策略接口
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/4 09:50
 */
public interface PaymentStrategy {
    /**
     * 支付
     *
     * @param balance
     * @param amount
     * @return
     */
    Result payment(BigDecimal balance, BigDecimal amount);

    /**
     * 支付渠道
     *
     * @return
     */
    PaymentChannel getChannel();
}
