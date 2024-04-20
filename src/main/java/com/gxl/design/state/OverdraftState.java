package com.gxl.design.state;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 透支状态
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/6 18:06
 */
@Service
public class OverdraftState implements AccountState {
    @Override
    public Result withdraw(AccountService accountService) {
        AccountRequest request = accountService.getRequest();
        if (!isSupport(request)) {
            return null;
        }
        BigDecimal overdrawBlance = request.getOverdrawBlance();
        BigDecimal amount = request.getAmount();
        if (overdrawBlance.compareTo(amount) >= 0) {
            return Result.buildResult(true, "透支额度足够,扣减金额:" + amount + ",剩余透支额度:" + overdrawBlance.subtract(amount));
        }
        return Result.buildResult(false, "透支额度不足!");
    }

    @Override
    public boolean isSupport(AccountRequest request) {
        // 是否允许透支
        return request.isOverdraw();
    }
}
