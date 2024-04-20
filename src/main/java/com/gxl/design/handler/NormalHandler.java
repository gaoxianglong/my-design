package com.gxl.design.handler;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 普通账户处理类
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/7 14:23
 */
@Service
@Data
public class NormalHandler extends AccountHandler {
    @Override
    public Result withdraw(AccountRequest request) {
        if (!isSupport(request)) {
            AccountHandler nextHandler = getNextHandler();
            if (Objects.nonNull(nextHandler)) {
                // 委托给下一个处理者
                return nextHandler.withdraw(request);
            }
            return Result.buildResult(false, "个人账户余额不足");
        }

        BigDecimal balance = request.getBalance();
        BigDecimal amount = request.getAmount();
        return Result.buildResult(true, "扣减个人账户金额:" + amount + ",剩余余额:" + balance.subtract(amount));
    }

    @Override
    boolean isSupport(AccountRequest request) {
        return request.getBalance().compareTo(request.getAmount()) >= 0;
    }
}
