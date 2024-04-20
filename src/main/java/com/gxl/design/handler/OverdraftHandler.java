package com.gxl.design.handler;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 透支账户处理者
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/7 14:25
 */
@Service
public class OverdraftHandler extends AccountHandler {
    @Override
    public Result withdraw(AccountRequest request) {
        if (!isSupport(request)) {
            AccountHandler nextHandler = getNextHandler();
            if (Objects.nonNull(nextHandler)) {
                // 委托给下一个处理者
                return nextHandler.withdraw(request);
            }
            return Result.buildResult(false, "扣款失败,用户没有透支额度!");
        }
        BigDecimal overdrawBlance = request.getOverdrawBlance();
        BigDecimal amount = request.getAmount();
        if (request.getOverdrawBlance().compareTo(request.getAmount()) >= 0) {
            return Result.buildResult(true, "扣减透支账户金额:" + amount + ",剩余透支额度:" + overdrawBlance.subtract(amount));
        }
        return Result.buildResult(false, "透支账户可用额度不足!");
    }

    @Override
    boolean isSupport(AccountRequest request) {
        return request.isOverdraw();
    }
}
