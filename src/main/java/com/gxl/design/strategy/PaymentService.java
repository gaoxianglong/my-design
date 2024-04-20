package com.gxl.design.strategy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * PayService
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/4 10:15
 */
@Service
public class PaymentService implements ApplicationContextAware {
    /**
     * 记录支付参数与支付渠道的映射关系
     */
    private Map<PaymentChannel, PaymentStrategy> paymentStrategyMap = new ConcurrentHashMap<>();

    /**
     * 拉起支付
     *
     * @param balance
     * @param amount
     * @param code
     * @return
     */
    public Result payment(BigDecimal balance, BigDecimal amount, int code) {
        PaymentChannel channel = PaymentChannel.getByCode(code);
        if (Objects.isNull(channel)) {
            return Result.buildResult(false, "支付渠道不存在");
        }
        PaymentStrategy paymentStrategy = paymentStrategyMap.get(channel);
        // 调用支付
        return paymentStrategy.payment(balance, amount);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 构建业务与支付渠道的映射关系
        applicationContext.getBeansOfType(PaymentStrategy.class).forEach((k, v) -> {
            PaymentChannel channel = v.getChannel();
            paymentStrategyMap.put(channel, v);
        });
    }
}
