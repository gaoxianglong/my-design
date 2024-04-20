package com.gxl.design.state;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 普通状态
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/6 16:14
 */
@Service
public class NormalState implements AccountState {
    /**
     * 取现逻辑
     *
     * @param accountService
     * @return
     * @throws Exception
     */
    @Override
    public Result withdraw(AccountService accountService) {
        AccountRequest request = accountService.getRequest();
        BigDecimal balance = request.getBalance();
        BigDecimal amount = request.getAmount();
        if (isSupport(request)) {
            return Result.buildResult(true, "余额足够,扣减金额:" + amount + ",剩余余额:" + balance.subtract(amount));
        }
        // 转换为透支状态
        accountService.setAccountState(accountService.getOverdraftState());
        Result rlt = accountService.withdraw(request);
        if (Objects.nonNull(rlt)) {
            return rlt;
        }
        return Result.buildResult(false, "余额不足");
    }

    @Override
    public boolean isSupport(AccountRequest request) {
        BigDecimal balance = request.getBalance();
        BigDecimal amount = request.getAmount();
        return balance.compareTo(amount) >= 0;
    }
}
